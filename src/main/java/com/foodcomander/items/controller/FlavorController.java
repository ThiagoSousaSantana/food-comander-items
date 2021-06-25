package com.foodcomander.items.controller;

import com.foodcomander.items.dto.FlavorUpdate;
import com.foodcomander.items.models.Flavor;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.services.FlavorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/flavor")
@Api(tags = "Flavor")
public class FlavorController {

  @Autowired private FlavorService flavorService;

  @ApiOperation(value = "Insert flavor")
  @PostMapping("/{id}")
  public ResponseEntity<Item> insertFlavor(@PathVariable UUID id, @RequestBody Flavor flavor) {
    var newFlavor = flavorService.insertFlavor(id, flavor);
    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newFlavor.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newFlavor);
  }

  @ApiOperation(value = "Update flavor")
  @PutMapping("/item/{itemId}")
  public ResponseEntity<Item> updateFlavor(
      @PathVariable UUID itemId, @RequestBody FlavorUpdate flavorUpdate) {
    var flavor = flavorService.updateFlavor(itemId, flavorUpdate);
    return ResponseEntity.ok(flavor);
  }

  @ApiOperation(value = "Delete flavor")
  @DeleteMapping("/{id}/item/{itemId}")
  public ResponseEntity<Void> deleteFlavor(@PathVariable UUID id, @PathVariable UUID itemId) {
    flavorService.deleteFlavor(itemId, id);
    return ResponseEntity.ok().build();
  }
}
