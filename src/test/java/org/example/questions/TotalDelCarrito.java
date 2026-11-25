package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.core.pages.WebElementFacade;
import org.example.ui.CartPage;

import java.util.List;

/** Devuelve el total mostrado o la suma de los precios de cada fila. */
public class TotalDelCarrito implements Question<Integer> {

    private final boolean sumaDeFilas;

    private TotalDelCarrito(boolean sumaDeFilas) {
        this.sumaDeFilas = sumaDeFilas;
    }

    @Override
    public Integer answeredBy(Actor actor) {
        if (sumaDeFilas) {
            List<WebElementFacade> precios = CartPage.PRECIOS.resolveAllFor(actor);
            return precios.stream().mapToInt(p -> Integer.parseInt(p.getText().trim())).sum();
        }
        String texto = CartPage.TOTAL.resolveFor(actor).getText().trim();
        return texto.isEmpty() ? 0 : Integer.parseInt(texto);
    }

    public static TotalDelCarrito mostrado() {
        return new TotalDelCarrito(false);
    }

    public static TotalDelCarrito sumaDePrecios() {
        return new TotalDelCarrito(true);
    }
}
