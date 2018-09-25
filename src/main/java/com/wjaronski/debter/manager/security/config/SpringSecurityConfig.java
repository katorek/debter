package com.wjaronski.debter.manager.security.config;

import com.wjaronski.debter.manager.api.facebook.FacebookRoleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private FacebookRoleProvider facebookRoleProvider;

//    @Autowired
//    public SpringSecurityConfig(ProfileInfoService profileInfoService) {
//        this.profileInfoService = profileInfoService;
//    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        CustomMethodSecurityExpressionHandler expressionHandler =
//                new CustomMethodSecurityExpressionHandler(profileInfoService);
//        expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
//        return expressionHandler;
        return new CustomMethodSecurityExpressionHandler(facebookRoleProvider);
    }

    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private final SecurityHandler securityHandler;

        public WebSecurityConfig(SecurityHandler securityHandler) {
            this.securityHandler = securityHandler;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/oauth_login")
                    .permitAll()
                    .anyRequest()

                    .authenticated()

                    .and()
                    .oauth2Login()
                    .loginPage("/oauth_login")
                    .successHandler(securityHandler)

//                .defaultSuccessUrl("/loginSuccess")
                    .failureUrl("/loginFailed");

        }
    }
}
