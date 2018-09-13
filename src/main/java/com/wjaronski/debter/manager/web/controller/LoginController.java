package com.wjaronski.debter.manager.web.controller;

import com.wjaronski.debter.manager.api.domain.UserBean;
import com.wjaronski.debter.manager.social.providers.FacebookProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    FacebookProvider facebookProvider;

    @GetMapping("/facebook")
    public String loginToFacebook(Model model) {
        return facebookProvider.getFacebookUserData(model, UserBean.builder().build());
    }

    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }
} 