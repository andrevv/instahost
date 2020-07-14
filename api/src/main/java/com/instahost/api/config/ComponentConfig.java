package com.instahost.api.config;

import com.instahost.api.service.IdGenerator;
import com.instahost.api.service.IdGeneratorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfig {
    @Bean
    IdGenerator idGenerator() {
        return new IdGeneratorImpl();
    }
}
