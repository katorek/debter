package com.wjaronski.debter.manager.web.controller;

import com.wjaronski.debter.manager.api.facebook.Facebook;
import com.wjaronski.debter.manager.api.facebook.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @Autowired
    private final Facebook facebook;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public InfoController(Facebook facebook) {
        this.facebook = facebook;
    }

    @GetMapping("/me")
    public String loginToFacebook() {
        Profile profile = facebook.getProfile();

        return profile.toString();
    }

}
