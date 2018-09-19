package com.wjaronski.debter.manager.web.config;

import com.wjaronski.debter.manager.api.config.ApiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApiConfig.class)
public class Config {


}
