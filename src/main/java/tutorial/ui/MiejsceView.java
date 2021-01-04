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
import tutorial.entity.Miejsce;
import tutorial.forms.MiejsceForm;
import tutorial.service.MiejsceService;
import tutorial.service.SalaService;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Route(value = "miejsce", layout = MainView.class)
@PageTitle("Miejsca")
public class MiejsceView extends VerticalLayout {

    MiejsceForm form;
    Grid<Miejsce> grid = new Grid<>(Miejsce.class);
    TextField filterTextNumerRzedu = new TextField();
    TextField filterTextNumerMiejsca = new TextField();
    TextField filterTextSala = new TextField();

    MiejsceService miejsceService;

    public MiejsceView(MiejsceService miejsceService, SalaService salaService) {
        this.miejsceService = miejsceService;
        addClassName("miejsce-view");
        setSizeFull();
        configureGrid();


        form = new MiejsceForm(salaService.findAll());
        form.addListener(MiejsceForm.SaveEvent.class, this::saveMiejsce);
        form.addListener(MiejsceForm.DeleteEvent.class, this::deleteMiejsce);
        form.addListener(MiejsceForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteMiejsce(MiejsceForm.DeleteEvent evt) {
        miejsceService.delete(evt.getMiejsce());
        updateList();
        closeEditor();
    }

    private void saveMiejsce(MiejsceForm.SaveEvent evt) {
        miejsceService.save(evt.getMiejsce());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterTextNumerRzedu.setPlaceholder("Filtruj rzad");
        filterTextNumerRzedu.setClearButtonVisible(true);
        filterTextNumerRzedu.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextNumerRzedu.addValueChangeListener(e -> updateList());

        filterTextNumerMiejsca.setPlaceholder("Filtruj miejsce");
        filterTextNumerMiejsca.setClearButtonVisible(true);
        filterTextNumerMiejsca.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextNumerMiejsca.addValueChangeListener(e -> updateList());

        filterTextSala.setPlaceholder("Filtruj sale");
        filterTextSala.setClearButtonVisible(true);
        filterTextSala.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextSala.addValueChangeListener(e -> updateList());

        Button addBiletButton = new Button("Dodaj miejsce", click -> addMiejsce());
        Button closeFormButton = new Button("Zamknij formularz", click -> closeEditor());
        HorizontalLayout toolbar = new HorizontalLayout(filterTextNumerRzedu,filterTextNumerMiejsca,addBiletButton, closeFormButton);
        //,filterTextSala
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addMiejsce() {
        grid.asSingleSelect().clear();
        editMiejsce(new Miejsce());
    }

    private void configureGrid() {
        grid.addClassName("miejsce-grid");
        grid.setSizeFull();
        grid.setColumns("numerRzedu", "numerMiejsca", "sala");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editMiejsce(evt.getValue()));
    }

    private void editMiejsce(Miejsce miejsce) {
        if (miejsce == null) {
            closeEditor();
        } else {
            form.setMiejsce(miejsce);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setMiejsce(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        List<Miejsce> list = new ArrayList<>();
        list.addAll(miejsceService.findAll(filterTextNumerRzedu.getValue()));
        list.retainAll(miejsceService.findAllCena(filterTextNumerMiejsca.getValue()));
        list.retainAll(miejsceService.findAllSala(filterTextSala.getValue()));
        grid.setItems(list);
    }
}
