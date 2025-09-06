package co.daniela.sqa.reto.tasks;

import co.daniela.sqa.reto.ui.DatepickerPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SelectDate implements Task {
    private final String isoDate;

    public SelectDate(String isoDate) { this.isoDate = isoDate; }

    public static SelectDate fromIso(String isoDate) {
        return Tasks.instrumented(SelectDate.class, isoDate);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade input = DatepickerPage.DATE_INPUT.resolveFor(actor);
        LocalDate date = LocalDate.parse(isoDate);
        String formatted = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        input.clear();
        input.type(formatted);
    }
}