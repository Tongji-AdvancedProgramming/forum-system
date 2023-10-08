package org.tongji.programming.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 启用Http Redis Session
 * @author cinea
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}
