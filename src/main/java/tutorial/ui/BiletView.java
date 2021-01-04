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
import tutorial.entity.Bilet;
import tutorial.forms.BiletForm;
import tutorial.service.BiletService;
import tutorial.service.MiejsceService;
import tutorial.service.ZamowienieService;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Route(value = "bilet", layout = MainView.class)
@PageTitle("Bilety")
public class BiletView extends VerticalLayout {

    BiletForm form;
    Grid<Bilet> grid = new Grid<>(Bilet.class);
    TextField filterTextUlga = new TextField();
    TextField filterTextCena = new TextField();
    TextField filterTextZamowienie = new TextField();
    TextField filterTextMiejsce = new TextField();
    BiletService biletService;

    public BiletView(BiletService biletService, MiejsceService miejsceService, ZamowienieService zamowienieService) {
        this.biletService = biletService;
        addClassName("bilet-view");
        setSizeFull();
        configureGrid();


        form = new BiletForm(miejsceService.findAll(),zamowienieService.findAll());
        form.addListener(BiletForm.SaveEvent.class, this::saveBilet);
        form.addListener(BiletForm.DeleteEvent.class, this::deleteBilet);
        form.addListener(BiletForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteBilet(BiletForm.DeleteEvent evt) {
        biletService.delete(evt.getBilet());
        updateList();
        closeEditor();
    }

    private void saveBilet(BiletForm.SaveEvent evt) {
        biletService.save(evt.getBilet());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterTextUlga.setPlaceholder("Filtruj ulga");
        filterTextUlga.setClearButtonVisible(true);
        filterTextUlga.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextUlga.addValueChangeListener(e -> updateList());

        filterTextCena.setPlaceholder("Filtruj cena");
        filterTextCena.setClearButtonVisible(true);
        filterTextCena.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextCena.addValueChangeListener(e -> updateList());

        filterTextZamowienie.setPlaceholder("Filtruj zamowienie");
        filterTextZamowienie.setClearButtonVisible(true);
        filterTextZamowienie.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextZamowienie.addValueChangeListener(e -> updateList());

        filterTextMiejsce.setPlaceholder("Filtruj miejsce");
        filterTextMiejsce.setClearButtonVisible(true);
        filterTextMiejsce.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextMiejsce.addValueChangeListener(e -> updateList());

        Button addBiletButton = new Button("Dodaj bilet", click -> addBilet());
        Button closeFormButton = new Button("Zamknij formularz", click -> closeEditor());
        HorizontalLayout toolbar = new HorizontalLayout(filterTextUlga,filterTextCena,addBiletButton, closeFormButton);
        //filterTextZamowienie,filterTextMiejsce
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addBilet() {
        grid.asSingleSelect().clear();
        editBilet(new Bilet());
    }

    private void configureGrid() {
        grid.addClassName("bilet-grid");
        grid.setSizeFull();
        grid.setColumns("ulga", "cena","zamowienie","miejsce");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editBilet(evt.getValue()));
    }

    private void editBilet(Bilet bilet) {
        if (bilet == null) {
            closeEditor();
        } else {
            form.setBilet(bilet);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setBilet(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        List<Bilet> list = new ArrayList<>();
        list.addAll(biletService.findAll(filterTextUlga.getValue()));
        list.retainAll(biletService.findAllCena(filterTextCena.getValue()));
        list.retainAll(biletService.findAllZamowienie(filterTextZamowienie.getValue()));
        list.retainAll(biletService.findAllMiejsce(filterTextMiejsce.getValue()));
        grid.setItems(list);
    }
}
