package dev.agasen.authserver;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.agasen.authserver.client.ClientRepository;
import dev.agasen.authserver.client.entities.AuthenticationMethod;
import dev.agasen.authserver.client.entities.Client;
import dev.agasen.authserver.client.entities.ClientTokenSettings;
import dev.agasen.authserver.client.entities.GrantType;
import dev.agasen.authserver.client.entities.RedirectUrl;
import dev.agasen.authserver.client.entities.Scope;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RegisteredClientIntegrationService implements RegisteredClientRepository {

  private final ClientRepository clientRepository;

  @Transactional
  public void save(RegisteredClient registeredClient) {
    Client client = new Client();
    client.setName(registeredClient.getClientId());
    client.setSecret(registeredClient.getClientSecret());
    client.setAuthenticationMethods(mapSet(registeredClient.getClientAuthenticationMethods(), m -> new AuthenticationMethod(null, m.getValue(), client)));
    client.setGrantTypes(mapSet(registeredClient.getAuthorizationGrantTypes(), gt -> new GrantType(null, gt.getValue(), client)));
    client.setScopes(mapSet(registeredClient.getScopes(), s -> new Scope(null, s, client)));
    client.setRedirectUrls(mapSet(registeredClient.getRedirectUris(), url -> new RedirectUrl(null, url, client)));
    client.setTokenSettings(new ClientTokenSettings(null, 
        registeredClient.getTokenSettings().getAuthorizationCodeTimeToLive().toHours(), 
        registeredClient.getTokenSettings().getAccessTokenFormat().getValue(),
        client));
    clientRepository.save(client);
  }

  public RegisteredClient findById(String id) {
    return clientRepository.findById(Long.valueOf(id))
        .map(this::toRegisteredClient)
        .orElseThrow();
  }

  public RegisteredClient findByClientId(String clientId) {
    return clientRepository.findByName(clientId)
        .map(this::toRegisteredClient)
        .orElseThrow();
  }


  private <T, U> Set<U> mapSet(Set<T> base, Function<T, U> mapper) {
    return base.stream().map(mapper).collect(Collectors.toSet());
  }

  private RegisteredClient toRegisteredClient(Client cl) {
    RegisteredClient client = RegisteredClient.withId(cl.getId().toString())
        .clientId(cl.getName())
        .clientSecret(cl.getSecret())
        .clientAuthenticationMethods(methods -> methods
            .addAll(mapSet(cl.getAuthenticationMethods(), (am) -> new ClientAuthenticationMethod(am.getValue())))
        )
        .authorizationGrantTypes(grantTypes -> grantTypes
            .addAll(mapSet(cl.getGrantTypes(), (g) -> new AuthorizationGrantType(g.getValue())))
        )
        .scopes(scope -> scope
            .addAll(mapSet(cl.getScopes(), Scope::getValue))
        )
        .redirectUris(uris -> uris
            .addAll(mapSet(cl.getRedirectUrls(), RedirectUrl::getValue))
        )
        .tokenSettings(TokenSettings.builder()
            .accessTokenFormat(new OAuth2TokenFormat(cl.getTokenSettings().getType()))
            .accessTokenTimeToLive(Duration.ofHours(cl.getTokenSettings().getAccessTokenTTL()))
            .build()
        )
        .build();

    return client;
  }

}
