package tutorial.forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import tutorial.entity.Film;
import tutorial.entity.Sala;
import tutorial.entity.Seans;

import java.util.List;

public class SeansForm extends FormLayout {

    ComboBox<Seans.Lektor> lektor = new ComboBox<>("lektor");
    ComboBox<Seans.Napisy> napisy = new ComboBox<>("napisy");
    ComboBox<Sala> sala = new ComboBox<>("sala");
    ComboBox<Film> film = new ComboBox<>("film");
    TextField godzina = new TextField("godzina");
    DatePicker data = new DatePicker("data");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Seans> binder = new Binder<>(Seans.class);
    private Seans seans;

    public SeansForm(List<Film> filmy,List<Sala> sale) {
        lektor.setRequired(true);
        napisy.setRequired(true);
        godzina.setRequired(true);
        data.setRequired(true);
        sala.setRequired(true);
        film.setRequired(true);

        addClassName("seans-form");
        lektor.setItems(Seans.Lektor.values());
        napisy.setItems(Seans.Napisy.values());
        sala.setItems(sale);
        sala.setItemLabelGenerator(Sala::getNrSali);
        film.setItems(filmy);
        film.setItemLabelGenerator(Film::getTytul);
        binder.bindInstanceFields(this);
        add(
                lektor,
                napisy,
                data,
                godzina,
                sala,
                film,
                createButtonsLayout()
        );
        binder.forField(lektor).withValidator(lektor -> !lektor.equals(""),"lektor nie może być puste!").bind(Seans::getLektor, Seans::setLektor);
        binder.forField(napisy).withValidator(napisy -> !napisy.equals(""),"napisy nie może być puste!").bind(Seans::getNapisy, Seans::setNapisy);
        binder.forField(data).withValidator(data -> !data.equals(""),"data nie może być pusta!").bind(Seans::getData, Seans::setData);
        binder.forField(godzina).withValidator(godzina -> godzina.length() > 0,"godzina nie może być pusta!").bind(Seans::getGodzina, Seans::setGodzina);
        binder.forField(sala).withValidator(sala -> !sala.equals(""),"sala nie może być pusta!").bind(Seans::getSala, Seans::setSala);
        binder.forField(film).withValidator(film -> !film.equals(""),"film nie może być pusty!").bind(Seans::getFilm, Seans::setFilm);

    }
    public void setSeans(Seans seans) {
        this.seans = seans;
        binder.readBean(seans);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, seans)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(seans);
            fireEvent(new SaveEvent(this, seans));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class SeansFormEvent extends ComponentEvent<SeansForm> {
        private Seans seans;

        protected SeansFormEvent(SeansForm source, Seans seans) {
            super(source, false);
            this.seans = seans;
        }

        public Seans getSeans() {
            return seans;
        }
    }

    public static class SaveEvent extends SeansFormEvent {
        SaveEvent(SeansForm source, Seans seans) {
            super(source, seans);
        }
    }

    public static class DeleteEvent extends SeansFormEvent {
        DeleteEvent(SeansForm source, Seans seans) {
            super(source, seans);
        }

    }

    public static class CloseEvent extends SeansFormEvent {
        CloseEvent(SeansForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
