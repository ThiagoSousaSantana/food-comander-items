package com.foodcomander.items.repositories;

import com.foodcomander.items.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends MongoRepository<Item, UUID> {

  List<Item> findAllByEnabledTrue();

  Optional<Item> findByIdAndEnabledTrue(UUID id);
}
