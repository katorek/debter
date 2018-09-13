package com.wjaronski.debter.manager.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class UserBean {


    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;
    private String passwordHash;
    @NotNull
    private Role role;

    private String provider;
    private String email;

    public static UserBean getUserOf(String login) {
        return UserBean.builder().login(login).build();
    }

    public enum Role {
        USER, ADMIN, FACEBOOK_USER
    }


    @Override
    public String toString() {
        return login;
    }
}
