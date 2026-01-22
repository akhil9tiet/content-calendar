package io.github.akhil9tiet.content_calendar.config;

import org.springframework.context.annotation.Configuration;

/**
 * H2 Console configuration.
 *
 * Note: Spring Boot auto-configures H2 console when:
 * - spring.h2.console.enabled=true is set in application.properties
 * - H2 dependency is on the classpath
 *
 * Access H2 console at: http://localhost:8080/h2-console
 * JDBC URL: jdbc:h2:mem:testdb
 * Username: sa
 * Password: (leave empty)
 */
@Configuration
public class H2ConsoleConfig {
    // Spring Boot auto-configuration handles H2 console registration
    // No manual servlet registration needed
}
