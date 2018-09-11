package com.wjaronski.debter.manager.security.service;

import com.wjaronski.debter.manager.api.domain.User;
import com.wjaronski.debter.manager.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Slf4j
public class UserInitializer {

    public UserInitializer(UserRepository repository, PasswordEncoder encoder) {
        User admin = User.builder().name("admin")
                .passwordHash(encoder.encode("admin"))
                .role(User.Role.ADMIN)
                .build();
        User plain = User.builder().name("user")
                .passwordHash(encoder.encode("user"))
                .role(User.Role.USER)
                .build();
        Stream.of(admin, plain)
                .forEach(user -> {
                    repository.save(user);
                    log.info("user added: {} pass: {}", user, user.getPasswordHash());
                });
    }
}
