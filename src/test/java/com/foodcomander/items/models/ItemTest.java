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
        var item = Item.builder()
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
        var item = Item.builder()
                .flavors(
                        asList(
                                Flavor.builder().id(randomUUID()).build(),
                                Flavor.builder().id(randomUUID()).build()))
                .build();
        assertThrows(ObjectNotFoundException.class, () ->
                item.findFlavorById(randomUUID()), "Object not found");
    }

    @Test
    void shouldRemoveAFlavor() {
        var uuid = randomUUID();
        var flavor = Flavor.builder().id(uuid).build();
        var item = Item.builder()
                .flavors(
                        new ArrayList<>(asList(
                                flavor,
                                Flavor.builder().id(randomUUID()).build(),
                                Flavor.builder().id(randomUUID()).build())))
                .build();

        item.deleteFlavor(uuid);

        assertThat(item.getFlavors().size(), is(2));
        assertThat(item.getFlavors(), not(hasItem(flavor)));
    }

    @Test
    void shouldReturnAnUnmodifiableList() {
        var item = Item.builder()
                .flavors(
                        asList(
                                Flavor.builder().id(randomUUID()).build(),
                                Flavor.builder().id(randomUUID()).build()))
                .build();

        var flavors = item.getFlavors();

        assertThrows(
                UnsupportedOperationException.class,
                () -> flavors.add(null));

        assertThrows(
                UnsupportedOperationException.class,
                () -> flavors.remove(1));
    }
}
