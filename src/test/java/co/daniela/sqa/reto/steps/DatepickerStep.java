package co.daniela.sqa.reto.steps;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Componente de apoyo para interactuar con el jQuery UI Datepicker
 */
public class DatepickerStep {

    // === Locators y URL por si no existe DatepickerPage ===
    private static final String DEMO_URL = "https://jqueryui.com/datepicker/";
    private static final Target IFRAME =
            Target.the("iframe de la demo")
                    .located(By.cssSelector(".demo-frame"));
    private static final Target INPUT_FECHA =
            Target.the("campo de fecha")
                    .located(By.id("datepicker"));

    // Targets del header y las flechas del jQuery UI datepicker
    private static final Target HEADER =
            Target.the("encabezado del datepicker (Mes Año)")
                    .located(By.cssSelector(".ui-datepicker-title"));

    private static final Target NEXT_BTN =
            Target.the("botón siguiente del datepicker")
                    .located(By.cssSelector(".ui-datepicker-next"));

    private static final Target PREV_BTN =
            Target.the("botón anterior del datepicker")
                    .located(By.cssSelector(".ui-datepicker-prev"));

    /** Factory method si quieres instanciar con Screenplay/Instrumented */
    public static DatepickerStep using() {
        return Instrumented.instanceOf(DatepickerStep.class).newInstance();
    }

    /** Abre la página y cambia el contexto al iframe del ejemplo. */
    public void open(Actor actor) {
        actor.attemptsTo(
                Open.url(DEMO_URL),
                WaitUntil.the(IFRAME, isVisible())
        );
        actor.attemptsTo(
                Switch.toFrame(IFRAME.resolveFor(actor))
        );
    }

    /** Selecciona una fecha en el datepicker. */
    public void pick(Actor actor, LocalDate date) {
        // Abre el widget
        actor.attemptsTo(Click.on(INPUT_FECHA));

        // Navega hasta el mes/año objetivo
        navigateToMonth(actor, date);

        // Selecciona el día
        actor.attemptsTo(
                Click.on(By.linkText(String.valueOf(date.getDayOfMonth())))
        );


        actor.attemptsTo(Switch.toDefaultContext());
    }

    /** Navega usando prev/next hasta que el encabezado muestre el mes/año deseado. */
    private void navigateToMonth(Actor actor, LocalDate targetDate) {
        final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        final YearMonth targetYm = YearMonth.from(targetDate);

        int guard = 24; // evita ciclos infinitos
        while (guard-- > 0) {

            String headerText = Text.of(HEADER).answeredBy(actor).trim();

            YearMonth shown;
            try {
                shown = YearMonth.parse(headerText, fmt);
            } catch (Exception e) {

                if (headerText.equalsIgnoreCase(targetYm.format(fmt))) {
                    break;
                }

                shown = YearMonth.now();
            }

            if (shown.equals(targetYm)) {
                break;
            } else if (shown.isBefore(targetYm)) {
                actor.attemptsTo(Click.on(NEXT_BTN));
            } else {
                actor.attemptsTo(Click.on(PREV_BTN));
            }
        }
    }
}