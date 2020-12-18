package com.example.CollectionServices;

import com.example.CollectionObjects.Bilet;
import com.example.CollectionRepositories.BiletRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.List;

@Service
public class BiletService implements CrudListener<Bilet> {

    private final BiletRepository repository;

    public BiletService(BiletRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Bilet> findAll() {
        return repository.findAll();
    }
    @Override
    public Bilet add(Bilet bilet) {
        return repository.save(bilet);
    }
    @Override
    public Bilet update(Bilet bilet) {
        return repository.save(bilet);
    }
    @Override
    public void delete(Bilet bilet){ repository.delete(bilet); }

}
