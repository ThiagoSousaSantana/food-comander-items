package com.foodcomander.items.repositories;

import com.foodcomander.items.models.Addon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AddonRepository extends MongoRepository<Addon, UUID> {}
