package tutorial.forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import tutorial.entity.Film;

public class FilmForm extends FormLayout {

    ComboBox<Film.Ograniczenie> ograniczenieWiekowe = new ComboBox<>("ograniczenie wiekowe");
    TextField tytul = new TextField("tytul");
    TextField rokProdukcji = new TextField("rokProdukcji");
    TextField gatunek = new TextField("gatunek");
    TextField rezyseria = new TextField("rezyseria");
    TextField czasTrwania = new TextField("czasTrwania");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Film> binder = new Binder<>(Film.class);
    private Film film;

    public FilmForm() {
        tytul.setRequired(true);
        rokProdukcji.setRequired(true);
        gatunek.setRequired(true);
        rezyseria.setRequired(true);
        czasTrwania.setRequired(true);
        ograniczenieWiekowe.setRequired(true);

        addClassName("film-form");
        ograniczenieWiekowe.setItems(Film.Ograniczenie.values());
        binder.bindInstanceFields(this);
        add(
                ograniczenieWiekowe,
                tytul,
                rokProdukcji,
                gatunek,
                rezyseria,
                czasTrwania,
                createButtonsLayout()
        );

        binder.forField(ograniczenieWiekowe).withValidator(ograniczenie -> !ograniczenie.equals(""),"ograniczenie nie może być puste!").bind(Film::getOgraniczenieWiekowe, Film::setOgraniczenieWiekowe);
        binder.forField(tytul).withValidator(tytul -> tytul.length() > 0,"tytul nie może być pusty!").bind(Film::getTytul, Film::setTytul);
        binder.forField(rokProdukcji).withValidator(rokProdukcji -> rokProdukcji.length() > 0,"rok produkcji nie może być pusty!").bind(Film::getRokProdukcji, Film::setRokProdukcji);
        binder.forField(rezyseria).withValidator(rezyseria -> rezyseria.length() > 0,"rezyseria nie może być pusta!").bind(Film::getRezyseria, Film::setRezyseria);
        binder.forField(czasTrwania).withValidator(czasTrwania -> czasTrwania.length() > 0,"czas nie może być pusty!").bind(Film::getCzasTrwania, Film::setCzasTrwania);
        binder.forField(gatunek).withValidator(gatunek -> gatunek.length() > 0,"gatunek nie może być pusty!").bind(Film::getGatunek, Film::setGatunek);


    }
    public void setFilm(Film film) {
        this.film = film;
        binder.readBean(film);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, film)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(film);
            fireEvent(new SaveEvent(this, film));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class FilmFormEvent extends ComponentEvent<FilmForm> {
        private Film film;

        protected FilmFormEvent(FilmForm source, Film film) {
            super(source, false);
            this.film = film;
        }

        public Film getFilm() {
            return film;
        }
    }

    public static class SaveEvent extends FilmFormEvent {
        SaveEvent(FilmForm source, Film film) {
            super(source, film);
        }
    }

    public static class DeleteEvent extends FilmFormEvent {
        DeleteEvent(FilmForm source, Film film) {
            super(source, film);
        }

    }

    public static class CloseEvent extends FilmFormEvent {
        CloseEvent(FilmForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
