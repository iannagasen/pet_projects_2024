package dev.agasen.ecomm.framework.configs;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ORIGIN;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class SecurityConfig {
  
  @Bean
  public CorsFilter corsFilter() {
    final var source = new UrlBasedCorsConfigurationSource();
    final var config = new CorsConfiguration();

    config.setAllowCredentials(true);
    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
    config.setAllowedHeaders(Arrays.asList(
      ORIGIN,
      CONTENT_TYPE,
      ACCEPT,
      AUTHORIZATION));
    config.setAllowedMethods(Arrays.asList(
      "GET",
      "POST",
      "DELETE",
      "PUT",
      "PATCH"));
    
    source.registerCorsConfiguration("/**", config);
    
    return new CorsFilter(source);
  }
}
