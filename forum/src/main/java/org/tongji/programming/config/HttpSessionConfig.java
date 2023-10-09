package org.tongji.programming.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 启用Http Redis Session，Spring很聪明，不需要我们设置，只要有这个类就够了
 * 登录失效时间：1天不活跃
 *
 * @author cinea
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 24 * 60 * 60)
public class HttpSessionConfig {
}
