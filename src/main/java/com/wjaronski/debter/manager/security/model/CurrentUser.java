//package com.wjaronski.debter.manager.security.model;
//
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Getter
//@Builder
//@ToString
//@RequiredArgsConstructor
//public class CurrentUser implements UserDetails {
//
//    private static final long serialVersionUID = 6608807423798767487L;
//
//    @NonNull
//    private String login;
//
//    @NonNull
//    private String passwordHash;
//
//    @NonNull
//    private String role;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(role);
//        return authorityList;
//    }
//
//    @Override
//    public String getPassword() {
//        return passwordHash;
//    }
//
//    @Override
//    public String getUsername() {
//        return login;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}