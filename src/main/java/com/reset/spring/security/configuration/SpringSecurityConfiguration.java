package com.reset.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.sql.DataSource;

/**
 * Configuration class for Spring Security.
 */
@EnableWebSecurity // since Spring 5.0 it is not includes @SpringWebConfiguration anymore
@Configuration
public class SpringSecurityConfiguration {
    @Autowired
    private DataSource dataSource;

    // Inject MVC Request Matcher introspector for matching requests.
    @Autowired
    private HandlerMappingIntrospector introspector;

    // Configure global user details setting their roles and authorities
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new MvcRequestMatcher(introspector, "/")).hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                        .requestMatchers(new MvcRequestMatcher(introspector, "/hr_info")).hasRole("HR")
                        .requestMatchers(new MvcRequestMatcher(introspector, "/manager_info")).hasRole("MANAGER")
                        .anyRequest().authenticated())
                .formLogin()
                .permitAll(); // creates its web view, not as a notification in browser. We don't have to write requestMatchers with it called.

        return http.build();
    }
}