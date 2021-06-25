package com.foodcomander.items.services;

import com.foodcomander.items.dto.FlavorUpdate;
import com.foodcomander.items.models.Flavor;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlavorService {

  @Autowired private ItemRepository itemRepository;

  @Autowired private ItemService itemService;

  public Item insertFlavor(UUID idItem, Flavor flavor) {
    var item = itemService.itemFindById(idItem);
    flavor.setId(UUID.randomUUID());
    item.getFlavors().add(flavor);
    return itemRepository.save(item);
  }

  public Item updateFlavor(UUID idItem, FlavorUpdate flavorUpdate) {
    var item = itemService.itemFindById(idItem);
    item.updateFlavor(flavorUpdate);
    return itemRepository.save(item);
  }

  public void deleteFlavor(UUID idItem, UUID idFlavor) {
    var item = itemService.itemFindById(idItem);
    item.removeFlavorById(idFlavor);
    itemRepository.save(item);
  }
}
