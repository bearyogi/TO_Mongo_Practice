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
import tutorial.entity.Sala;
import tutorial.forms.SalaForm;
import tutorial.service.SalaService;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Route(value = "sala", layout = MainView.class)
@PageTitle("Sale")
public class SalaView extends VerticalLayout {

    SalaForm form;
    Grid<Sala> grid = new Grid<>(Sala.class);
    TextField filterTextNrSali = new TextField();
    TextField filterTextLokalizacja = new TextField();
    TextField filterTextKlasa = new TextField();

    SalaService salaService;

    public SalaView(SalaService salaService) {
        this.salaService = salaService;
        addClassName("bilet-view");
        setSizeFull();
        configureGrid();


        form = new SalaForm();
        form.addListener(SalaForm.SaveEvent.class, this::saveSala);
        form.addListener(SalaForm.DeleteEvent.class, this::deleteSala);
        form.addListener(SalaForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteSala(SalaForm.DeleteEvent evt) {
        salaService.delete(evt.getSala());
        updateList();
        closeEditor();
    }

    private void saveSala(SalaForm.SaveEvent evt) {
        salaService.save(evt.getSala());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterTextNrSali.setPlaceholder("Filtruj numer");
        filterTextNrSali.setClearButtonVisible(true);
        filterTextNrSali.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextNrSali.addValueChangeListener(e -> updateList());

        filterTextLokalizacja.setPlaceholder("Filtruj lokalizacja");
        filterTextLokalizacja.setClearButtonVisible(true);
        filterTextLokalizacja.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextLokalizacja.addValueChangeListener(e -> updateList());

        filterTextKlasa.setPlaceholder("Filtruj klasa");
        filterTextKlasa.setClearButtonVisible(true);
        filterTextKlasa.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextKlasa.addValueChangeListener(e -> updateList());

        Button addBiletButton = new Button("Dodaj sale", click -> addBilet());
        Button closeFormButton = new Button("Zamknij formularz", click -> closeEditor());
        HorizontalLayout toolbar = new HorizontalLayout(filterTextNrSali,filterTextLokalizacja,filterTextKlasa,addBiletButton, closeFormButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addBilet() {
        grid.asSingleSelect().clear();
        editBilet(new Sala());
    }

    private void configureGrid() {
        grid.addClassName("sala-grid");
        grid.setSizeFull();
        grid.setColumns("nrSali", "lokalizacja", "klasa");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editBilet(evt.getValue()));
    }

    private void editBilet(Sala sala) {
        if (sala == null) {
            closeEditor();
        } else {
            form.setSala(sala);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setSala(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        List<Sala> list = new ArrayList<>();
        list.addAll(salaService.findAllNrSali(filterTextNrSali.getValue()));
        list.retainAll(salaService.findAllLokalizacja(filterTextLokalizacja.getValue()));
        list.retainAll(salaService.findAllKlasa(filterTextKlasa.getValue()));
        grid.setItems(list);
    }
}
