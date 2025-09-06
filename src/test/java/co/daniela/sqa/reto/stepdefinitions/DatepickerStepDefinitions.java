package co.daniela.sqa.reto.stepdefinitions;

import co.daniela.sqa.reto.ui.DatepickerPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Attribute;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static co.daniela.sqa.reto.ui.DatepickerPage.DATE_INPUT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class DatepickerStepDefinitions {

    // URL del demo SIN iframe
    private static final String DATEPICKER_DEMO_URL =
            "https://jqueryui.com/resources/demos/datepicker/default.html";

    // El Datepicker del demo usa formato mm/dd/yy. Usaremos MM/dd/yyyy.
    private static final DateTimeFormatter UI_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private Actor actor() {
        return Actor.named("Usuario").whoCan(BrowseTheWeb.with(Serenity.getDriver()));
    }

    @Dado("que abro el datepicker")
    public void que_abro_el_datepicker() {
        Actor user = actor();
        user.wasAbleTo(
                Open.url(DATEPICKER_DEMO_URL),
                WaitUntil.the(DATE_INPUT, isVisible()).forNoMoreThan(20).seconds()
        );
    }

    @Cuando("selecciono la fecha {string}")
    public void selecciono_la_fecha(String fechaIso) {
        LocalDate fecha = LocalDate.parse(fechaIso); // ISO por defecto
        String valorUI = UI_FORMAT.format(fecha);

        Actor user = actor();
        user.attemptsTo(
                WaitUntil.the(DATE_INPUT, isVisible()).forNoMoreThan(20).seconds(),
                Clear.field(DATE_INPUT),
                Enter.theValue(valorUI).into(DATE_INPUT)
        );
    }

    @Entonces("debo ver el valor {string} en el campo")
    public void debo_ver_el_valor_en_el_campo(String esperado) {
        Actor user = actor();
        user.attemptsTo(
                WaitUntil.the(DATE_INPUT, isVisible()).forNoMoreThan(20).seconds(),
                Ensure.that(Attribute.of(DATE_INPUT).named("value")).isEqualTo(esperado)
        );
    }

    // ----------------------------
    // NUEVOS STEPS PARA FECHAS INVÁLIDAS
    // ----------------------------

    @Cuando("intento seleccionar la fecha invalida {string}")
    public void intento_seleccionar_la_fecha_invalida(String fechaInvalida) {
        Actor user = actor();

        // Guardamos el valor antes del intento
        String valorAntes = Attribute.of(DATE_INPUT).named("value").answeredBy(user);
        user.remember("valorAntes", valorAntes);

        user.attemptsTo(
                WaitUntil.the(DATE_INPUT, isVisible()).forNoMoreThan(20).seconds(),
                Clear.field(DATE_INPUT),
                Enter.theValue(fechaInvalida).into(DATE_INPUT),
                Hit.the(Keys.TAB).into(DATE_INPUT) // Forzar blur
        );
    }

    @Entonces("el campo de fecha debe permanecer {string}")
    public void el_campo_de_fecha_debe_permanecer(String esperado) {
        Actor user = actor();
        String valorActual = Attribute.of(DATE_INPUT).named("value").answeredBy(user);

        if ("<anterior>".equalsIgnoreCase(esperado) || "sin cambios".equalsIgnoreCase(esperado)) {
            String valorAntes = user.recall("valorAntes");
            Ensure.that(valorActual).isEqualTo(valorAntes);
        } else {
            Ensure.that(valorActual).isEqualTo(esperado);
        }
    }
}