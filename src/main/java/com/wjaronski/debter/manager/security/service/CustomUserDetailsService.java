//package com.wjaronski.debter.manager.security.service;
//
//import com.wjaronski.debter.manager.api.repository.UserRepository;
//import com.wjaronski.debter.manager.security.model.CurrentUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.getByName(username).map(user -> CurrentUser.builder()
//                .login(user.getName())
//                .passwordHash(user.getPasswordHash())
//                .role("ROLE_" + user.getRole().name())
//                .build())
//                .orElseThrow(() -> new UsernameNotFoundException(username));
//    }
//}
