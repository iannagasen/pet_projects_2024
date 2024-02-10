package dev.agasen.authserver.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dev.agasen.authserver.MySqlTestBase;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClientRepositoryTest extends MySqlTestBase {

  @Autowired private ClientRepository clientRepository;

  @BeforeEach
  void setupDatabase() {

  }

  @Test
  void testInitialCount() {
    long count = clientRepository.count();
    assertEquals(1, count);
  }


  
}
