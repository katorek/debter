package com.wjaronski.debter.manager.api.facebook.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Wojciech Jaronski
 */
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.facebook")
@Data
public class FacebookProperties {
    private String clientId;
    private String clientSecret;
    private String redirectHost;
}