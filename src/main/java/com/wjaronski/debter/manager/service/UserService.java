//package com.wjaronski.debter.manager.service;
//
//import com.wjaronski.debter.manager.model.User;
//import com.wjaronski.debter.manager.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    User save(User user) {
//        Optional<User> optionalUser = userRepository.getByName(user.getName());
//
//        //todo save if found ?
//        if (!optionalUser.isPresent()) {
//            return userRepository.save(user);
//        }
//        return user;
//    }
//
//    User save(String userName) {
//        return save(User.getUserOf(userName));
//    }
//}
