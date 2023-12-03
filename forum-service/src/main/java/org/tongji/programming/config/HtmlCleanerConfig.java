package org.tongji.programming.config;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cinea
 */
@Configuration
public class HtmlCleanerConfig {
    @Bean
    HtmlCleaner htmlCleaner() {
        CleanerProperties cleanerProps = new CleanerProperties();
        cleanerProps.setPruneTags("script,style,pre,code");
        return new HtmlCleaner(cleanerProps);
    }
}
