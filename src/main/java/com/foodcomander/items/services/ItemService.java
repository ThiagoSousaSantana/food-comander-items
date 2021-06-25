package com.foodcomander.items.services;

import com.foodcomander.items.dto.ItemUpdate;
import com.foodcomander.items.exceptions.ObjectNotFoundException;
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
    return item.orElseThrow(ObjectNotFoundException::new);
  }

  public List<Item> findAllItem() {
    return itemRepository.findAll();
  }

  public Item updateItem(UUID id, ItemUpdate itemUpdate) {
    var item = itemFindById(id);
    return itemRepository.save(new Item(itemUpdate, item));
  }

  public void deleteItem(UUID id) {
    var item = itemFindById(id);
    itemRepository.delete(item);
  }
}
