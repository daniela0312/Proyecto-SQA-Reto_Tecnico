package co.daniela.sqa.reto.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.questions.Attribute;

public class DateValue {

    private DateValue() {}

    /**
     * Devuelve el atributo "value" del Target (útil para inputs).
     */
    public static Question<String> from(Target target) {
        return new Question<>() {
            @Override
            public String answeredBy(Actor actor) {
                return Attribute.of(target).named("value").answeredBy(actor);
            }

            @Override
            public String getSubject() {
                return "valor del campo " + target.getName();
            }
        };
    }
}