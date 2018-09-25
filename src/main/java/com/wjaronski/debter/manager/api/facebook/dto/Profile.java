package com.wjaronski.debter.manager.api.facebook.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Profile {
    private String id;
    private String name;
    private String email;
    private String role;
    private List<Profile> friends;

    public void mergeWithUser(Map<String, Object> attributes) {
        if (id.equals(attributes.get("id"))) {
            name = (name == null) ? attributes.get("name").toString() : name;
            email = (email == null) ? attributes.get("email").toString() : email;
//            role = "user";
            role = (role == null) ? attributes.get("role").toString() : role;
        }
    }
}
