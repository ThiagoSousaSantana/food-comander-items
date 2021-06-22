package com.foodcomander.items.repositories;

import com.foodcomander.items.models.Flavor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FlavorRepository extends MongoRepository<Flavor, UUID> {}
