package io.github.akhil9tiet.content_calendar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyWebConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
