package com.example.Views;

import com.example.CollectionObjects.Bilet;
import com.example.CollectionServices.BiletService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringBootApplication
@Route("bilet")
@PreserveOnRefresh
public class BiletView extends VerticalLayout {

    public BiletView(BiletService service) {
        VerticalLayout content = new VerticalLayout();
        GridCrud<Bilet> crud = new GridCrud<>(Bilet.class, service);
        add(content);
        add(crud);
        setSizeFull();

    }
}