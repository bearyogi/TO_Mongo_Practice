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
import tutorial.entity.Seans;
import tutorial.forms.SeansForm;
import tutorial.service.FilmService;
import tutorial.service.SalaService;
import tutorial.service.SeansService;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Route(value = "seans", layout = MainView.class)
@PageTitle("Seanse")
public class SeansView extends VerticalLayout {

    SeansForm form;
    Grid<Seans> grid = new Grid<>(Seans.class);
    TextField filterTextLektor = new TextField();
    TextField filterTextNapisy = new TextField();
    TextField filterTextData = new TextField();
    TextField filterTextGodzina = new TextField();
    TextField filterTextSala = new TextField();
    TextField filterTextFilm = new TextField();
    SeansService seansService;

    public SeansView(SeansService seansService, SalaService salaService, FilmService filmService) {
        this.seansService = seansService;
        addClassName("seans-view");
        setSizeFull();
        configureGrid();


        form = new SeansForm(filmService.findAll(),salaService.findAll());
        form.addListener(SeansForm.SaveEvent.class, this::saveSeans);
        form.addListener(SeansForm.DeleteEvent.class, this::deleteSeans);
        form.addListener(SeansForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteSeans(SeansForm.DeleteEvent evt) {
        seansService.delete(evt.getSeans());
        updateList();
        closeEditor();
    }

    private void saveSeans(SeansForm.SaveEvent evt) {
        seansService.save(evt.getSeans());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterTextLektor.setPlaceholder("Filtruj lektor");
        filterTextLektor.setClearButtonVisible(true);
        filterTextLektor.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextLektor.addValueChangeListener(e -> updateList());

        filterTextNapisy.setPlaceholder("Filtruj napisy");
        filterTextNapisy.setClearButtonVisible(true);
        filterTextNapisy.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextNapisy.addValueChangeListener(e -> updateList());

        filterTextData.setPlaceholder("Filtruj data");
        filterTextData.setClearButtonVisible(true);
        filterTextData.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextData.addValueChangeListener(e -> updateList());

        filterTextGodzina.setPlaceholder("Filtruj godzina");
        filterTextGodzina.setClearButtonVisible(true);
        filterTextGodzina.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextGodzina.addValueChangeListener(e -> updateList());

        filterTextSala.setPlaceholder("Filtruj sale");
        filterTextSala.setClearButtonVisible(true);
        filterTextSala.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextSala.addValueChangeListener(e -> updateList());

        filterTextFilm.setPlaceholder("Filtruj film");
        filterTextFilm.setClearButtonVisible(true);
        filterTextFilm.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextFilm.addValueChangeListener(e -> updateList());

        Button addSeansButton = new Button("Dodaj seans", click -> addSeans());
        Button closeFormButton = new Button("Zamknij formularz", click -> closeEditor());
        HorizontalLayout toolbar = new HorizontalLayout(filterTextLektor,filterTextNapisy,filterTextData,filterTextGodzina,filterTextSala,filterTextFilm,addSeansButton, closeFormButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addSeans() {
        grid.asSingleSelect().clear();
        editSeans(new Seans());
    }

    private void configureGrid() {
        grid.addClassName("seans-grid");
        grid.setSizeFull();
        grid.setColumns("lektor", "napisy","data","godzina","sala","film");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editSeans(evt.getValue()));
    }

    private void editSeans(Seans seans) {
        if (seans == null) {
            closeEditor();
        } else {
            form.setSeans(seans);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setSeans(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        List<Seans> list = new ArrayList<>();
        list.addAll(seansService.findAllLektor(filterTextLektor.getValue()));
        list.retainAll(seansService.findAllNapisy(filterTextNapisy.getValue()));
        list.retainAll(seansService.findAllData(filterTextData.getValue()));
        list.retainAll(seansService.findAllGodzina(filterTextGodzina.getValue()));
        list.retainAll(seansService.findAllSala(filterTextSala.getValue()));
        list.retainAll(seansService.findAllFilm(filterTextFilm.getValue()));
        grid.setItems(list);
    }
}
