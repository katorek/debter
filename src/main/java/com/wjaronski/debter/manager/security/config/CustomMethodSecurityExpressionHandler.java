package com.wjaronski.debter.manager.security.config;

import com.wjaronski.debter.manager.api.facebook.ProfileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

@Slf4j
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    private ProfileInfoService profileInfoService;

    CustomMethodSecurityExpressionHandler(ProfileInfoService profileInfoService) {
        this.profileInfoService = profileInfoService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {
        log.info("MethodSecurityExpressionOperations");
        CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication, profileInfoService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}