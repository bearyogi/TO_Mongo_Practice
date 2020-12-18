package com.example.CollectionRepositories;

import com.example.CollectionObjects.Zamowienie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZamowienieRepository extends MongoRepository<Zamowienie, Integer> {
}