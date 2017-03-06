package com.praveen.shethe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Praveenkumar on 3/6/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.praveen.shethe")
@EnableWebMvc
public class WebapplicationConfiguration {

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        return resolver;
    }
}
