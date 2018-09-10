package com.wjaronski.debter.manager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
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
