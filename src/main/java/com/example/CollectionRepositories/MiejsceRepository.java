package com.example.CollectionRepositories;

import com.example.CollectionObjects.Miejsce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiejsceRepository extends MongoRepository<Miejsce, Integer> {
}