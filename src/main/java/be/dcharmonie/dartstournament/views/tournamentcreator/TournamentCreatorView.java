package be.dcharmonie.dartstournament.views.tournamentcreator;

import be.dcharmonie.dartstournament.library.core.Pair;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationStatusChangeEvent;
import com.vaadin.flow.data.binder.ValidationStatusChangeListener;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("TournamentCreatorView")
@Route(value = "empty")
@RouteAlias(value = "")
public class TournamentCreatorView extends VerticalLayout {

    private final List<Person> names = new ArrayList<>();

    private VerticalLayout preview = new VerticalLayout();
    private TextArea namesInput = new TextArea();

    public TournamentCreatorView() {
        addClassName("tournament-creator-view");

        HorizontalLayout input = new HorizontalLayout();

        namesInput.setWidthFull();
        namesInput.setLabel("0 players");
        namesInput.setPlaceholder("Voornaam Naam");
        namesInput.setValueChangeMode(ValueChangeMode.ON_BLUR);
        namesInput.addKeyPressListener(Key.ENTER, event -> {
            namesInput.blur();
            namesInput.focus();
        });
        namesInput.addValueChangeListener(event -> {
            names.clear();
            String[] lines = namesInput.getValue().split("\n");
            Arrays.stream(lines).forEach(splitted -> {
                String[] pieces = splitted.split(" ");
                Person person = new Person();
                person.setLastName(pieces[0]);
                person.setFirstName(Arrays.stream(pieces).skip(1).collect(Collectors.joining(" ")));
                names.add(person);
            });
            updatePreview();
        });

        updatePreview();
        input.add(namesInput);
        input.add(preview);
        input.setFlexGrow(1, namesInput);

        HorizontalLayout buttons = new HorizontalLayout();
        IntegerField numberOfPoules = new IntegerField();
        numberOfPoules.setLabel("Aantal Poules");
        numberOfPoules.setValue(1);
        numberOfPoules.setMin(1);
        numberOfPoules.setStepButtonsVisible(true);

        IntegerField numberOfPlayersPerPoule = new IntegerField();
        numberOfPlayersPerPoule.setLabel("Aantal spelers per poule");
        numberOfPlayersPerPoule.setValue(4);
        numberOfPlayersPerPoule.setMin(4);
        numberOfPlayersPerPoule.setMax(6);
        numberOfPlayersPerPoule.setStepButtonsVisible(true);

        Button generate = new Button("Genereer");
        buttons.add(numberOfPoules, numberOfPlayersPerPoule, generate);

        add(input);
        add(buttons);

    }

    private void updatePreview() {
        preview.removeAll();
        HorizontalLayout layout = new HorizontalLayout();
        layout.setJustifyContentMode(JustifyContentMode.START);
        layout.setWidthFull();
        Div lastname = new Div();
        lastname.add(new Text("Naam"));
        Div firstname = new Div();
        firstname.add(new Text("Voornaam"));
        layout.add(lastname, firstname);
        preview.add(layout);
        for(Person person: names) {
            HorizontalLayout row = new HorizontalLayout();
            row.setJustifyContentMode(JustifyContentMode.START);
            row.setWidthFull();
            Div lastnameRow = new Div();
            lastnameRow.setWidthFull();
            lastnameRow.add(new Text(person.getLastName()));
            Div firstnameRow = new Div();
            firstnameRow.setWidthFull();
            firstnameRow.add(new Text(person.getFirstName()));
            row.add(lastnameRow, firstnameRow);
            preview.add(row);
        }
        namesInput.setLabel(String.format("%s players", names.size()));
    }


    /*
    public TournamentCreatorView() {
        addClassName("tournament-creator-view");
        setSizeFull();
        add(createParameterLayout());
        add(createPouleLayout());
    }

    private Component createParameterLayout() {
        VerticalLayout layout = new VerticalLayout();
        layout.add(new Text("boven"));
        return layout;
    }

    private Component createPouleLayout() {
        FlexLayout layout = new FlexLayout();
        for(int i=0; i<10; i++){
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setPadding(true);
            horizontalLayout.add(createPoule());
            layout.add(horizontalLayout);
        }
        layout.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        return layout;
    }

    private HorizontalLayout createPoule() {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");
        Span poule = new Span("Poule A");
        poule.addClassName("name");
        header.add(poule);
        description.add(header);

        for(int i=0; i < 6; i++) {
            HorizontalLayout name = new HorizontalLayout();
            TextField nameField = new TextField();
            nameField.setPlaceholder("Naam");
            name.add(nameField);
            TextField surNameField = new TextField();
            surNameField.setPlaceholder("Voornaam");
            name.add(surNameField);
            description.add(name);
        }

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");
        actions.add(new Button("Add"));

        description.add(actions);
        card.add(description);
        return card;
    }
     */
}
