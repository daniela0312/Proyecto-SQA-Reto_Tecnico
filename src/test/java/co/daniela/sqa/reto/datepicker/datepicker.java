package co.daniela.sqa.reto.datepicker;
import net.serenitybdd.screenplay.Actor;
import java.time.LocalDate;

public interface datepicker {
    void open(Actor actor);
    void pick(Actor actor, LocalDate date);
}