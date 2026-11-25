package org.example.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {
    public static final Target FILAS = Target.the("filas del carrito")
            .locatedBy("//tbody[@id='tbodyid']/tr");
    public static final Target NOMBRES = Target.the("nombres de productos del carrito")
            .locatedBy("//tbody[@id='tbodyid']/tr/td[2]");
    public static final Target PRECIOS = Target.the("precios de productos del carrito")
            .locatedBy("//tbody[@id='tbodyid']/tr/td[3]");
    public static final Target TOTAL = Target.the("total del carrito")
            .locatedBy("#totalp");
    public static final Target ELIMINAR = Target.the("enlace Delete del producto {0}")
            .locatedBy("//tbody[@id='tbodyid']//td[text()='{0}']/following-sibling::td/a[text()='Delete']");
    public static final Target PLACE_ORDER = Target.the("boton Place Order")
            .locatedBy("//button[text()='Place Order']");

    // Formulario de compra
    public static final Target CAMPO_NOMBRE = Target.the("campo Name").locatedBy("#name");
    public static final Target CAMPO_PAIS = Target.the("campo Country").locatedBy("#country");
    public static final Target CAMPO_CIUDAD = Target.the("campo City").locatedBy("#city");
    public static final Target CAMPO_TARJETA = Target.the("campo Credit card").locatedBy("#card");
    public static final Target CAMPO_MES = Target.the("campo Month").locatedBy("#month");
    public static final Target CAMPO_ANIO = Target.the("campo Year").locatedBy("#year");
    public static final Target PURCHASE = Target.the("boton Purchase")
            .locatedBy("//button[text()='Purchase']");

    // Confirmacion
    public static final Target MENSAJE_COMPRA = Target.the("mensaje de confirmacion")
            .locatedBy(".sweet-alert h2");
    public static final Target DETALLE_COMPRA = Target.the("detalle de la compra")
            .locatedBy(".sweet-alert p");
    public static final Target BOTON_OK = Target.the("boton OK")
            .locatedBy(".sweet-alert .confirm");
}
