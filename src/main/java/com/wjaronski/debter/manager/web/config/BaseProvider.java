package com.wjaronski.debter.manager.web.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;

@Data
@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

    private Facebook facebook;
    private Google google;
    private ConnectionRepository connectionRepository;

    public BaseProvider(Facebook facebook, Google google, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.google = google;
    }
}
