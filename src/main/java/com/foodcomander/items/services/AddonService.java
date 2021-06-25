package com.foodcomander.items.services;

import com.foodcomander.items.dto.AddonUpdate;
import com.foodcomander.items.exceptions.ObjectNotFoundException;
import com.foodcomander.items.models.Addon;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddonService {

  @Autowired private ItemRepository itemRepository;

  @Autowired private ItemService itemService;

  public Item insertAddon(UUID idItem, Addon addon) {
    var item = itemService.itemFindById(idItem);
    addon.setId(UUID.randomUUID());
    item.getAddons().add(addon);
    return itemRepository.save(item);
  }

  public Item updateAddon(UUID idItem, UUID idAddon, AddonUpdate addonUpdate) {
    var item = itemService.itemFindById(idItem);
    var addon = addonFindById(item, idAddon);
    var newAddon = new Addon(idAddon, addonUpdate);
    item.getAddons().remove(addon);
    item.getAddons().add(newAddon);
    return itemRepository.save(item);
  }

  public void deleteAddon(UUID idItem, UUID idAddon) {
    var item = itemService.itemFindById(idItem);
    var addon = addonFindById(item, idAddon);
    item.getAddons().remove(addon);
    itemRepository.save(item);
  }

  private Addon addonFindById(Item item, UUID idAddon) {
    return item.getAddons().stream()
        .filter(obj -> obj.getId().equals(idAddon))
        .findFirst()
        .orElseThrow(ObjectNotFoundException::new);
  }
}
