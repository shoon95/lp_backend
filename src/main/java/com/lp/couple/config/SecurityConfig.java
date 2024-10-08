package com.lp.couple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .formLogin(formLogin -> formLogin.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                .sessionManagement(session -> session.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user", "/user/sign-in", "/user/sign-up").permitAll()
                        .anyRequest().authenticated());

    return http.build();
    }
}
