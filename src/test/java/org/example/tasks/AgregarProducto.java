package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.example.Utils.Esperas;
import org.example.ui.HomePage;
import org.example.ui.ProductPage;

public class AgregarProducto implements Task {

    private final String categoria;
    private final String producto;

    public AgregarProducto(String categoria, String producto) {
        this.categoria = categoria;
        this.producto = producto;
    }

    @Override
    @Step("{0} agrega '#producto' de la categoria #categoria al carrito")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(HomePage.CATEGORIA.of(categoria)));
        HomePage.PRODUCTO.of(producto).resolveFor(actor).waitUntilVisible();
        actor.attemptsTo(
                TomarEvidencia.de("categoria_" + categoria),
                Click.on(HomePage.PRODUCTO.of(producto)));

        ProductPage.AGREGAR_AL_CARRITO.resolveFor(actor).waitUntilVisible();
        actor.attemptsTo(
                TomarEvidencia.de("detalle_" + producto),
                Click.on(ProductPage.AGREGAR_AL_CARRITO));

        // Demoblaze muestra una alerta JS "Product added"
        String alerta = Esperas.aceptarAlerta(BrowseTheWeb.as(actor).getDriver());
        actor.remember("alerta_agregar", alerta);

        actor.attemptsTo(Click.on(HomePage.INICIO));
        HomePage.CUALQUIER_PRODUCTO.resolveFor(actor).waitUntilVisible();
    }

    public static AgregarProducto llamado(String categoria, String producto) {
        return Tasks.instrumented(AgregarProducto.class, categoria, producto);
    }
}
