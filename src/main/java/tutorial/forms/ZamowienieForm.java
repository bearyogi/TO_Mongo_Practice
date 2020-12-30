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
import tutorial.entity.Klient;
import tutorial.entity.Seans;
import tutorial.entity.Zamowienie;

import java.util.List;

public class ZamowienieForm extends FormLayout {

    ComboBox<Zamowienie.Platnosc> typPlatnosci = new ComboBox<>("platnosc");
    ComboBox<Zamowienie.Status> status = new ComboBox<>("status");
    ComboBox<Klient> klient = new ComboBox("klient");
    ComboBox<Seans> seans = new ComboBox("seans");
    TextField liczbaBiletow = new TextField("liczbaBiletow");
    TextField kwota = new TextField("kwota");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Zamowienie> binder = new Binder<>(Zamowienie.class);
    private Zamowienie zamowienie;

    public ZamowienieForm(List<Klient>klienci, List<Seans> seanse) {
        typPlatnosci.setRequired(true);
        status.setRequired(true);
        liczbaBiletow.setRequired(true);
        kwota.setRequired(true);
        klient.setRequired(true);
        seans.setRequired(true);

        addClassName("seans-form");
        typPlatnosci.setItems(Zamowienie.Platnosc.values());
        status.setItems(Zamowienie.Status.values());

        klient.setItems(klienci);
        klient.setItemLabelGenerator(Klient::getNazwisko);

        seans.setItems(seanse);
        seans.setItemLabelGenerator(Seans::toString);
        binder.bindInstanceFields(this);
        add(
                typPlatnosci,
                status,
                liczbaBiletow,
                kwota,
                klient,
                seans,
                createButtonsLayout()
        );
        binder.forField(typPlatnosci).withValidator(typPlatnosci -> !typPlatnosci.equals(""),"typPlatnosci nie może być pusty!").bind(Zamowienie::getTypPlatnosci, Zamowienie::setTypPlatnosci);
        binder.forField(status).withValidator(status -> !status.equals(""),"status nie może być pusty!").bind(Zamowienie::getStatus, Zamowienie::setStatus);
        binder.forField(liczbaBiletow).withValidator(liczbaBiletow -> liczbaBiletow.length() > 0,"liczbaBiletow nie może być pusta!").bind(Zamowienie::getLiczbaBiletow, Zamowienie::setLiczbaBiletow);
        binder.forField(kwota).withValidator(kwota -> kwota.length() > 0,"kwota nie może być pusta!").bind(Zamowienie::getKwota, Zamowienie::setKwota);
        binder.forField(klient).withValidator(klient -> !klient.equals(""),"klient nie może być pusty!").bind(Zamowienie::getKlient, Zamowienie::setKlient);
        binder.forField(seans).withValidator(seans -> !seans.equals(""),"seans nie może być pusty!").bind(Zamowienie::getSeans, Zamowienie::setSeans);

    }
    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
        binder.readBean(zamowienie);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, zamowienie)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(zamowienie);
            fireEvent(new SaveEvent(this, zamowienie));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ZamowienieFormEvent extends ComponentEvent<ZamowienieForm> {
        private Zamowienie zamowienie;

        protected ZamowienieFormEvent(ZamowienieForm source, Zamowienie zamowienie) {
            super(source, false);
            this.zamowienie = zamowienie;
        }

        public Zamowienie getSeans() {
            return zamowienie;
        }
    }

    public static class SaveEvent extends ZamowienieFormEvent {
        SaveEvent(ZamowienieForm source, Zamowienie zamowienie) {
            super(source, zamowienie);
        }
    }

    public static class DeleteEvent extends ZamowienieFormEvent {
        DeleteEvent(ZamowienieForm source, Zamowienie zamowienie) {
            super(source, zamowienie);
        }

    }

    public static class CloseEvent extends ZamowienieFormEvent {
        CloseEvent(ZamowienieForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
