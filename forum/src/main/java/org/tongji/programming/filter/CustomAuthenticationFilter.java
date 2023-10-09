package org.tongji.programming.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.springboot.captcha.utils.CaptchaJakartaUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.helper.RealIpHelper;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 登录接口主要做两个限制：一是验证码，二是限流。
 *
 * @author cinea
 */
@Slf4j
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        if (!"/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        //************************//
        //*   Part1. 限制流量      *//
        //************************//

        var ip = RealIpHelper.getClientIpAddr(request);
        var key = "forum:login-limit:" + ip;

        // 如果超过限制，则拒绝请求
        var count = redisTemplate.opsForValue().increment(key, 1);
        if (count != null && (long) count > 10) {
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
            response.setStatus(429);
            writeResponse(response, tooManyRequests());
            return;
        }

        // 如果key是新的（即第一次请求），为它设置一个60秒的过期时间
        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        }

        //************************//
        //*   Part2. 检查验证码    *//
        //************************//

        Optional<String> code = Optional.empty();

        // 检查是否为multipart/form-data请求
        if (JakartaServletFileUpload.isMultipartContent(request)) {
            try {
                var parts = request.getParts();
                for (var part : parts) {
                    if ("code".equals(part.getName()) && "text/plain".equals(part.getContentType())) {
                        try {
                            var stream = part.getInputStream();
                            var outStream = new StringWriter();
                            IOUtils.copy(stream, outStream, StandardCharsets.UTF_8);
                            code = Optional.of(outStream.toString());
                            break;
                        } catch (IOException e) {
                            log.error("{}", e.getLocalizedMessage());
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                            writeResponse(response, captchaFailed());
                        }
                    }
                }
            } catch (Exception e) {
                log.error("{}", e.getLocalizedMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                writeResponse(response, captchaFailed());
            }
        }
        if (code.isEmpty()) {
            // 如果不是multipart/form-data，尝试从URL参数中获取code
            code = Optional.ofNullable(request.getParameter("code"));
        }

        if (code.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            writeResponse(response, captchaMissing());
        } else {
            if (CaptchaJakartaUtil.ver(code.get(), request)) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                writeResponse(response, captchaInvalid());
            }
        }
    }

    private void writeResponse(HttpServletResponse response, ApiResponse respData) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        new ObjectMapper().writeValue(response.getOutputStream(), respData);
    }

    private ApiResponse captchaMissing() {
        return ApiResponse.fail(4002, "未提交验证码");
    }

    private ApiResponse captchaInvalid() {
        return ApiResponse.fail(4002, "验证码错误");
    }

    private ApiResponse captchaFailed() {
        return ApiResponse.fail(5000, "验证码验证失败");
    }

    private ApiDataResponse<String> tooManyRequests() {
        var resp = new ApiDataResponse<>("每次登录行为都会记录IP，校园网内IP可直接追溯到人，爆破行为百害无利。");
        resp.setCode(4029);
        resp.setMsg("您的操作太频繁了，请等会再来吧");
        return resp;
    }
}
