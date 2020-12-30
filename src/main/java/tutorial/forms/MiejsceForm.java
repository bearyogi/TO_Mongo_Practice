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
import tutorial.entity.Miejsce;
import tutorial.entity.Sala;

import java.util.List;

public class MiejsceForm extends FormLayout {

    TextField numerRzedu= new TextField("numerRzedu");
    TextField numerMiejsca = new TextField("numerMiejsca");
    ComboBox<Sala> sala = new ComboBox("sala");
    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Miejsce> binder = new Binder<>(Miejsce.class);
    private Miejsce miejsce;

    public MiejsceForm(List<Sala> sale) {
        numerRzedu.setRequired(true);
        numerMiejsca.setRequired(true);
        sala.setRequired(true);
        addClassName("miejsce-form");
        sala.setItems(sale);
        sala.setItemLabelGenerator(Sala::getNrSali);
        binder.bindInstanceFields(this);
        add(
                numerRzedu,
                numerMiejsca,
                sala,
                createButtonsLayout()
        );
        binder.forField(numerRzedu).withValidator(numerRzedu -> numerRzedu.length() > 0,"rzad nie może być pusty!").bind(Miejsce::getNumerMiejsca, Miejsce::setNumerMiejsca);
        binder.forField(numerMiejsca).withValidator(numerMiejsca -> numerMiejsca.length() > 0,"miejsce nie może być puste!").bind(Miejsce::getNumerRzedu, Miejsce::setNumerRzedu);
        binder.forField(sala).withValidator(sala -> !sala.equals(""), "sala nie może być pusta!").bind(Miejsce::getSala,Miejsce::setSala);

    }
    public void setMiejsce(Miejsce miejsce) {
        this.miejsce = miejsce;
        binder.readBean(miejsce);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, miejsce)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(miejsce);
            fireEvent(new SaveEvent(this, miejsce));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class MiejsceFormEvent extends ComponentEvent<MiejsceForm> {
        private Miejsce miejsce;

        protected MiejsceFormEvent(MiejsceForm source, Miejsce miejsce) {
            super(source, false);
            this.miejsce = miejsce;
        }

        public Miejsce getMiejsce() {
            return miejsce;
        }
    }

    public static class SaveEvent extends MiejsceFormEvent {
        SaveEvent(MiejsceForm source, Miejsce miejsce) {
            super(source, miejsce);
        }
    }

    public static class DeleteEvent extends MiejsceFormEvent {
        DeleteEvent(MiejsceForm source, Miejsce miejsce) {
            super(source, miejsce);
        }

    }

    public static class CloseEvent extends MiejsceFormEvent {
        CloseEvent(MiejsceForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}

