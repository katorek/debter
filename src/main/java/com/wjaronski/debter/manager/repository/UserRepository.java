package com.wjaronski.debter.manager.repository;

import com.wjaronski.debter.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByName(String name);


}
