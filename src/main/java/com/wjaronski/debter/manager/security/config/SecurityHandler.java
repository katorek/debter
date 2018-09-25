package com.wjaronski.debter.manager.security.config;

import com.wjaronski.debter.manager.api.domain.UserBean;
import com.wjaronski.debter.manager.api.facebook.FacebookRoleProvider;
import com.wjaronski.debter.manager.api.facebook.dto.UserRole;
import com.wjaronski.debter.manager.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Wojciech Jaronski
 */

@Slf4j
@Service
public class SecurityHandler implements AuthenticationSuccessHandler {

    private final UserService service;
    private final FacebookRoleProvider facebookRoleProvider;

    @Autowired
    public SecurityHandler(UserService service, FacebookRoleProvider facebookRoleProvider) {
        this.service = service;
        this.facebookRoleProvider = facebookRoleProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        UserBean user = UserBean.getUserOf(oauthUser.getAttributes());
        UserRole role = facebookRoleProvider.getRole(user.getId());
        user.setRole(role);
        log.info("{}", user);
        service.saveUser(user);
        httpServletResponse.sendRedirect("/loginSuccess");
    }
}
