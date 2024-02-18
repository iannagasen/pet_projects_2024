package dev.agasen.catalog.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import dev.agasen.catalog.document.Product;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
  
}
