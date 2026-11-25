package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import org.example.ui.HomePage;

public class AbrirTienda implements Task {

    private static final String URL = "https://www.demoblaze.com/";

    @Override
    @Step("{0} abre la tienda Demoblaze")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(URL));
        HomePage.CUALQUIER_PRODUCTO.resolveFor(actor).waitUntilVisible();
        actor.attemptsTo(TomarEvidencia.de("01_home_demoblaze"));
    }

    public static AbrirTienda demoblaze() {
        return Tasks.instrumented(AbrirTienda.class);
    }
}
