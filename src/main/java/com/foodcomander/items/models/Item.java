package com.foodcomander.items.models;

import com.foodcomander.items.dto.ItemUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

  @Id
  private UUID id;
  private String name;
  private String description;
  private BigDecimal price;
  private String classification;
  private String size;
  private String imageUrl;
  private String rating;
  private Boolean enabled;
  private List<Flavor> flavors;
  private List<Addon> addons;


  public Item(ItemUpdate itemUpdate, Item item) {
    this.id = item.getId();
    this.name = itemUpdate.getName();
    this.description = itemUpdate.getDescription();
    this.price = itemUpdate.getPrice();
    this.classification = itemUpdate.getClassification();
    this.size = itemUpdate.getSize();
    this.imageUrl = itemUpdate.getImageUrl();
    this.rating = itemUpdate.getRating();
    this.enabled = itemUpdate.getEnabled();
    this.flavors = item.getFlavors();
    this.addons = item.getAddons();

  }


}
