package com.wjaronski.debter.manager.api.config;

import com.wjaronski.debter.manager.api.facebook.properties.FacebookProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FacebookProperties.class)
//@ComponentScan
//@EnableJpaRepositories("com.wjaronski.debter.manager.api.repository")
public class ApiConfig {
}
