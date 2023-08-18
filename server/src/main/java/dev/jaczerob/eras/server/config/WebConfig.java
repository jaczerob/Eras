package dev.jaczerob.eras.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebConfig {
    @Value("${cors.allowed.origin}")
    private String corsAllowedOrigin;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        System.out.println(corsAllowedOrigin);

        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(corsAllowedOrigin));
        configuration.setAllowedMethods(List.of("GET"));
        configuration.setAllowedHeaders(List.of("Content-Type", "Access-Control-Allow-Origin"));
        configuration.setAllowCredentials(true);

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}