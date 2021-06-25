package com.foodcomander.items.services;

import com.foodcomander.items.dto.ItemUpdate;
import com.foodcomander.items.exceptions.ObjectNotFoundException;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    var itemOptional = itemRepository.findById(id);
    var item = itemOptional.orElseThrow(ObjectNotFoundException::new);
    if (item.getEnabled() == false) {
      throw new ObjectNotFoundException();
    }
    item.setFlavors(
        item.getFlavors().stream()
            .filter(flavor -> flavor.getEnabled().equals(true))
            .collect(Collectors.toList()));
    item.setAddons(
        item.getAddons().stream()
            .filter(addon -> addon.getEnabled().equals(true))
            .collect(Collectors.toList()));
    return item;
  }

  public List<Item> findAllItem() {
    var item = itemRepository.findAllByEnabledTrue();
    item.stream()
        .forEach(
            obj -> {
              obj.setFlavors(
                  obj.getFlavors().stream()
                      .filter(flavor -> flavor.getEnabled().equals(true))
                      .collect(Collectors.toList()));
              obj.setAddons(
                  obj.getAddons().stream()
                      .filter(addon -> addon.getEnabled().equals(true))
                      .collect(Collectors.toList()));
            });

    return item;
  }

  public Item updateItem(UUID id, ItemUpdate itemUpdate) {
    var item = itemFindById(id);
    return itemRepository.save(new Item(itemUpdate, item));
  }

  public void deleteItem(UUID id) {
    var item = itemFindById(id);
    item.setEnabled(false);
    itemRepository.save(item);
  }
}
