package com.wjaronski.debter.manager.security.config;

import com.wjaronski.debter.manager.api.domain.UserBean;
import com.wjaronski.debter.manager.api.facebook.ProfileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

@Slf4j
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private ProfileInfoService profileInfoService;

    private Object filterObject;
    private Object returnObject;

    CustomMethodSecurityExpressionRoot(Authentication authentication, ProfileInfoService profileInfoService) {
        super(authentication);
        this.profileInfoService = profileInfoService;
        log.info("CustomMethodSecurityExpressionRoot");
    }

    public boolean isMember(String group) {
        UserBean u = UserBean.getUserOf(((DefaultOAuth2User) this.getPrincipal()).getAttributes());

        String userRole = profileInfoService.getRole(u.getId().toString());

        return userRole.equals(group);

    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
