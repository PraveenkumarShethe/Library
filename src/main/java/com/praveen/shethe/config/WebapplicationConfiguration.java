package com.praveen.shethe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.Filter;
import java.io.IOException;

/**
 * Created by Praveenkumar on 3/6/2017.
 */
@Configuration
@ComponentScan(basePackages = "com.praveen.shethe")
@EnableJpaRepositories("com.praveen.shethe.repository")
@EnableWebMvc
@ConfigurationProperties(value = "classpath:/application.yml")
public class WebapplicationConfiguration {

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
        return resolver;
    }

    @Bean
    public PropertySource yamlPropertySourceLoader() throws IOException {

        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();
        return (PropertySource) yamlPropertySourceLoader.load("application.yml",
                new ClassPathResource("application.yml"), "default");
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

}
