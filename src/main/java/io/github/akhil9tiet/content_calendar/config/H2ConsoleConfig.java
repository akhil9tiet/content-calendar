package io.github.akhil9tiet.content_calendar.config;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * H2 Console configuration for Spring Boot 4.x
 *
 * Access H2 console at: http://localhost:8080/h2-console
 * JDBC URL: jdbc:h2:mem:content
 * Username: sa
 * Password: (leave empty)
 */
@Configuration
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2servletRegistration() {
        ServletRegistrationBean<JakartaWebServlet> registrationBean =
            new ServletRegistrationBean<>(new JakartaWebServlet());
        registrationBean.addUrlMappings("/h2-console/*");
        return registrationBean;
    }
}
