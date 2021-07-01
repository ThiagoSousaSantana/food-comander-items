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
@RequestMapping("/item/addon")
@Api(tags = "Addon")
public class AddonController {

  @Autowired private AddonService addonService;

  @ApiOperation(value = "Insert addon")
  @PostMapping
  public ResponseEntity<Item> insertAddon(@RequestParam UUID itemId, @RequestBody Addon addon) {
    var newAddon = addonService.insertAddon(itemId, addon);
    var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{itemId}")
            .buildAndExpand(newAddon.getId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @ApiOperation(value = "Update addon")
  @PutMapping
  public ResponseEntity<Item> updateAddon(@RequestParam UUID itemId,@Valid @RequestBody AddonUpdate addonUpdate) {
    var addon = addonService.updateAddon(itemId, addonUpdate);
    return ResponseEntity.ok(addon);
  }

  @ApiOperation(value = "Delete addon")
  @DeleteMapping
  public ResponseEntity<Void> deleteAddon(@RequestParam UUID addonId, @RequestParam UUID itemId) {
    addonService.deleteAddon(itemId, addonId);
    return ResponseEntity.ok().build();
  }
}
