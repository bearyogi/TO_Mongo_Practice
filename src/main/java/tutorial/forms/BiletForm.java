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
import tutorial.entity.Bilet;

public class BiletForm extends FormLayout {

    ComboBox<Bilet.Ulga> ulga = new ComboBox<>("ulga");
    TextField cena = new TextField("cena");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Bilet> binder = new Binder<>(Bilet.class);
    private Bilet bilet;

    public BiletForm() {
        ulga.setRequired(true);
        cena.setRequired(true);
        addClassName("bilet-form");
        ulga.setItems(Bilet.Ulga.values());
        binder.bindInstanceFields(this);
        add(
                ulga,
                cena,
                createButtonsLayout()
        );
        binder.forField(ulga).withValidator(ulga -> !ulga.equals(""),"ulga nie może być pusta!").bind(Bilet::getUlga, Bilet::setUlga);
        binder.forField(cena).withValidator(cena -> cena.length() > 0,"cena nie może być pusta!").bind(Bilet::getCena, Bilet::setCena);


    }
    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
        binder.readBean(bilet);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, bilet)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(bilet);
            fireEvent(new SaveEvent(this, bilet));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class BiletFormEvent extends ComponentEvent<BiletForm> {
        private Bilet bilet;

        protected BiletFormEvent(BiletForm source, Bilet bilet) {
            super(source, false);
            this.bilet = bilet;
        }

        public Bilet getBilet() {
            return bilet;
        }
    }

    public static class SaveEvent extends BiletFormEvent {
        SaveEvent(BiletForm source, Bilet bilet) {
            super(source, bilet);
        }
    }

    public static class DeleteEvent extends BiletFormEvent {
        DeleteEvent(BiletForm source, Bilet bilet) {
            super(source, bilet);
        }

    }

    public static class CloseEvent extends BiletFormEvent {
        CloseEvent(BiletForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
