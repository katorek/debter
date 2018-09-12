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
public class User {

    @Column(unique = true, nullable = false)
    private String name;

    @Id
    @GeneratedValue
    private Long id;
    private String passwordHash;
    @NotNull
    private Role role;

    @Column(unique = true)
    private String email;

    public static User getUserOf(String name) {
        return User.builder().name(name).build();
    }

    public enum Role {
        USER, ADMIN, FACEBOOK_USER
    }

    @Override
    public String toString() {
        return name;
    }
}
