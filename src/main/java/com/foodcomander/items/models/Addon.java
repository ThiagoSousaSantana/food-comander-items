package com.foodcomander.items.models;

import com.foodcomander.items.dto.AddonUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Addon implements Serializable {

  @Id
  private UUID id;
  private String name;
  private String description;
  private String imageUrl;
  private Boolean enabled;
  private BigDecimal price;

  public Addon(Addon addon, AddonUpdate addonUpdate) {
    this.id = addon.getId();
    this.name = addonUpdate.getName();
    this.description = addonUpdate.getDescription();
    this.imageUrl = addonUpdate.getImageUrl();
    this.enabled = addonUpdate.getEnabled();
    this.price = addonUpdate.getPrice();
  }

}
