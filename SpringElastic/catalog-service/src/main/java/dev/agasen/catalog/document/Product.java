package dev.agasen.catalog.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "products")
public record Product(
  
  @Id 
  @Field(type = FieldType.Keyword)
  String id,

  @Field(type = FieldType.Keyword)
  String name,

  @Field(type = FieldType.Double)
  double price

) {
  
}
