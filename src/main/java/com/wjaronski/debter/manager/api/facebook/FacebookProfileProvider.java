package com.wjaronski.debter.manager.api.facebook;

import com.wjaronski.debter.manager.api.ApiBinding;
import com.wjaronski.debter.manager.api.facebook.dto.Profile;
import lombok.extern.slf4j.Slf4j;

//https://developers.facebook.com/docs/graph-api/using-graph-api/
@Slf4j
public class FacebookProfileProvider extends ApiBinding {
    private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v3.1";

    public FacebookProfileProvider(String accessToken) {
        super(accessToken);
    }

    Profile getProfile() {
        return restTemplate.getForObject(
                GRAPH_API_BASE_URL + "/me", Profile.class
        );
    }


}
