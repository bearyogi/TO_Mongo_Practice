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
import tutorial.entity.Sala;

public class SalaForm extends FormLayout {

    ComboBox<Sala.Lokalizacja> lokalizacja = new ComboBox<>("lokalizacja");
    ComboBox<Sala.Klasa> klasa = new ComboBox<>("klasa");
    TextField nrSali = new TextField("nrSali");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Sala> binder = new Binder<>(Sala.class);
    private Sala sala;

    public SalaForm() {
        klasa.setRequired(true);
        lokalizacja.setRequired(true);
        nrSali.setRequired(true);
        addClassName("sala-form");
        lokalizacja.setItems(Sala.Lokalizacja.values());
        klasa.setItems(Sala.Klasa.values());
        binder.bindInstanceFields(this);
        add(
                lokalizacja,
                klasa,
                nrSali,
                createButtonsLayout()
        );
        binder.forField(klasa).withValidator(klasa -> !klasa.equals(""),"ulga nie może być pusta!").bind(Sala::getKlasa, Sala::setKlasa);
        binder.forField(lokalizacja).withValidator(lokalizacja -> !lokalizacja.equals(""),"ulga nie może być pusta!").bind(Sala::getLokalizacja, Sala::setLokalizacja);
        binder.forField(nrSali).withValidator(nrSali -> nrSali.length() > 0,"cena nie może być pusta!").bind(Sala::getNrSali, Sala::setNrSali);


    }
    public void setSala(Sala sala) {
        this.sala = sala;
        binder.readBean(sala);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, sala)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(sala);
            fireEvent(new SaveEvent(this, sala));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class SalaFormEvent extends ComponentEvent<SalaForm> {
        private Sala sala;

        protected SalaFormEvent(SalaForm source, Sala sala) {
            super(source, false);
            this.sala = sala;
        }

        public Sala getSala() {
            return sala;
        }
    }

    public static class SaveEvent extends SalaFormEvent {
        SaveEvent(SalaForm source, Sala sala) {
            super(source, sala);
        }
    }

    public static class DeleteEvent extends SalaFormEvent {
        DeleteEvent(SalaForm source, Sala sala) {
            super(source, sala);
        }

    }

    public static class CloseEvent extends SalaFormEvent {
        CloseEvent(SalaForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
