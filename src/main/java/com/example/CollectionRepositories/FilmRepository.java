package com.example.CollectionRepositories;

import com.example.CollectionObjects.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends MongoRepository<Film, Integer> {
}