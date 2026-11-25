package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import org.example.ui.CartPage;
import org.example.ui.HomePage;

public class IrAlCarrito implements Task {

    @Override
    @Step("{0} va al carrito de compras")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(HomePage.CARRITO));
        CartPage.FILAS.resolveFor(actor).waitUntilVisible();
        actor.attemptsTo(TomarEvidencia.de("carrito_con_productos"));
    }

    public static IrAlCarrito deCompras() {
        return Tasks.instrumented(IrAlCarrito.class);
    }
}
