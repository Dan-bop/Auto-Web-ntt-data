package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.example.ui.CartPage;

import java.util.Map;

public class ConfirmarCompra implements Task {

    private final Map<String, String> datos;

    public ConfirmarCompra(Map<String, String> datos) {
        this.datos = datos;
    }

    @Override
    @Step("{0} confirma la compra con el formulario de pago")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CartPage.PLACE_ORDER));
        CartPage.CAMPO_NOMBRE.resolveFor(actor).waitUntilVisible();
        actor.attemptsTo(
                Enter.theValue(datos.get("nombre")).into(CartPage.CAMPO_NOMBRE),
                Enter.theValue(datos.get("pais")).into(CartPage.CAMPO_PAIS),
                Enter.theValue(datos.get("ciudad")).into(CartPage.CAMPO_CIUDAD),
                Enter.theValue(datos.get("tarjeta")).into(CartPage.CAMPO_TARJETA),
                Enter.theValue(datos.get("mes")).into(CartPage.CAMPO_MES),
                Enter.theValue(datos.get("anio")).into(CartPage.CAMPO_ANIO),
                TomarEvidencia.de("formulario_de_compra_lleno"),
                Click.on(CartPage.PURCHASE));

        CartPage.MENSAJE_COMPRA.resolveFor(actor).waitUntilVisible();
        String mensaje = CartPage.MENSAJE_COMPRA.resolveFor(actor).getText().trim();
        actor.remember("mensajeCompra", mensaje);

        actor.attemptsTo(
                TomarEvidencia.de("confirmacion_de_compra"),
                Click.on(CartPage.BOTON_OK));
    }

    public static ConfirmarCompra conDatos(Map<String, String> datos) {
        return Tasks.instrumented(ConfirmarCompra.class, datos);
    }
}
