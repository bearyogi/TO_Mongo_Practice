package com.example.CollectionRepositories;

import com.example.CollectionObjects.Seans;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeansRepository extends MongoRepository<Seans, Integer> {
}