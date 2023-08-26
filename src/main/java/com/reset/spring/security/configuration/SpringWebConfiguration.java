package com.reset.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.reset.spring.security")
@EnableWebMvc
public class SpringWebConfiguration {
    /**
     * This method is used to define the Bean for ViewResolver in Spring MVC.
     * ViewResolver is essential for mapping view names to actual views.
     *
     * @return ViewResolver object which is an instance of InternalResourceViewResolver.
     *
     * The InternalResourceViewResolver is configured with prefix ("/WEB-INF/view/") and suffix (".jsp").
     * This setup means that if a view is returned as "home" then the actual view will
     * be a JSP at location "/WEB-INF/view/home.jsp".
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =
                new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }
}
