package com.wjaronski.debter.manager.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wjaronski.debter.manager.api.facebook.dto.Profile;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String role;

    public static UserBean getUserOf(String login) {
        return UserBean.builder().name(login).build();
    }

    public static UserBean getUserOf(Map map) {
        return UserBean.builder()
                .id(Long.valueOf((String) map.get("id")))
                .name((String) map.get("name"))
                .email((String) map.get("email"))
                .role((String) map.get("role"))
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
