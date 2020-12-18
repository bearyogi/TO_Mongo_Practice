package com.example.CollectionServices;

import com.example.CollectionObjects.Film;
import com.example.CollectionRepositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.List;

@Service
public class FilmService implements CrudListener<Film> {

    private final FilmRepository repository;

    public FilmService(FilmRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Film> findAll() {
        return repository.findAll();
    }
    @Override
    public Film add(Film film) {
        return repository.save(film);
    }
    @Override
    public Film update(Film film) {
        return repository.save(film);
    }
    @Override
    public void delete(Film film){
        repository.delete(film);
    }

}