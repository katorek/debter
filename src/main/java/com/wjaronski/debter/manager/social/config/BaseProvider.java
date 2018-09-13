package com.wjaronski.debter.manager.social.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;

@Data
@Slf4j
@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

    private Facebook facebook;
    //    private Google google;
    private ConnectionRepository connectionRepository;

    //    @Lazy
    public BaseProvider(Facebook facebook, /*Google google,*/ ConnectionRepository connectionRepository) {
        log.error("facebook");
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        /*this.google = google;*/
    }
}
