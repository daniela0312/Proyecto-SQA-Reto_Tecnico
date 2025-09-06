package co.daniela.sqa.reto.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class TrySetInvalidDate implements Task {

    private final String rawValue;

    private TrySetInvalidDate(String rawValue) {
        this.rawValue = rawValue;
    }

    public static Performable fromIso(String rawValue) {
        return Task.where("{0} intenta setear una fecha invalida " + rawValue,
                new TrySetInvalidDate(rawValue)
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = net.serenitybdd.screenplay.abilities.BrowseTheWeb.as(actor).getDriver();

        ((JavascriptExecutor) driver).executeScript(
                "const input = document.getElementById('date');" +
                        "if (input) {" +
                        "  input.value = arguments[0];" +
                        "  input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "  input.dispatchEvent(new Event('change', { bubbles: true }));" +
                        "  input.blur();" +
                        "}"
                , rawValue
        );
    }
}