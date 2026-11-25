package org.example.ui;

import net.serenitybdd.screenplay.targets.Target;

public class HomePage {
    public static final Target INICIO = Target.the("enlace Home")
            .locatedBy("//a[contains(text(),'Home')]");
    public static final Target CARRITO = Target.the("enlace Cart")
            .locatedBy("#cartur");
    public static final Target CATEGORIA = Target.the("categoria {0}")
            .locatedBy("//a[@id='itemc'][text()='{0}']");
    public static final Target PRODUCTO = Target.the("producto {0}")
            .locatedBy("//div[@id='tbodyid']//a[@class='hrefch'][text()='{0}']");
    public static final Target CUALQUIER_PRODUCTO = Target.the("primer producto del listado")
            .locatedBy("//div[@id='tbodyid']//a[@class='hrefch']");
}
