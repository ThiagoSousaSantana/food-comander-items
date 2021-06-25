package com.foodcomander.items.models;

import com.foodcomander.items.dto.AddonUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Addon implements Serializable {

  @Id private UUID id;
  private String name;
  private String description;
  private String imageUrl;
  private Boolean enabled;
  private BigDecimal price;

  public Addon(AddonUpdate addonUpdate) {
    this.id = addonUpdate.getId();
    this.name = addonUpdate.getName();
    this.description = addonUpdate.getDescription();
    this.imageUrl = addonUpdate.getImageUrl();
    this.enabled = addonUpdate.getEnabled();
    this.price = addonUpdate.getPrice();
  }
}
