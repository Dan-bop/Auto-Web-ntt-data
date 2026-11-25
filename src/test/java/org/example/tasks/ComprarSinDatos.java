package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.example.Utils.Esperas;
import org.example.ui.CartPage;

public class ComprarSinDatos implements Task {

    @Override
    @Step("{0} intenta comprar con el formulario vacio")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CartPage.PLACE_ORDER));
        CartPage.CAMPO_NOMBRE.resolveFor(actor).waitUntilVisible();
        actor.attemptsTo(
                TomarEvidencia.de("formulario_vacio"),
                Click.on(CartPage.PURCHASE));
        String alerta = Esperas.aceptarAlerta(BrowseTheWeb.as(actor).getDriver());
        actor.remember("alerta_formulario", alerta);
        actor.attemptsTo(TomarEvidencia.de("alerta_formulario_vacio_aceptada"));
    }

    public static ComprarSinDatos formularioVacio() {
        return Tasks.instrumented(ComprarSinDatos.class);
    }
}
