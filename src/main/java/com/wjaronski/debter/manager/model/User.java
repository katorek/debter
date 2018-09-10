package com.wjaronski.debter.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;

    public static User getUserOf(String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    @Override
    public String toString() {
        return name;
    }
}
