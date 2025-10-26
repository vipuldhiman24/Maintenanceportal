package com.maintenanceportal.maintenance_request_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8080/users") // ✅ user-service URL
                .build();
    }
}