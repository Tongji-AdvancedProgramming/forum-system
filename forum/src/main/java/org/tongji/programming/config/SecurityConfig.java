package org.tongji.programming.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.filter.CustomAuthenticationFilter;

import java.io.IOException;

/**
 * 安全配置。
 * 将登录的鉴权、Session下发完全交给Spring Security代办，提升系统安全水平。
 * 我们要做的就是指定鉴权规则、处理鉴权失败/成功事件。
 *
 * @author cinea
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    CustomAuthenticationFilter customAuthenticationFilter;

    @Autowired
    public void setCustomAuthenticationFilter(CustomAuthenticationFilter customAuthenticationFilter) {
        this.customAuthenticationFilter = customAuthenticationFilter;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.debug("正在配置HttpSecurity...");
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/login", "/captcha/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(config -> config.loginProcessingUrl("/login")
                        .loginPage("/login.html")
                        .successHandler(authenticationSuccessHandler())  // 自定义成功处理器
                        .failureHandler(authenticationFailureHandler())  // 自定义失败处理器
                        .permitAll())
                .exceptionHandling(config -> config.authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        var resp = new ApiDataResponse<String>(
                                "你看到了这条消息，说明你正在使用浏览器、API测试工具、curl等工具来手动访问我们的接口。" +
                                "很显然，你正在访问一个受限的接口，而接口拒绝了你，因为你没有登录。" +
                                "出于个人的、学习的、非侵入的目的，使用我们系统的接口，我作为一个曾经的极客，原则上是不反对的。" +
                                "但是希望你谨慎调用，切勿滥用，否则可能会被封禁账号、上报教师，" +
                                "滥用接口对系统造成严重破坏的，教师可能会上报学院/学校。请君自重。" +
                                "供开发者使用的login页面位于后端的/login.html端点，生产版本中可能需要加上/api前缀，请你自己探究。"
                        );
                        resp.setCode(4001);
                        resp.setMsg("未登录/登录过期");

                        response.setStatus(401);
                        response.setContentType("application/json; charset=utf-8");
                        var out = response.getOutputStream();
                        new ObjectMapper().writeValue(out, resp);
                        out.close();
                    }
                }))
        ;

        return http.build();
    }

    @Bean
    protected AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new forumAuthenticationSuccessHandler();
    }

    @Bean
    protected AuthenticationFailureHandler authenticationFailureHandler() {
        return new forumAuthenticationFailureHandler();
    }
}

class forumAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");

        var apiResponse = ApiResponse.success("登录成功");

        var out = response.getOutputStream();
        out.write(new ObjectMapper().writeValueAsBytes(apiResponse));
        out.close();
    }
}

class forumAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");

        var apiResponse = ApiResponse.fail(4001, "用户名或密码错误");

        var out = response.getOutputStream();
        out.write(new ObjectMapper().writeValueAsBytes(apiResponse));
        out.close();
    }
}
