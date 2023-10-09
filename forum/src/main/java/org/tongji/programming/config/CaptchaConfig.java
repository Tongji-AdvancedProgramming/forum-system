package org.tongji.programming.config;

import io.springboot.captcha.GifCaptcha;
import io.springboot.captcha.base.Captcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用的验证码是GIF动图，因为我喜欢
 * @author cinea
 */
@Configuration
public class CaptchaConfig {
    @Bean
    protected Captcha captcha(){
        return new GifCaptcha(130, 48, 4);
    }
}
