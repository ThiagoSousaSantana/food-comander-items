package com.foodcomander.items.controller;

import com.foodcomander.items.dto.AddonUpdate;
import com.foodcomander.items.dto.FlavorUpdate;
import com.foodcomander.items.dto.ItemUpdate;
import com.foodcomander.items.models.Addon;
import com.foodcomander.items.models.Flavor;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.services.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/item")
@Api(tags = "Item")
public class ItemController {

  @Autowired private ItemService itemService;

  @ApiOperation(value = "Insert Item complet")
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

  @ApiOperation(value = "Insert flavor")
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

  @ApiOperation(value = "Insert addon")
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

  @ApiOperation(value = "Find all items")
  @GetMapping
  public ResponseEntity<List<Item>> findAll() {
    var listItem = itemService.findAllItem();
    return ResponseEntity.ok(listItem);
  }

  @ApiOperation(value = "Find item by id")
  @GetMapping("/{id}")
  public ResponseEntity<Item> findById(@PathVariable UUID id) {
    var item = itemService.itemFindById(id);
    return ResponseEntity.ok(item);
  }

  @ApiOperation(value = "Update item")
  @PutMapping("/{id}")
  public ResponseEntity<Item> updateItem(
      @PathVariable UUID id, @RequestBody ItemUpdate itemUpdate) {
    var item = itemService.updateItem(id, itemUpdate);
    return ResponseEntity.ok(item);
  }

  @ApiOperation(value = "Update flavor")
  @PutMapping("/flavor/{id}/item/{itemId}")
  public ResponseEntity<Item> updateFlavor(
      @PathVariable UUID id, @PathVariable UUID itemId, @RequestBody FlavorUpdate flavorUpdate) {
    var flavor = itemService.updateFlavor(itemId, id, flavorUpdate);
    return ResponseEntity.ok(flavor);
  }

  @ApiOperation(value = "Update addon")
  @PutMapping("/addon/{id}/item/{itemId}")
  public ResponseEntity<Item> updateAddon(
      @PathVariable UUID id, @PathVariable UUID itemId, @RequestBody AddonUpdate addonUpdate) {
    var addon = itemService.updateAddon(itemId, id, addonUpdate);
    return ResponseEntity.ok(addon);
  }

  @ApiOperation(value = "Delete item")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
    itemService.deleteItem(id);
    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Delete flavor")
  @DeleteMapping("/flavor/{id}/item/{itemId}")
  public ResponseEntity<Void> deleteFlavor(@PathVariable UUID id, @PathVariable UUID itemId) {
    itemService.deleteFlavor(itemId, id);
    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Delete addon")
  @DeleteMapping("/addon/{id}/item/{itemId}")
  public ResponseEntity<Void> deleteAddon(@PathVariable UUID id, @PathVariable UUID itemId) {
    itemService.deleteAddon(itemId, id);
    return ResponseEntity.ok().build();
  }
}
