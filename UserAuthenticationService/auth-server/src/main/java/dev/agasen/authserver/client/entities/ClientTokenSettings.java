package dev.agasen.authserver.client.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "token_settings")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientTokenSettings {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "access_token_ttl")
  private Long accessTokenTTL;

  private String type;

  @OneToOne
  private Client client;
}
