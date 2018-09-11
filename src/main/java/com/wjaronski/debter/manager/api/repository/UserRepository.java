package com.wjaronski.debter.manager.api.repository;

import com.wjaronski.debter.manager.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByName(String name);
//    User findByUsername(String username);
}
