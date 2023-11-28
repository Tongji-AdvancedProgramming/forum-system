package org.tongji.programming.config;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cinea
 */
@Configuration
public class MeiliConfig {
    @Value("${forum.meili.host}")
    private String host;

    @Value("${forum.meili.key}")
    private String key;

    @Bean
    Client meiliClient() {
        return new Client(new Config(host, key));
    }
}
