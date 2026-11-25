package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.example.Utils.Esperas;
import org.example.ui.CartPage;

public class EliminarProducto implements Task {

    private final String producto;

    public EliminarProducto(String producto) {
        this.producto = producto;
    }

    @Override
    @Step("{0} elimina '#producto' del carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TomarEvidencia.de("antes_de_eliminar_" + producto),
                Click.on(CartPage.ELIMINAR.of(producto)));
        Esperas.desapareceTexto(BrowseTheWeb.as(actor).getDriver(), producto);
        actor.attemptsTo(TomarEvidencia.de("despues_de_eliminar_" + producto));
    }

    public static EliminarProducto delCarrito(String producto) {
        return Tasks.instrumented(EliminarProducto.class, producto);
    }
}
