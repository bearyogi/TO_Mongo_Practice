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
import tutorial.entity.Zamowienie;
import tutorial.forms.ZamowienieForm;
import tutorial.service.KlientService;
import tutorial.service.SeansService;
import tutorial.service.ZamowienieService;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Route(value = "zamowienie", layout = MainView.class)
@PageTitle("Zamówienia")
public class ZamowienieView extends VerticalLayout {

    ZamowienieForm form;
    Grid<Zamowienie> grid = new Grid<>(Zamowienie.class);
    TextField filterTextTypPlatnosci = new TextField();
    TextField filterTextStatus = new TextField();
    TextField filterTextLiczbaBiletow = new TextField();
    TextField filterTextKwota = new TextField();
    TextField filterTextKlient = new TextField();
    TextField filterTextSeans = new TextField();
    ZamowienieService zamowienieService;

    public ZamowienieView(ZamowienieService zamowienieService, SeansService seansService, KlientService klientService) {
        this.zamowienieService = zamowienieService;
        addClassName("seans-view");
        setSizeFull();
        configureGrid();


        form = new ZamowienieForm(klientService.findAll(),seansService.findAll());
        form.addListener(ZamowienieForm.SaveEvent.class, this::saveZamowienie);
        form.addListener(ZamowienieForm.DeleteEvent.class, this::deleteZamowienie);
        form.addListener(ZamowienieForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteZamowienie(ZamowienieForm.DeleteEvent evt) {
        zamowienieService.delete(evt.getSeans());
        updateList();
        closeEditor();
    }

    private void saveZamowienie(ZamowienieForm.SaveEvent evt) {
        zamowienieService.save(evt.getSeans());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterTextTypPlatnosci.setPlaceholder("Filtruj typ płatnosci");
        filterTextTypPlatnosci.setClearButtonVisible(true);
        filterTextTypPlatnosci.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextTypPlatnosci.addValueChangeListener(e -> updateList());

        filterTextStatus.setPlaceholder("Filtruj status");
        filterTextStatus.setClearButtonVisible(true);
        filterTextStatus.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextStatus.addValueChangeListener(e -> updateList());

        filterTextLiczbaBiletow.setPlaceholder("Filtruj liczba biletow");
        filterTextLiczbaBiletow.setClearButtonVisible(true);
        filterTextLiczbaBiletow.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextLiczbaBiletow.addValueChangeListener(e -> updateList());

        filterTextKwota.setPlaceholder("Filtruj kwota");
        filterTextKwota.setClearButtonVisible(true);
        filterTextKwota.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextKwota.addValueChangeListener(e -> updateList());

        filterTextKlient.setPlaceholder("Filtruj klient");
        filterTextKlient.setClearButtonVisible(true);
        filterTextKlient.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextKlient.addValueChangeListener(e -> updateList());

        filterTextSeans.setPlaceholder("Filtruj seans");
        filterTextSeans.setClearButtonVisible(true);
        filterTextSeans.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextSeans.addValueChangeListener(e -> updateList());

        Button addSeansButton = new Button("Dodaj zamowienie", click -> addZamowienie());
        Button closeFormButton = new Button("Zamknij formularz", click -> closeEditor());
        HorizontalLayout toolbar = new HorizontalLayout(filterTextTypPlatnosci,filterTextStatus,filterTextLiczbaBiletow,filterTextKwota,addSeansButton, closeFormButton);
        //filtertextSeans,Klient
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addZamowienie() {
        grid.asSingleSelect().clear();
        editZamowienie(new Zamowienie());
    }

    private void configureGrid() {
        grid.addClassName("zamowienie-grid");
        grid.setSizeFull();
        grid.setColumns("typPlatnosci", "status","liczbaBiletow","kwota","klient","seans");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editZamowienie(evt.getValue()));
    }

    private void editZamowienie(Zamowienie zamowienie) {
        if (zamowienie == null) {
            closeEditor();
        } else {
            form.setZamowienie(zamowienie);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setZamowienie(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        List<Zamowienie> list = new ArrayList<>();
        list.addAll(zamowienieService.findAllTypPlatnosci(filterTextTypPlatnosci.getValue()));
        list.retainAll(zamowienieService.findAllStatus(filterTextStatus.getValue()));
        list.retainAll(zamowienieService.findAllLiczbaBiletow(filterTextLiczbaBiletow.getValue()));
        list.retainAll(zamowienieService.findAllKwota(filterTextKwota.getValue()));
        list.retainAll(zamowienieService.findAllKlient(filterTextKlient.getValue()));
        list.retainAll(zamowienieService.findAllSeans(filterTextSeans.getValue()));
        grid.setItems(list);
    }
}
