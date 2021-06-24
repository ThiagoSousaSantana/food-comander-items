package com.foodcomander.items.services;

import com.foodcomander.items.Dto.FlavorUpdate;
import com.foodcomander.items.Dto.ItemUpdate;
import com.foodcomander.items.exceptions.ObjectNotFoundException;
import com.foodcomander.items.models.Addon;
import com.foodcomander.items.models.Flavor;
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

  public Item itemFindById(UUID id){
    var item = itemRepository.findById(id);
    return item.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
  }

  public Item insertFlavor(UUID idItem, Flavor flavor){
    var item = itemFindById(idItem);
    flavor.setId(UUID.randomUUID());
    item.getFlavors().add(flavor);
    return itemRepository.save(item);
  }

  public Item insertAddon(UUID idItem, Addon addon){
    var item = itemFindById(idItem);
    addon.setId(UUID.randomUUID());
    item.getAddons().add(addon);
    return itemRepository.save(item);
  }

  public List<Item> findAllItem(){
    return itemRepository.findAll();
  }

  public Item updateItem (UUID id, ItemUpdate itemUpdate){
    var item = itemFindById(id);
    return itemRepository.save(new Item(itemUpdate, item));
  }

}
