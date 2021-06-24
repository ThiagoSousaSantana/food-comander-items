package com.foodcomander.items.models;

import com.foodcomander.items.dto.FlavorUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flavor implements Serializable {

  @Id
  private UUID id;
  private String name;
  private String description;
  private Boolean enabled;
  private BigDecimal price;

  public Flavor(Flavor flavor, FlavorUpdate flavorUpdate) {

    this.id = flavor.getId();
    this.name = flavorUpdate.getName();
    this.description = flavorUpdate.getDescription();
    this.enabled = flavorUpdate.getEnabled();
    this.price = flavorUpdate.getPrice();
  }
}
