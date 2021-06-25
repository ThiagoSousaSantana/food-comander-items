package com.foodcomander.items.controller;

import com.foodcomander.items.dto.ItemUpdate;
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

  @ApiOperation(value = "Delete item")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
    itemService.deleteItem(id);
    return ResponseEntity.ok().build();
  }
}
