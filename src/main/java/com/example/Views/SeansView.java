package com.example.Views;

import com.example.CollectionObjects.Seans;
import com.example.CollectionServices.SeansService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringBootApplication
@Route("seans")
@PreserveOnRefresh
public class SeansView extends VerticalLayout {

    public SeansView(SeansService service) {
        VerticalLayout content = new VerticalLayout();
        GridCrud<Seans> crud = new GridCrud<>(Seans.class, service);
        add(content);
        add(crud);
        setSizeFull();

    }
}
