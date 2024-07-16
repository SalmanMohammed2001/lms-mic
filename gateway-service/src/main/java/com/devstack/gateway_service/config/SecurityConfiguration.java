package com.devstack.gateway_service.config;


import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


;import java.util.List;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

        @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity){
//        serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .cors(httSecurityCorsConfigure->httSecurityCorsConfigure.configurationSource(corsConfigurationSource()))
//                .authorizeExchange(authorizeExchangeSpec ->
//                        authorizeExchangeSpec
//                                .pathMatchers(HttpMethod.POST,"/api/v1/users/create").permitAll()
//                                .pathMatchers(HttpMethod.POST,"/api/v1/users/login").permitAll()
//                                .pathMatchers("/eureka/**")
//                        .permitAll()
//                        .anyExchange()
//                        .authenticated())
//                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
//        return  serverHttpSecurity.build();

            serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                    /* .cors(httpSecurityCorsConfigurer ->
                             httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()))*/
                    .authorizeExchange(authorize ->
                            authorize
                                    .pathMatchers("/api/v1/users/**").permitAll()
                                    .pathMatchers("/eureka/**")
                                    .permitAll()
                                    .anyExchange()
                                    .authenticated())
                    .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

            return  serverHttpSecurity.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder(){
    return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:8080/realms/lms");
    }

//     @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        // Set allowed headers
//        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
//        // Set allowed origins
//        corsConfiguration.setAllowedOrigins(List.of("*"));
//        // Set allowed methods
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
//        // Set whether credentials are allowed
//        corsConfiguration.setAllowCredentials(false);
//        // Set exposed headers
//        corsConfiguration.setExposedHeaders(List.of("Authorization"));
//        // Set max age
//        corsConfiguration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:4200")); // Specify your allowed origin
        corsConfig.setAllowedMethods(List.of("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.addAllowedHeader("*");

        org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source =
                new org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
