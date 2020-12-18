package com.example.Views;


import com.example.CollectionObjects.Zamowienie;
import com.example.CollectionServices.ZamowienieService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringBootApplication
@Route("zamowienie")
@PreserveOnRefresh
public class ZamowienieView extends VerticalLayout {

    public ZamowienieView(ZamowienieService service) {
        VerticalLayout content = new VerticalLayout();
        GridCrud<Zamowienie> crud = new GridCrud<>(Zamowienie.class, service);
        add(content);
        add(crud);
        setSizeFull();

    }
}
