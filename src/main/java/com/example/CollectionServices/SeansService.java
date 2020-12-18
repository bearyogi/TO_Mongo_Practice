package com.example.CollectionServices;

import com.example.CollectionObjects.Seans;
import com.example.CollectionRepositories.SeansRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.List;

@Service
public class SeansService implements CrudListener<Seans> {

    private final SeansRepository repository;

    public SeansService(SeansRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Seans> findAll() {
        return repository.findAll();
    }
    @Override
    public Seans add(Seans seans) {
        return repository.save(seans);
    }
    @Override
    public Seans update(Seans seans) {
        return repository.save(seans);
    }
    @Override
    public void delete(Seans seans){
        repository.delete(seans);
    }

}