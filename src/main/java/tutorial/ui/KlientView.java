package tutorial.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import tutorial.MainView;
import tutorial.entity.Klient;
import tutorial.forms.KlientForm;
import tutorial.service.KlientService;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Route(value = "klient", layout = MainView.class)
@PageTitle("Klienci")
public class KlientView extends VerticalLayout {

    KlientForm form;
    Grid<Klient> grid = new Grid<>(Klient.class);
    TextField filterText = new TextField();
    TextField filterTextImie = new TextField();
    TextField filterTextAdres = new TextField();
    TextField filterTextEmail = new TextField();
    KlientService klientService;

    public KlientView(KlientService klientService) {
        this.klientService = klientService;
        addClassName("klient-view");
        setSizeFull();
        configureGrid();


        form = new KlientForm();
        form.addListener(KlientForm.SaveEvent.class, this::saveKlient);
        form.addListener(KlientForm.DeleteEvent.class, this::deleteKlient);
        form.addListener(KlientForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteKlient(KlientForm.DeleteEvent evt) {
        klientService.delete(evt.getKlient());
        updateList();
        closeEditor();
    }

    private void saveKlient(KlientForm.SaveEvent evt) {
        klientService.save(evt.getKlient());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filtruj nazwisko");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        filterTextImie.setPlaceholder("Filtruj imie");
        filterTextImie.setClearButtonVisible(true);
        filterTextImie.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextImie.addValueChangeListener(e -> updateList());

        filterTextAdres.setPlaceholder("Filtruj Adres");
        filterTextAdres.setClearButtonVisible(true);
        filterTextAdres.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextAdres.addValueChangeListener(e -> updateList());

        filterTextEmail.setPlaceholder("Filtruj Email");
        filterTextEmail.setClearButtonVisible(true);
        filterTextEmail.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextEmail.addValueChangeListener(e -> updateList());

        Button addKlientButton = new Button("Dodaj klienta", click -> addKlient());
        Button closeFormButton = new Button("Zamknij formularz", click -> closeEditor());
        HorizontalLayout toolbar = new HorizontalLayout(filterText,filterTextImie,filterTextAdres,filterTextEmail, addKlientButton, closeFormButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addKlient() {
        grid.asSingleSelect().clear();
        editKlient(new Klient());
    }

    private void configureGrid() {
        grid.addClassName("klient-grid");
        grid.setSizeFull();
        grid.setColumns("imie", "nazwisko", "email", "tel","adres");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editKlient(evt.getValue()));
    }

    private void editKlient(Klient klient) {
        if (klient == null) {
            closeEditor();
        } else {
            form.setKlient(klient);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setKlient(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        List<Klient> list = new ArrayList<>();
        list.addAll(klientService.findAll(filterText.getValue()));
        list.retainAll(klientService.findAllImie(filterTextImie.getValue()));
        list.retainAll(klientService.findAllAdres(filterTextAdres.getValue()));
        list.retainAll(klientService.findAllEmail(filterTextEmail.getValue()));
        grid.setItems(list);
    }
}
