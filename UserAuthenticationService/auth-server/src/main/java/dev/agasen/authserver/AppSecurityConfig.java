package dev.agasen.authserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@Configuration
public class AppSecurityConfig {
  
  @Bean
  @Order(1)
  public SecurityFilterChain authServerFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
        .oidc(Customizer.withDefaults());

    http.exceptionHandling(exc -> exc
      .defaultAuthenticationEntryPointFor(
        new LoginUrlAuthenticationEntryPoint("/login"),
        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
      )
    );   

    return http.build();
  }

  // @Bean
  // @Order(2)
  // public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
  //   return http.build();
  // }
  @Bean
  @Order(2)
  public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
    http.formLogin(Customizer.withDefaults());

    http.authorizeHttpRequests(c -> c.anyRequest().permitAll());

    return http.build();
  }


  @Bean 
  public AuthorizationServerSettings authorizationServerSettings() {
    return AuthorizationServerSettings.builder().build();
  }

}
