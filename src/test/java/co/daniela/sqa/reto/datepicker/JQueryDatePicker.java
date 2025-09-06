package co.daniela.sqa.reto.datepicker;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/datepicker",
        glue = "co.daniela.sqa.reto.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class JQueryDatePicker {
}