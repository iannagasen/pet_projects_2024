package dev.agasen.authserver.client;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import dev.agasen.authserver.client.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
  
  Optional<Client> findByName(String name);
}
