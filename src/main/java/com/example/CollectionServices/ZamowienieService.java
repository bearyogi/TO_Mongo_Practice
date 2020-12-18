package com.example.CollectionServices;

import com.example.CollectionObjects.Zamowienie;
import com.example.CollectionRepositories.ZamowienieRepository;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;
import java.util.List;

@Service
public class ZamowienieService implements CrudListener<Zamowienie> {

    private final ZamowienieRepository repository;

    public ZamowienieService(ZamowienieRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Zamowienie> findAll() {
        return repository.findAll();
    }
    @Override
    public Zamowienie add(Zamowienie zamowienie) {
        return repository.save(zamowienie);
    }
    @Override
    public Zamowienie update(Zamowienie zamowienie) {
        return repository.save(zamowienie);
    }
    @Override
    public void delete(Zamowienie zamowienie){
        repository.delete(zamowienie);
    }

}