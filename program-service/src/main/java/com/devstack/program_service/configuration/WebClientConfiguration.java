package com.devstack.program_service.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    @LoadBalanced
    public WebClient webClient(){
        return WebClient.builder().build();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        String issuerUri= "http://localhost:8080/realms/lms";
        return NimbusJwtDecoder.withIssuerLocation(issuerUri).build();
    }
}
