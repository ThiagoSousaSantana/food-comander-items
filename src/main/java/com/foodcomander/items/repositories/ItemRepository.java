package com.foodcomander.items.repositories;

import com.foodcomander.items.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ItemRepository extends MongoRepository<Item, UUID> {}
