package dev.agasen.securitydemo;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestValidationFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
              throws IOException, ServletException {
    
    HttpServletRequest httpReq = (HttpServletRequest) req;
    HttpServletResponse httpRes = (HttpServletResponse) res;

    String requestId = httpReq.getHeader("Request-Id");

    if (requestId == null || requestId.isBlank()) {
      httpRes.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    fc.doFilter(req, res);
  }
  
}
