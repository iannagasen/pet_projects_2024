package dev.agasen.authserver;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;

public abstract class MySqlTestBase {

  private static JdbcDatabaseContainer db = 
      new MySQLContainer<>("mysql:8.0.32").withStartupTimeoutSeconds(300);

  static {
    db.start();
  }

  @DynamicPropertySource
  static void databaseProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", db::getJdbcUrl);
    registry.add("spring.datasource.username", db::getJdbcUrl);
    registry.add("spring.datasource.password", db::getJdbcUrl);
  }
  
}
