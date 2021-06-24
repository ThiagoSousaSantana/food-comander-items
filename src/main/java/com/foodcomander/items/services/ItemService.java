package com.foodcomander.items.services;

import com.foodcomander.items.dto.AddonUpdate;
import com.foodcomander.items.dto.FlavorUpdate;
import com.foodcomander.items.dto.ItemUpdate;
import com.foodcomander.items.exceptions.ObjectNotFoundException;
import com.foodcomander.items.models.Addon;
import com.foodcomander.items.models.Flavor;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

  @Autowired private ItemRepository itemRepository;

  public Item insert(Item item) {
    item.setId(UUID.randomUUID());
    item.getFlavors().forEach(obj -> obj.setId(UUID.randomUUID()));
    item.getAddons().forEach(obj -> obj.setId(UUID.randomUUID()));
    return itemRepository.save(item);
  }

  public Item itemFindById(UUID id) {
    var item = itemRepository.findById(id);
    return item.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
  }

  public Item insertFlavor(UUID idItem, Flavor flavor) {
    var item = itemFindById(idItem);
    flavor.setId(UUID.randomUUID());
    item.getFlavors().add(flavor);
    return itemRepository.save(item);
  }

  public Item insertAddon(UUID idItem, Addon addon) {
    var item = itemFindById(idItem);
    addon.setId(UUID.randomUUID());
    item.getAddons().add(addon);
    return itemRepository.save(item);
  }

  public List<Item> findAllItem() {
    return itemRepository.findAll();
  }

  public Item updateItem(UUID id, ItemUpdate itemUpdate) {
    var item = itemFindById(id);
    return itemRepository.save(new Item(itemUpdate, item));
  }

  public Item updateFlavor(UUID idItem, UUID idFlavor, FlavorUpdate flavorUpdate) {
    var item = itemFindById(idItem);
    var flavor = flavorFindByID(item, idFlavor);
    var newFlavor = new Flavor(flavor, flavorUpdate);
    item.getFlavors().remove(flavor);
    item.getFlavors().add(newFlavor);
    return itemRepository.save(item);
  }

  public Item updateAddon(UUID idItem, UUID idAddon, AddonUpdate addonUpdate) {
    var item = itemFindById(idItem);
    var addon = addonFindById(item, idAddon);
    var newAddon = new Addon(addon, addonUpdate);
    item.getAddons().remove(addon);
    item.getAddons().add(newAddon);
    return itemRepository.save(item);
  }

  public void deleteItem(UUID id) {
    var item = itemFindById(id);
    itemRepository.delete(item);
  }

  public void deleteFlavor(UUID idItem, UUID idFlavor) {
    var item = itemFindById(idItem);
    var flavor = flavorFindByID(item, idFlavor);
    item.getFlavors().remove(flavor);
    itemRepository.save(item);
  }

  public void deleteAddon(UUID idItem, UUID idAddon) {
    var item = itemFindById(idItem);
    var addon = addonFindById(item, idAddon);
    item.getAddons().remove(addon);
    itemRepository.save(item);
  }

  private Addon addonFindById(Item item, UUID idAddon){
    return item.getAddons().stream()
            .filter(obj -> obj.getId().equals(idAddon))
            .findFirst()
            .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
  }

  private Flavor flavorFindByID(Item item, UUID idFlavor){
    return item.getFlavors().stream()
            .filter(obj -> obj.getId().equals(idFlavor))
            .findFirst()
            .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
  }
}
