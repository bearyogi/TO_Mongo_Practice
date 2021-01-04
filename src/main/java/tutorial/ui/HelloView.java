package tutorial.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import tutorial.MainView;

@Route(value = "", layout = MainView.class)
@PageTitle("Home")
public class HelloView extends VerticalLayout {
    public HelloView() {
        var h1 = new H1("A P L I K A C J A  K I N O");
        add(h1);
        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setAlignItems(FlexComponent.Alignment.CENTER);
    }
}