package com.wjaronski.debter.manager.api.facebook;

import com.wjaronski.debter.manager.api.ApiBinding;
import lombok.extern.slf4j.Slf4j;

//https://developers.facebook.com/docs/graph-api/using-graph-api/
@Slf4j
public class Facebook extends ApiBinding {
    private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v2.12";
    private static final String GRAPGH_PARAMS = "?fields=id,name,email";

    public Facebook(String accessToken) {
        super(accessToken);
    }

    public Profile getProfile() {
        Profile p = restTemplate.getForObject(
                GRAPH_API_BASE_URL + "/me" + GRAPGH_PARAMS, Profile.class
        );
        log.info("Facebook profile: {}", p);
        return p;
    }

}
