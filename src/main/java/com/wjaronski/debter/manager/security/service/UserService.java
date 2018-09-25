package com.wjaronski.debter.manager.security.service;

import com.wjaronski.debter.manager.api.domain.UserBean;
import com.wjaronski.debter.manager.api.facebook.dto.Profile;
import com.wjaronski.debter.manager.api.repository.UserBeanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Wojciech Jaronski
 */

@Slf4j
@Service
public class UserService {
    private final UserBeanRepository userRepository;

    public UserService(UserBeanRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserBean saveUser(Map map) {
        log.info("Saving user {}", map);
        UserBean user = UserBean.getUserOf(map);
        return userRepository.save(user);
    }

    public UserBean saveUser(Profile profile) {
        log.info("Saving user {}", profile);
        UserBean user = UserBean.getUserOf(profile);
        return userRepository.save(user);
    }
}
