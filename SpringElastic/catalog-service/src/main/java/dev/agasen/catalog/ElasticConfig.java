package dev.agasen.catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticConfig extends ElasticsearchConfiguration {

  private final String elasticsearchUrl;
  
  public ElasticConfig(@Value("${app.elasticsearch.url}") String elasticsearchUrl) {
    this.elasticsearchUrl = elasticsearchUrl;
  }

  @Override
  public ClientConfiguration clientConfiguration() {
    return ClientConfiguration.builder()
        .connectedTo(elasticsearchUrl)
        .build();
  }

}
