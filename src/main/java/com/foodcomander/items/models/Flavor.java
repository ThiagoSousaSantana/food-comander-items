package com.foodcomander.items.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Flavor implements Serializable {

  @Id
  private UUID id;
  private String name;
  private String description;
  private Boolean enabled;
  private BigDecimal price;
}
