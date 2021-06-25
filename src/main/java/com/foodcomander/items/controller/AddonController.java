package com.foodcomander.items.controller;

import com.foodcomander.items.dto.AddonUpdate;
import com.foodcomander.items.models.Addon;
import com.foodcomander.items.models.Item;
import com.foodcomander.items.services.AddonService;
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
@RequestMapping("/addon")
@Api(tags = "Addon")
public class AddonController {

  @Autowired private AddonService addonService;

  @ApiOperation(value = "Insert addon")
  @PostMapping("/item/{itemId}")
  public ResponseEntity<Item> insertAddon(@PathVariable UUID itemId, @RequestBody Addon addon) {
    var newAddon = addonService.insertAddon(itemId, addon);
    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{itemId}")
            .buildAndExpand(newAddon.getId())
            .toUri();
    return ResponseEntity.created(uri).body(newAddon);
  }

  @ApiOperation(value = "Update addon")
  @PutMapping("item/{itemId}")
  public ResponseEntity<Item> updateAddon(@PathVariable UUID itemId,@Valid @RequestBody AddonUpdate addonUpdate) {
    var addon = addonService.updateAddon(itemId, addonUpdate);
    return ResponseEntity.ok(addon);
  }

  @ApiOperation(value = "Delete addon")
  @DeleteMapping("/{id}/item/{itemId}")
  public ResponseEntity<Void> deleteAddon(@PathVariable UUID id, @PathVariable UUID itemId) {
    addonService.deleteAddon(itemId, id);
    return ResponseEntity.ok().build();
  }
}
