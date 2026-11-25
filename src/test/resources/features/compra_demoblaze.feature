@Demoblaze
Feature: Compra de productos en Demoblaze
Como cliente de la tienda
Quiero agregar productos de varias categorías, eliminar uno y confirmar mi compra
Para recibir la confirmación de mi pedido

@Regresion @CompraExitosa
Scenario: Comprar productos de distintas categorías, eliminar uno y confirmar
Given que el cliente abre la tienda Demoblaze
When agrega el producto "Samsung galaxy s6" de la categoria "Phones"
And agrega el producto "MacBook air" de la categoria "Laptops"
And agrega el producto "Apple monitor 24" de la categoria "Monitors"
And va al carrito de compras
Then el carrito muestra 3 productos
And el total del carrito coincide con la suma de los precios
When elimina el producto "MacBook air" del carrito
Then el carrito muestra 2 productos
And el producto "MacBook air" ya no esta en el carrito
And el total del carrito coincide con la suma de los precios
When confirma la compra con los siguientes datos:
| nombre  | Danner Loyola    |
| pais    | Peru             |
| ciudad  | Trujillo         |
| tarjeta | 4111111111111111 |
| mes     | 11               |
| anio    | 2026             |
Then ve el mensaje de compra "Thank you for your purchase!"

@Regresion @CompraNegativa
Scenario: No se puede comprar con el formulario vacío
Given que el cliente abre la tienda Demoblaze
When agrega el producto "Samsung galaxy s6" de la categoria "Phones"
And va al carrito de compras
And intenta comprar sin llenar el formulario
Then el sistema muestra la alerta "Please fill out Name and Creditcard."