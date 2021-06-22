package com.foodcomander.items.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Document(collection = "flavors")
@AllArgsConstructor
public class Flavor implements Serializable {

  @Id
  private UUID id;
  private String name;
  private String description;
  private Boolean enable;
  private BigDecimal price;
}
