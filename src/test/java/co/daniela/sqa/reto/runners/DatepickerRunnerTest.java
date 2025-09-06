package co.daniela.sqa.reto.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features/datepicker",
        glue = "co.daniela.sqa.reto",
        tags = "@smoke or @regression",
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json"
        },
        monochrome = true
)
public class DatepickerRunnerTest {
}