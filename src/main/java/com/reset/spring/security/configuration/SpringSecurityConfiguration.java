package com.reset.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/**
 * Configuration class for Spring Security.
 *
 * This class provides the necessary configuration for securing the application using Spring Security.
 * It includes methods for configuring the authentication manager, defining in-memory users, and setting up URL-based
 * authorization rules.
 */
@EnableWebSecurity // since Spring 5.0 it is not includes @SpringWebConfiguration anymore
@Configuration
public class SpringSecurityConfiguration {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoderBCrypt();

        addUserToMemory(auth, encoder, "user", "user", "EMPLOYEE", "HR", "MANAGER");
        addUserToMemory(auth, encoder, "alex", "alex", "EMPLOYEE", "MANAGER");
        addUserToMemory(auth, encoder, "ivan", "ivan", "EMPLOYEE", "HR");
    }

    private void addUserToMemory(AuthenticationManagerBuilder auth, PasswordEncoder encoder,
                                 String username, String password, String... roles) throws Exception {

        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser(username)
                .password(encoder.encode(password))
                .roles(roles);

    }

    @Bean
    public PasswordEncoder passwordEncoderBCrypt() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
    }

    @Bean
    public SecurityFilterChain securityfilterchain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(new MvcRequestMatcher(introspector, "/"))
                                .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                                .requestMatchers(new MvcRequestMatcher(introspector, "/hr_info"))
                                .hasRole("HR")

                                .requestMatchers(new MvcRequestMatcher(introspector, "/?continue"))
                                .hasAnyRole("EMPLOYEE", "HR", "MANAGER")

                                .requestMatchers(new MvcRequestMatcher(introspector, "/manager_info"))
                                .hasRole("MANAGER")
                                .anyRequest().hasRole("EMPLOYEE")) // if we pass to path not specified above, it's allowed
                .formLogin() //creates form
                .permitAll(); // creates its web view, not as a notification in browser. We don't have to write requestMatchers with it called.

        return http.build();
    }
}

