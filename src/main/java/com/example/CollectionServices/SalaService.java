package com.example.CollectionServices;

import com.example.CollectionObjects.Sala;
import com.example.CollectionRepositories.SalaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.List;

@Service
public class SalaService implements CrudListener<Sala> {

    private final SalaRepository repository;

    public SalaService(SalaRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Sala> findAll() {
        return repository.findAll();
    }
    @Override
    public Sala add(Sala sala) {
        return repository.save(sala);
    }
    @Override
    public Sala update(Sala sala) {
        return repository.save(sala);
    }
    @Override
    public void delete(Sala sala){
        repository.delete(sala);
    }

}