package com.foodcomander.items.controller;

import com.foodcomander.items.models.Item;
import com.foodcomander.items.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
}
