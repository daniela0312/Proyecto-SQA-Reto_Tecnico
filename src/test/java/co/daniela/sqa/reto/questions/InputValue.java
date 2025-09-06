package co.daniela.sqa.reto.questions;

import co.daniela.sqa.reto.ui.DatepickerPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class InputValue implements Question<String> {

    public static InputValue ofDateField() {
        return new InputValue();
    }

    @Override
    public String answeredBy(Actor actor) {
        return DatepickerPage.DATE_INPUT.resolveFor(actor).getValue();
    }
}