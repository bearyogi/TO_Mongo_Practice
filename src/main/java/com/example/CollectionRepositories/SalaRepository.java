package com.example.CollectionRepositories;

import com.example.CollectionObjects.Sala;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends MongoRepository<Sala, Integer> {
}