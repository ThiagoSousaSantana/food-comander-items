package com.foodcomander.items.models;

import com.foodcomander.items.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

  @Test
  void shouldFindAnItemById() {
    var uuid = randomUUID();
    var item =
        Item.builder()
            .flavors(
                asList(
                    Flavor.builder().id(uuid).build(),
                    Flavor.builder().id(randomUUID()).build(),
                    Flavor.builder().id(randomUUID()).build()))
            .build();

    var flavor = item.findFlavorById(uuid);

    assertThat(flavor, notNullValue());
    assertThat(flavor.getId(), is(uuid));
  }

  @Test
  void shouldThrowExceptionWhenFindAnItemById() {
    var item =
        Item.builder()
            .flavors(
                asList(
                    Flavor.builder().id(randomUUID()).build(),
                    Flavor.builder().id(randomUUID()).build()))
            .build();
    assertThrows(
        ObjectNotFoundException.class, () -> item.findFlavorById(randomUUID()), "Object not found");
  }

  @Test
  void shouldRemoveAFlavor() {
    var uuid = randomUUID();
    var flavor = Flavor.builder().id(uuid).build();
    var item =
        Item.builder()
            .flavors(
                new ArrayList<>(
                    asList(
                        flavor,
                        Flavor.builder().id(randomUUID()).build(),
                        Flavor.builder().id(randomUUID()).build())))
            .build();

    item.deleteFlavor(uuid);
    var favlorDelete = item.findFlavorById(uuid);

    assertThat(favlorDelete.getEnabled(), is(false));
    assertThat(favlorDelete, notNullValue());
  }

  @Test
  void shouldReturnAnUnmodifiableList() {
    var item =
        Item.builder()
            .addons(
                asList(
                    Addon.builder().id(randomUUID()).build(),
                    Addon.builder().id(randomUUID()).build()))
            .flavors(
                asList(
                    Flavor.builder().id(randomUUID()).build(),
                    Flavor.builder().id(randomUUID()).build()))
            .build();

    var flavors = item.getFlavors();
    var addons = item.getAddons();

    assertThrows(UnsupportedOperationException.class, () -> flavors.add(null));
    assertThrows(UnsupportedOperationException.class, () -> flavors.remove(1));

    assertThrows(UnsupportedOperationException.class, () -> addons.add(null));
    assertThrows(UnsupportedOperationException.class, () -> addons.remove(1));
  }

  @Test
  void shouldFindAddonById() {
    var uuid = randomUUID();
    var item =
        Item.builder()
            .addons(
                asList(
                    Addon.builder().id(uuid).build(),
                    Addon.builder().id(randomUUID()).build(),
                    Addon.builder().id(randomUUID()).build()))
            .build();

    var addon = item.findAddonById(uuid);
    assertThat(addon, notNullValue());
    assertThat(addon.getId(), is(uuid));
  }

  @Test
  void shouldRemoveAddon() {
    var uuid = randomUUID();
    var addon = Addon.builder().id(uuid).build();
    var item =
            Item.builder()
                    .addons(
                            new ArrayList<>(
                                    asList(
                                            addon,
                                            Addon.builder().id(randomUUID()).build(),
                                            Addon.builder().id(randomUUID()).build())))
                    .build();

    item.deleteAddon(uuid);
    var addonDelete = item.findAddonById(uuid);

    assertThat(addonDelete.getEnabled(), is(false));
    assertThat(addonDelete, notNullValue());
  }
}
