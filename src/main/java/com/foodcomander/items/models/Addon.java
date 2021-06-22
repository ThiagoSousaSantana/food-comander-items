package com.foodcomander.items.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "addon")
@AllArgsConstructor
public class Addon implements Serializable {

  @Id
  private UUID id;
  private String name;
  private String description;
  private String imageUrl;
  private Boolean enabled;
  private BigDecimal price;
  private List<Flavor> flavors;
}
