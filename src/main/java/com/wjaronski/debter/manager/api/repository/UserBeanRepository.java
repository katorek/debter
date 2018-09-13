package com.wjaronski.debter.manager.api.repository;

import com.wjaronski.debter.manager.api.domain.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBeanRepository extends JpaRepository<UserBean, Long> {
    Optional<UserBean> getByLogin(String login);
//    UserBean findByUsername(String username);
}
