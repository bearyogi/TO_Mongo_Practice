package com.example.CollectionServices;

import com.example.CollectionObjects.Miejsce;
import com.example.CollectionRepositories.MiejsceRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.List;

@Service
public class MiejsceService implements CrudListener<Miejsce> {

    private final MiejsceRepository repository;

    public MiejsceService(MiejsceRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Miejsce> findAll() {
        return repository.findAll();
    }
    @Override
    public Miejsce add(Miejsce miejsce) {
        return repository.save(miejsce);
    }
    @Override
    public Miejsce update(Miejsce miejsce) {
        return repository.save(miejsce);
    }
    @Override
    public void delete(Miejsce miejsce){
        repository.delete(miejsce);
    }

}