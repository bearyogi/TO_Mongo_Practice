package com.example.CollectionServices;

import com.example.CollectionObjects.Klient;
import com.example.CollectionRepositories.KlientRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.List;

@Service
public class KlientService implements CrudListener<Klient> {

    private final KlientRepository repository;

    public KlientService(KlientRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Klient> findAll() {
     return repository.findAll();
    }
    @Override
    public Klient add(Klient klient) {
        return repository.save(klient);
    }
    @Override
    public Klient update(Klient klient) {
        return repository.save(klient);
    }
    @Override
    public void delete(Klient klient){
        repository.delete(klient);
    }

}
