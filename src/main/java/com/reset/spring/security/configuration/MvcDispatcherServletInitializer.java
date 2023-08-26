package com.reset.spring.security.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Component
public class MvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //home url
    }
}
