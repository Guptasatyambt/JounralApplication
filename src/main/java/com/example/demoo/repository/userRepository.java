package com.example.demoo.repository;

import com.example.demoo.entities.userEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface userRepository extends MongoRepository<userEntity, ObjectId> {
    public Optional<userEntity> findByEmail(String email);
}
