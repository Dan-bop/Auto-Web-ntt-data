package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.core.pages.WebElementFacade;
import org.example.ui.CartPage;

import java.util.List;
import java.util.stream.Collectors;

public class ProductosEnCarrito implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
        return CartPage.NOMBRES.resolveAllFor(actor).stream()
                .map(WebElementFacade::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static ProductosEnCarrito nombres() {
        return new ProductosEnCarrito();
    }
}
