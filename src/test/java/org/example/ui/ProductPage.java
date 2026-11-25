package org.example.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ProductPage {
    public static final Target AGREGAR_AL_CARRITO = Target.the("boton Add to cart")
            .locatedBy("//a[contains(text(),'Add to cart')]");
}
