package org.tongji.programming.controller;

import io.springboot.captcha.GifCaptcha;
import io.springboot.captcha.utils.CaptchaJakartaUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

/**
 * 验证码，使用 <a href="https://gitee.com/ele-admin/EasyCaptcha">EasyCaptcha</a> 项目本地生成
 * <p>
 * 注释：原仓库作者已经停止维护，请用<a href="https://github.com/pig-mesh/easy-captcha">此fork版</a>。
 *
 * @author cinea
 */
@Tag(
        name = "Captcha",
        description = "验证码"
)
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @GetMapping
    @Operation(summary = "获取一个验证码")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaJakartaUtil.out(new GifCaptcha(130, 48, 4), request, response);
    }

    @PostMapping("/verify")
    @Operation(summary = "验证验证码")
    public String verifyCaptcha(HttpServletRequest request, @RequestPart String code) {
        if (code != null && CaptchaJakartaUtil.ver(code, request)) {
            return "Valid";
        } else {
            return "Invalid";
        }
    }

}
