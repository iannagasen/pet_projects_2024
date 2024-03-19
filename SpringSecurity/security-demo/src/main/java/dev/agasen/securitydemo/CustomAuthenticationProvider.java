package dev.agasen.securitydemo;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = String.valueOf(authentication.getCredentials());

    String myUsername = "neil";
    String myPassword = "12345";

    boolean matches = myUsername.equals(username) && myPassword.equals(password);

    if (matches) {
      return new UsernamePasswordAuthenticationToken(
        username, 
        password, 
        Arrays.asList());
    } else {
      throw new AuthenticationCredentialsNotFoundException("Error!");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class
        .isAssignableFrom(authentication);
  }

}
