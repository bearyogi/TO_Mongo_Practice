package com.example.CollectionRepositories;

import com.example.CollectionObjects.Bilet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiletRepository extends MongoRepository<Bilet, Integer> {
}