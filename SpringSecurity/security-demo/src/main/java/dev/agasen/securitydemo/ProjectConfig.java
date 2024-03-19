package dev.agasen.securitydemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

  private final CustomAuthenticationProvider authProvider;

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults());


    http.authenticationProvider(authProvider);

    http.addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class);
    http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);

    var user = User.withUsername("ian")
      .password("12345")
      .authorities("read")
      .build();

    http.authorizeHttpRequests(c -> c.anyRequest().authenticated());


    var userDetailsService = new InMemoryUserDetailsManager(user);

    http.userDetailsService(userDetailsService);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
