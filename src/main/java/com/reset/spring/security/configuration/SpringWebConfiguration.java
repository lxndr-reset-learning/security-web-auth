package com.reset.spring.security.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "com.reset.spring.security")
@EnableWebMvc
@PropertySource("classpath:logging.properties")
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
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver =
                new InternalResourceViewResolver();

        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass("org.postgresql.Driver");
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }
}
