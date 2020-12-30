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
import tutorial.entity.Film;
import tutorial.forms.FilmForm;
import tutorial.service.FilmService;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Route(value = "film", layout = MainView.class)
@PageTitle("Filmy")
public class FilmView extends VerticalLayout {

    FilmForm form;
    Grid<Film> grid = new Grid<>(Film.class);
    TextField filterTextTytul = new TextField();
    TextField filterTextGatunek = new TextField();
    TextField filterTextRokProdukcji = new TextField();
    TextField filterTextRezyseria = new TextField();
    TextField filterTextCzasTrwania = new TextField();
    TextField filterTextOgraniczenieWiekowe = new TextField();

    FilmService filmService;

    public FilmView(FilmService filmService) {
        this.filmService = filmService;
        addClassName("film-view");
        setSizeFull();
        configureGrid();


        form = new FilmForm();
        form.addListener(FilmForm.SaveEvent.class, this::saveFilm);
        form.addListener(FilmForm.DeleteEvent.class, this::deleteFilm);
        form.addListener(FilmForm.CloseEvent.class, e -> closeEditor());

        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteFilm(FilmForm.DeleteEvent evt) {
        filmService.delete(evt.getFilm());
        updateList();
        closeEditor();
    }

    private void saveFilm(FilmForm.SaveEvent evt) {
        filmService.save(evt.getFilm());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterTextTytul.setPlaceholder("Filtruj tytuł");
        filterTextTytul.setClearButtonVisible(true);
        filterTextTytul.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextTytul.addValueChangeListener(e -> updateList());

        filterTextGatunek.setPlaceholder("Filtruj gatunek");
        filterTextGatunek.setClearButtonVisible(true);
        filterTextGatunek.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextGatunek.addValueChangeListener(e -> updateList());

        filterTextRezyseria.setPlaceholder("Filtruj rezyserie");
        filterTextRezyseria.setClearButtonVisible(true);
        filterTextRezyseria.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextRezyseria.addValueChangeListener(e -> updateList());

        filterTextCzasTrwania.setPlaceholder("Filtruj czas trwania");
        filterTextCzasTrwania.setClearButtonVisible(true);
        filterTextCzasTrwania.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextCzasTrwania.addValueChangeListener(e -> updateList());

        filterTextRokProdukcji.setPlaceholder("Filtruj rok produkcji");
        filterTextRokProdukcji.setClearButtonVisible(true);
        filterTextRokProdukcji.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextRokProdukcji.addValueChangeListener(e -> updateList());

        filterTextOgraniczenieWiekowe.setPlaceholder("Filtruj ograniczenie");
        filterTextOgraniczenieWiekowe.setClearButtonVisible(true);
        filterTextOgraniczenieWiekowe.setValueChangeMode(ValueChangeMode.LAZY);
        filterTextOgraniczenieWiekowe.addValueChangeListener(e -> updateList());
        Button addBiletButton = new Button("Dodaj film", click -> addFilm());
        Button closeFormButton = new Button("Zamknij formularz", click -> closeEditor());
        HorizontalLayout toolbar = new HorizontalLayout(filterTextTytul,filterTextGatunek,filterTextRezyseria,filterTextCzasTrwania,filterTextRokProdukcji,filterTextOgraniczenieWiekowe,addBiletButton, closeFormButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addFilm() {
        grid.asSingleSelect().clear();
        editFilm(new Film());
    }

    private void configureGrid() {
        grid.addClassName("film-grid");
        grid.setSizeFull();
        grid.setColumns("tytul", "gatunek","rezyseria","rokProdukcji","czasTrwania","ograniczenieWiekowe");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(evt -> editFilm(evt.getValue()));
    }

    private void editFilm(Film film) {
        if (film == null) {
            closeEditor();
        } else {
            form.setFilm(film);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setFilm(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        List<Film> list = new ArrayList<>();
        list.addAll(filmService.findAllTytul(filterTextTytul.getValue()));
        list.retainAll(filmService.findAllGatunek(filterTextTytul.getValue()));
        list.retainAll(filmService.findAllCzasTrwania(filterTextCzasTrwania.getValue()));
        list.retainAll(filmService.findAllRokProdukcji(filterTextRokProdukcji.getValue()));
        list.retainAll(filmService.findAllRezyseria(filterTextRezyseria.getValue()));
        list.retainAll(filmService.findAllOgraniczenie(filterTextOgraniczenieWiekowe.getValue()));


        grid.setItems(list);
    }
}
