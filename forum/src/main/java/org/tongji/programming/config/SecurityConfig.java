package org.tongji.programming.config;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.debug("正在配置HttpSecurity...");
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(config -> {
                    config
                            .requestMatchers("/login").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(config -> config.loginProcessingUrl("/login")
                        .successHandler(authenticationSuccessHandler())  // 自定义成功处理器
                        .failureHandler(authenticationFailureHandler())  // 自定义失败处理器
                        .permitAll())
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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(200);
        var out = new BufferedOutputStream(response.getOutputStream());
        out.write("Success".getBytes(StandardCharsets.UTF_8));
        out.close();
    }
}

class forumAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(401);
        var out = new BufferedOutputStream(response.getOutputStream());
        out.write("Sorry!".getBytes(StandardCharsets.UTF_8));
        out.close();
    }
}
