package com.example.Views;

import com.example.CollectionObjects.Film;
import com.example.CollectionServices.FilmService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringBootApplication
@Route("film")
@PreserveOnRefresh
public class FilmView extends VerticalLayout {

    public FilmView(FilmService service) {
        VerticalLayout content = new VerticalLayout();
        GridCrud<Film> crud = new GridCrud<>(Film.class, service);
        add(content);
        add(crud);
        setSizeFull();

    }
}