package com.wjaronski.debter.manager.web.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/connect")
public class ChangeDefaultFlowController extends ConnectController {

    public ChangeDefaultFlowController(ConnectionFactoryLocator connectionFactoryLocator,
                                       ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    protected String connectedView(String providerId) {
        return "redirect:/" + providerId;
    }
}
