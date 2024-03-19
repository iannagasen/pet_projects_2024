package dev.agasen.securitydemo;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
              throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;

    var requestId = req.getHeader("Request-Id");

    log.info("Successfully authenticated request with Id: " + requestId );

    chain.doFilter(request, response);
  }
  
}
