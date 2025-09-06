package co.daniela.sqa.reto.ui;

import net.serenitybdd.screenplay.targets.Target;

public class DatepickerPage {

    public static final Target DATE_INPUT = Target.the("campo de fecha del datepicker")
            .locatedBy("#datepicker");

}