package org.example.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import org.example.questions.MensajeDeCompra;
import org.example.questions.ProductosEnCarrito;
import org.example.questions.TotalDelCarrito;
import org.example.tasks.AbrirTienda;
import org.example.tasks.AgregarProducto;
import org.example.tasks.ComprarSinDatos;
import org.example.tasks.ConfirmarCompra;
import org.example.tasks.EliminarProducto;
import org.example.tasks.IrAlCarrito;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoblazeSteps {

    @Dado("que el cliente abre la tienda Demoblaze")
    public void abreLaTienda() {
        OnStage.theActorInTheSpotlight().attemptsTo(AbrirTienda.demoblaze());
    }

    @Cuando("agrega el producto {string} de la categoria {string}")
    public void agregaProducto(String producto, String categoria) {
        OnStage.theActorInTheSpotlight().attemptsTo(AgregarProducto.llamado(categoria, producto));
        String alerta = OnStage.theActorInTheSpotlight().recall("alerta_agregar");
        assertThat(alerta).contains("Product added");
    }

    @Cuando("va al carrito de compras")
    public void vaAlCarrito() {
        OnStage.theActorInTheSpotlight().attemptsTo(IrAlCarrito.deCompras());
    }

    @Entonces("el carrito muestra {int} productos")
    public void carritoMuestra(int cantidad) {
        assertThat(OnStage.theActorInTheSpotlight().asksFor(ProductosEnCarrito.nombres()))
                .hasSize(cantidad);
    }

    @Entonces("el total del carrito coincide con la suma de los precios")
    public void totalCoincide() {
        int mostrado = OnStage.theActorInTheSpotlight().asksFor(TotalDelCarrito.mostrado());
        int suma = OnStage.theActorInTheSpotlight().asksFor(TotalDelCarrito.sumaDePrecios());
        assertThat(mostrado).isEqualTo(suma);
    }

    @Cuando("elimina el producto {string} del carrito")
    public void eliminaProducto(String producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(EliminarProducto.delCarrito(producto));
    }

    @Entonces("el producto {string} ya no esta en el carrito")
    public void productoNoEsta(String producto) {
        assertThat(OnStage.theActorInTheSpotlight().asksFor(ProductosEnCarrito.nombres()))
                .doesNotContain(producto);
    }

    @Cuando("confirma la compra con los siguientes datos:")
    public void confirmaCompra(DataTable tabla) {
        Map<String, String> datos = tabla.asMap(String.class, String.class);
        OnStage.theActorInTheSpotlight().attemptsTo(ConfirmarCompra.conDatos(datos));
    }

    @Entonces("ve el mensaje de compra {string}")
    public void veMensaje(String mensaje) {
        assertThat(OnStage.theActorInTheSpotlight().asksFor(MensajeDeCompra.confirmacion()))
                .isEqualTo(mensaje);
    }

    @Cuando("intenta comprar sin llenar el formulario")
    public void compraSinDatos() {
        OnStage.theActorInTheSpotlight().attemptsTo(ComprarSinDatos.formularioVacio());
    }

    @Entonces("el sistema muestra la alerta {string}")
    public void muestraAlerta(String mensaje) {
        String alerta = OnStage.theActorInTheSpotlight().recall("alerta_formulario");
        assertThat(alerta).isEqualTo(mensaje);
    }
}
