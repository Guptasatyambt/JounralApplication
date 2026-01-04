package com.example.demoo.repository;

import com.example.demoo.entities.jounralEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface jounralRepository extends MongoRepository<jounralEntity, ObjectId> {
}
