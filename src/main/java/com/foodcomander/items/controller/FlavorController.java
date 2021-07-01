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

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/item/flavor")
@Api(tags = "Flavor")
public class FlavorController {

  @Autowired private FlavorService flavorService;

  @ApiOperation(value = "Insert flavor")
  @PostMapping
  public ResponseEntity<Item> insertFlavor(@RequestParam UUID itemId, @RequestBody Flavor flavor) {
    var newFlavor = flavorService.insertFlavor(itemId, flavor);
    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{idItem}")
            .buildAndExpand(newFlavor.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @ApiOperation(value = "Update flavor")
  @PutMapping
  public ResponseEntity<Item> updateFlavor(
          @RequestParam UUID itemId, @Valid @RequestBody FlavorUpdate flavorUpdate) {
    var flavor = flavorService.updateFlavor(itemId, flavorUpdate);
    return ResponseEntity.ok(flavor);
  }

  @ApiOperation(value = "Delete flavor")
  @DeleteMapping
  public ResponseEntity<Void> deleteFlavor(@RequestParam UUID flavorId, @RequestParam UUID itemId) {
    flavorService.deleteFlavor(itemId, flavorId);
    return ResponseEntity.ok().build();
  }
}
