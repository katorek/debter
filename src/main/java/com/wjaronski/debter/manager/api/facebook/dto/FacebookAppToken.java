package com.wjaronski.debter.manager.api.facebook.dto;

import lombok.Data;

@Data
public class FacebookAppToken {
    private String access_token;
    private String token_type;
}