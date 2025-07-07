package com.cg.UserManagementSpringSecurity.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cg.UserManagementSpringSecurity.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Autowired
private UserService userService;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
http.authorizeRequests().requestMatchers("/users").hasRole("ADMIN").anyRequest().authenticated().and()
.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
return http.build();
}

@Bean
public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService)
.passwordEncoder(passwordEncoder()).and().build();
}

@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}
}