package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.example.Utils.Esperas;
import org.example.ui.CartPage;

import java.util.Map;

public class ComprarConDatosIncompletos implements Task {

    private final Map<String, String> datos;

    public ComprarConDatosIncompletos(Map<String, String> datos) {
        this.datos = datos;
    }

    private String valor(String clave) {
        String v = datos.get(clave);
        return v == null ? "" : v;
    }

    @Override
    @Step("{0} intenta comprar con datos incompletos")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CartPage.PLACE_ORDER));
        CartPage.CAMPO_NOMBRE.resolveFor(actor).waitUntilVisible();
        actor.attemptsTo(
                Enter.theValue(valor("nombre")).into(CartPage.CAMPO_NOMBRE),
                Enter.theValue(valor("pais")).into(CartPage.CAMPO_PAIS),
                Enter.theValue(valor("ciudad")).into(CartPage.CAMPO_CIUDAD),
                Enter.theValue(valor("tarjeta")).into(CartPage.CAMPO_TARJETA),
                Enter.theValue(valor("mes")).into(CartPage.CAMPO_MES),
                Enter.theValue(valor("anio")).into(CartPage.CAMPO_ANIO),
                TomarEvidencia.de("formulario_incompleto"),
                Click.on(CartPage.PURCHASE));

        String alerta = Esperas.aceptarAlerta(BrowseTheWeb.as(actor).getDriver());
        actor.remember("alerta_formulario", alerta);
        actor.attemptsTo(TomarEvidencia.de("alerta_datos_incompletos"));
    }

    public static ComprarConDatosIncompletos conDatos(Map<String, String> datos) {
        return Tasks.instrumented(ComprarConDatosIncompletos.class, datos);
    }
}