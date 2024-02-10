package dev.agasen.authserver.client.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "client_name")
  private String name;

  private String secret;

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<RedirectUrl> redirectUrls = new HashSet<>();

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<GrantType> grantTypes = new HashSet<>();

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<Scope> scopes = new HashSet<>();

  @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
  private Set<AuthenticationMethod> authenticationMethods = new HashSet<>();

  @OneToOne(mappedBy = "client", fetch = FetchType.EAGER)
  private ClientTokenSettings tokenSettings;

}
