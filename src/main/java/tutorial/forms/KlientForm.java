package tutorial.forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.shared.Registration;
import tutorial.entity.Klient;

public class KlientForm extends FormLayout {

    TextField imie = new TextField("imie");
    TextField nazwisko = new TextField("nazwisko");
    EmailField email = new EmailField("email");
    TextField tel = new TextField("tel");
    TextField adres = new TextField("adres");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Klient> binder = new Binder<>(Klient.class);
    private Klient klient;

    public KlientForm() {
        imie.setRequired(true);
        nazwisko.setRequired(true);
        adres.setRequired(true);
        addClassName("klient-form");

        binder.bindInstanceFields(this);
        add(
                imie,
                nazwisko,
                email,
                tel,
                adres,
                createButtonsLayout()
        );
        binder.forField(imie).withValidator(imie -> imie.length() > 0,"imię nie może być puste!").bind(Klient::getImie, Klient::setImie);
        binder.forField(nazwisko).withValidator(nazwisko -> nazwisko.length() > 0,"Nazwisko nie może być puste!").bind(Klient::getNazwisko, Klient::setNazwisko);
        binder.forField(adres).withValidator(adres -> adres.length() > 0,"Adres nie może być pusty!").bind(Klient::getAdres, Klient::setAdres);
        binder.forField(email).withValidator(new EmailValidator("Podaj poprawny email!")).bind(Klient::getEmail, Klient::setEmail);


    }

    public void setKlient(Klient klient) {
        this.klient = klient;
        binder.readBean(klient);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, klient)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(klient);
            fireEvent(new SaveEvent(this, klient));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class KlientFormEvent extends ComponentEvent<KlientForm> {
        private Klient klient;

        protected KlientFormEvent(KlientForm source, Klient klient) {
            super(source, false);
            this.klient = klient;
        }

        public Klient getKlient() {
            return klient;
        }
    }

    public static class SaveEvent extends KlientFormEvent {
        SaveEvent(KlientForm source, Klient klient) {
            super(source, klient);
        }
    }

    public static class DeleteEvent extends KlientFormEvent {
        DeleteEvent(KlientForm source, Klient klient) {
            super(source, klient);
        }

    }

    public static class CloseEvent extends KlientFormEvent {
        CloseEvent(KlientForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
