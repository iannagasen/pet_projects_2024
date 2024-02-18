

### Spring Data Elasticsearch will generate an index when you define/extend a `ElasticsearchRepository<T, U>`
## Sample:
localhost:9200/products

```java
@Document(indexName = "products")
public class Product {
  @Id
  @Field(type = FieldType.KEYWORD)
  private String id

  // ... other fields
}


public interface ProductRepository extends ElasticsearchRepository<Product, String> {
}
```


### To delete all indices:
- Run [delete_all_index.bash](delete_all_index.bash)