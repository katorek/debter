package com.wjaronski.debter.manager.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wjaronski.debter.manager.api.facebook.dto.Profile;
import com.wjaronski.debter.manager.api.facebook.dto.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Builder
@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class UserBean {

    @Id
//    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String passwordHash;

    private String provider;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public static UserBean getUserOf(String login) {
        return UserBean.builder().name(login).build();
    }

    public static UserBean getUserOf(Map map) {
        return UserBean.builder()
                .id(Long.valueOf((String) map.get("id")))
                .name((String) map.get("name"))
                .email((String) map.get("email"))
                .build();
    }

    public static UserBean getUserOf(Profile profile) {
        return UserBean.builder()
                .id(Long.valueOf(profile.getId()))
                .name(profile.getName())
                .email(profile.getEmail())
                .role(profile.getRole())
                .build();
    }


    @Override
    public String toString() {
        return name;
    }
}
