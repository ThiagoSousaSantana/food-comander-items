package com.foodcomander.items.models;

import com.foodcomander.items.dto.AddonUpdate;
import com.foodcomander.items.dto.FlavorUpdate;
import com.foodcomander.items.dto.ItemUpdate;
import com.foodcomander.items.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Collections.unmodifiableList;

@Data
@Builder
@Document(collection = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

  @Id private UUID id;
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

  public Flavor findFlavorById(UUID idFlavor) {
    return this.flavors.stream()
        .filter(obj -> obj.getId().equals(idFlavor))
        .findFirst()
        .orElseThrow(ObjectNotFoundException::new);
  }

  public void insertFlavor(Flavor flavor) {
    this.flavors.add(flavor);
  }

  public void deleteFlavor(UUID idFlavor) {
    var flavor = findFlavorById(idFlavor);
    flavor.setEnabled(false);
    this.flavors.remove(flavor);
    this.flavors.add(flavor);
  }

  public void updateFlavor(FlavorUpdate flavorUpdate) {
    var foundFlavor = false;

    for (Flavor flavor : this.flavors) {
      if (flavor.getId().equals(flavorUpdate.getId())) {
        foundFlavor = true;
        flavor.update(flavorUpdate);
        break;
      }
    }

    if (!foundFlavor) {
      throw new ObjectNotFoundException();
    }
  }

  public Addon findAddonById(UUID idAddon) {
    return this.getAddons().stream()
            .filter(obj -> obj.getId().equals(idAddon))
            .findFirst()
            .orElseThrow(ObjectNotFoundException::new);
  }

  public void insertAddons(Addon addon) {
    this.addons.add(addon);
  }

  public void updateAddon(AddonUpdate addonUpdate) {
    var addon = findAddonById(addonUpdate.getId());

    this.addons.remove(addon);
    var newAddon = new Addon(addonUpdate);
    this.addons.add(newAddon);
  }

  public void deleteAddon(UUID idAddon) {
    this.addons.forEach(addon -> {
      if (addon.getId().equals(idAddon))
        addon.setEnabled(false);
    });
  }

  public void update(ItemUpdate itemUpdate) {
    this.name = itemUpdate.getName();
    this.description = itemUpdate.getDescription();
    this.price = itemUpdate.getPrice();
    this.classification = itemUpdate.getClassification();
    this.size = itemUpdate.getSize();
    this.imageUrl = itemUpdate.getImageUrl();
    this.rating = itemUpdate.getRating();
    this.enabled = itemUpdate.getEnabled();
  }

  public List<Flavor> getFlavors() {
    return unmodifiableList(this.flavors);
  }

  public List<Addon> getAddons() {
    return unmodifiableList(this.addons);
  }
}
