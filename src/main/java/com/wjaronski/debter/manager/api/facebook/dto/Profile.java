package com.wjaronski.debter.manager.api.facebook.dto;

import lombok.Data;

import javax.persistence.Transient;
import java.util.Map;

@Data
public class Profile {
    private String id;
    private String name;
    private String email;
    private UserRole role;
    @Transient
//    private ProfileData friends;

    public void mergeWithUser(Map<String, Object> attributes) {
        if (id.equals(attributes.get("id"))) {
            name = (name == null) ? attributes.get("name").toString() : name;
            email = (email == null) ? attributes.get("email").toString() : email;
//            role = "user";
            role = (role == null) ? UserRole.getFromFacebookRole(attributes.get("role").toString()) : role;
        }
    }
}
