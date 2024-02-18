package dev.agasen.catalog;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import dev.agasen.catalog.document.Indices;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class IndexService {
  private final List<String> INDICIES_TO_CREATE = List.of(Indices.PRODUCTS, "testing_11");
  private final ElasticsearchClient client;

  private final String fileFormat = "static/mappings/%s.json";

  public IndexService(ElasticsearchClient client) {
    this.client = client;
  }

  @PostConstruct
  public void tryToCreateIndices() {
    InputStream settings = loadAsInputStream("static/elasticsearch-settings.json");

    for (String index : INDICIES_TO_CREATE) {
      try {
        boolean indexExists = client
            .indices()
            .exists(e -> e.index(index).includeDefaults(true))
            .value();

        if (indexExists) continue;

        InputStream mappings = loadAsInputStream(fileFormat.formatted(index));
        if (settings == null || mappings == null) {
          log.error("Failed to create index with name '{}'", index);
          continue;
        }

        client.indices()
            .create(c -> c
                .index(index)
                .settings(s -> s.withJson(settings))
                .mappings(m -> m.withJson(mappings))
            );

      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
  }

  private InputStream loadAsInputStream(String path) {
    try {
      return new ClassPathResource(path).getInputStream();
    } catch (IOException e) {
      return null;
    }
  }


}
