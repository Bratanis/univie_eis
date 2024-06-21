package org.omilab.portal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PortalServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PortalServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PortalServiceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                // Apply CORS settings to all endpoints under `/portal_service/api/**`
                registry.addMapping("/portal_service/api/**")  // Adjust the pattern to match all endpoints
                        .allowedOrigins("http://localhost:8080")  // Allow all requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify the HTTP methods allowed
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);  // Allow credentials like cookies, authorization headers, etc.
            }
        };
    }
    
}
