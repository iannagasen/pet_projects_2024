package dev.agasen.webfluxpatterns.sec03.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.agasen.webfluxpatterns.sec03.dto.OrchestrationRequestContext;

public class DebugUtil {
    
  private static final ObjectMapper mapper = new ObjectMapper();
  
  public static void print(OrchestrationRequestContext ctx) {
    try {
      String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ctx);
      System.out.println(json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
