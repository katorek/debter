package com.wjaronski.debter.manager.config;

import com.wjaronski.debter.manager.converter.ProductToDebtConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Wojciech Jaronski
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProductToDebtConverter());
    }

}
