# Spring Security In-Memory Authentication Project

This is a Java-based Spring MVC project learning that uses the Spring Security module to secure web applications. It utilizes in-memory user authentication, including role-based access.

## Key Features

- **Spring Security Configuration**: Configures the necessary components to secure the web application with Spring Security. This includes methods for setting up the authentication manager and defining in-memory user credentials.

- **In-Memory User Authentication**: Seeded with a set of users each with defined roles ('EMPLOYEE', 'HR', 'MANAGER'). User credentials and roles are configured on startup and stored in-memory.

- **BCrypt Password Encoder**: Encrypts user passwords for secure storage using BCrypt's $2A version.

- **URL-based Authorization**: Configures path-based authorization rules based on user roles. Different roles grant access to specific endpoints within the application.

- **Custom Security Filter Chain**: Builds and sets up a custom SecurityFilterChain using the provided HttpSecurity object and HandlerMappingIntrospector.

This project is intended as a basis for understanding and extending secure Spring MVC applications. The in-memory users and roles can be replaced or supplemented with a persistent, real-world authentication infrastructure like a SQL database or an OAuth2 server.