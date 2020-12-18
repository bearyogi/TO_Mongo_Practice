package com.example.Views;

import com.example.CollectionObjects.Klient;
import com.example.CollectionServices.KlientService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.crudui.crud.impl.GridCrud;


@SpringBootApplication
@Route("klient")
@PreserveOnRefresh

public class KlientView extends VerticalLayout {

    public KlientView(KlientService service) {
        VerticalLayout content = new VerticalLayout();
        Text text = new Text("HELLOWORLD");
        content.add(text);
        GridCrud<Klient> crud = new GridCrud<>(Klient.class, service);
        add(content);
        add(crud);
        setSizeFull();

    }
}
