package com.foodcomander.items.controller;

import com.foodcomander.items.Dto.AddonUpdate;
import com.foodcomander.items.Dto.FlavorUpdate;
import com.foodcomander.items.Dto.ItemUpdate;
import com.foodcomander.items.models.Addon;
import com.foodcomander.items.models.Flavor;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

  @Autowired private ItemService itemService;

  @PostMapping
  public ResponseEntity<Item> insert(@RequestBody Item item) {
    var itemInsert = itemService.insert(item);
    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(itemInsert.getId())
            .toUri();
    return ResponseEntity.created(uri).body(itemInsert);
  }

  @PostMapping("/flavor/{id}")
  public ResponseEntity<Item> insertFlavor(@PathVariable UUID id, @RequestBody Flavor flavor) {
    var newFlavor = itemService.insertFlavor(id, flavor);
    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newFlavor.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newFlavor);
  }

  @PostMapping("/addon/{id}")
  public ResponseEntity<Item> insertAddon(@PathVariable UUID id, @RequestBody Addon addon) {
    var newAddon = itemService.insertAddon(id, addon);
    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newAddon.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newAddon);
  }

  @GetMapping
  public ResponseEntity<List<Item>> findAll() {
    var listItem = itemService.findAllItem();
    return ResponseEntity.ok(listItem);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> findById(@PathVariable UUID id) {
    var item = itemService.itemFindById(id);
    return ResponseEntity.ok(item);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Item> updateItem(
      @PathVariable UUID id, @RequestBody ItemUpdate itemUpdate) {
    var item = itemService.updateItem(id, itemUpdate);
    return ResponseEntity.ok(item);
  }

  @PutMapping("/flavor/{id}/item/{itemId}")
  public ResponseEntity<Item> updateFlavor(
      @PathVariable UUID id, @PathVariable UUID itemId, @RequestBody FlavorUpdate flavorUpdate) {
    var flavor = itemService.updateFlavor(itemId, id, flavorUpdate);
    return ResponseEntity.ok(flavor);
  }

  @PutMapping("/addon/{id}/item/{itemId}")
  public ResponseEntity<Item> updateFlavor(
      @PathVariable UUID id, @PathVariable UUID itemId, @RequestBody AddonUpdate addonUpdate) {
    var addon = itemService.updateAddon(itemId, id, addonUpdate);
    return ResponseEntity.ok(addon);
  }
}
