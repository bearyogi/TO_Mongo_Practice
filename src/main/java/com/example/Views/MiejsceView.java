package com.example.Views;

import com.example.CollectionObjects.Miejsce;
import com.example.CollectionServices.MiejsceService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringBootApplication
@Route("miejsce")
@PreserveOnRefresh
public class MiejsceView extends VerticalLayout {

    public MiejsceView(MiejsceService service) {
        VerticalLayout content = new VerticalLayout();
        GridCrud<Miejsce> crud = new GridCrud<>(Miejsce.class, service);
        add(content);
        add(crud);
        setSizeFull();

    }
}
