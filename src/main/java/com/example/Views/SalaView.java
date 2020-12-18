package com.example.Views;

import com.example.CollectionObjects.Sala;
import com.example.CollectionServices.SalaService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringBootApplication
@Route("sala")
@PreserveOnRefresh
public class SalaView extends VerticalLayout {

    public SalaView(SalaService service) {
        VerticalLayout content = new VerticalLayout();
        GridCrud<Sala> crud = new GridCrud<>(Sala.class, service);
        add(content);
        add(crud);
        setSizeFull();

    }
}
