package com.example.CollectionRepositories;

import com.example.CollectionObjects.Klient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlientRepository extends MongoRepository<Klient, Integer> {
}
