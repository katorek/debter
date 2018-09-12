//package com.wjaronski.debter.manager.security.config;
//
//import com.wjaronski.debter.manager.security.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////import org.springframework.social.connect.ConnectionFactoryLocator;
////import org.springframework.social.connect.UsersConnectionRepository;
////import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
//
//@EnableWebSecurity
//@Configuration
////@ComponentScan(basePackages = {"com.wjaronski.debter.manager.security.service"})
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired
////    private ConnectionFactoryLocator connectionFactoryLocator;
////
////    @Autowired
////    private UsersConnectionRepository usersConnectionRepository;
////
////    @Autowired
////    private FacebookConnectionSignup facebookConnectionSignup;
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.httpBasic();
//        http.authorizeRequests()
//                .antMatchers("/info", "/health").permitAll()
//                .anyRequest().fullyAuthenticated();
//
//
////        http
////                .csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/login*").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin().loginPage("/login").permitAll()
////                .and()
////                .logout();
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider
//                = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(encoder());
//        return authProvider;
//    }
//
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder(11);
//    }
//
////    @Bean
////    public ProviderSignInController providerSignInController() {
////        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
////                .setConnectionSignUp(facebookConnectionSignup);
////
////        return new ProviderSignInController(
////                connectionFactoryLocator,
////                usersConnectionRepository,
////                new FacebookSignInAdapter());
////    }
//
//    /*
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication().
////                .withUser(User.withUserDetails(CustomUserPrincipal));// User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
//                .withUser("admin");// User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
//    }
//    */
//}
