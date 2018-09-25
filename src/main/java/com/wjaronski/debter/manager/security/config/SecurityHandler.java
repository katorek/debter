package com.wjaronski.debter.manager.security.config;

import com.wjaronski.debter.manager.api.facebook.ProfileInfoService;
import com.wjaronski.debter.manager.api.facebook.dto.Profile;
import com.wjaronski.debter.manager.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    private final ProfileInfoService infoService;

    public SecurityHandler(UserService service, ProfileInfoService infoService) {
        this.service = service;
        this.infoService = infoService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

        Profile profile = infoService.getProfile();
        profile.mergeWithUser(user.getAttributes());
        log.info("{}", profile);
        service.saveUser(profile);
        httpServletResponse.sendRedirect("/loginSuccess");
    }
}
