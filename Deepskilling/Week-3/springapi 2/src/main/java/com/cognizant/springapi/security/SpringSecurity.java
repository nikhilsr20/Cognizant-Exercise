package com.example.springapi.security;

import org.apache.logging.log4j.util.Base64Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public UserDetailsService users()
    {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("pwd"))
                .roles("ADMIN") // ROLE_ADMIN
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("pwd"))
                .roles("USER") // ROLE_USER
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

        /*
                12345
                  |
                BCrypt
                  |
               @2A@10!Q8
        */
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/countries")
                        .hasRole("USER")
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
