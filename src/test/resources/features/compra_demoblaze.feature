@Demoblaze
Feature: Compra de productos en Demoblaze
  Como cliente de la tienda
  Quiero gestionar mi carrito y confirmar mis compras
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

  @Regresion @AgregarPorCategoria
  Scenario Outline: Agregar un producto de cada categoría al carrito
    Given que el cliente abre la tienda Demoblaze
    When agrega el producto "<producto>" de la categoria "<categoria>"
    And va al carrito de compras
    Then el carrito muestra 1 productos
    And el carrito contiene el producto "<producto>"

    Examples:
      | categoria | producto          |
      | Phones    | Samsung galaxy s6 |
      | Laptops   | MacBook air       |
      | Monitors  | Apple monitor 24  |

  @Regresion @ProductoRepetido
  Scenario: Agregar el mismo producto dos veces
    Given que el cliente abre la tienda Demoblaze
    When agrega el producto "Nokia lumia 1520" de la categoria "Phones"
    And agrega el producto "Nokia lumia 1520" de la categoria "Phones"
    And va al carrito de compras
    Then el carrito muestra 2 productos
    And el total del carrito coincide con la suma de los precios

  @Regresion @CompraNegativa
  Scenario: No se puede comprar con el formulario vacío
    Given que el cliente abre la tienda Demoblaze
    When agrega el producto "Samsung galaxy s6" de la categoria "Phones"
    And va al carrito de compras
    And intenta comprar sin llenar el formulario
    Then el sistema muestra la alerta "Please fill out Name and Creditcard."

  @Regresion @CompraNegativa
  Scenario Outline: No se puede comprar con datos obligatorios faltantes
    Given que el cliente abre la tienda Demoblaze
    When agrega el producto "Sony vaio i5" de la categoria "Laptops"
    And va al carrito de compras
    And intenta comprar con los siguientes datos:
      | nombre  | <nombre>  |
      | pais    | Peru       |
      | ciudad  | Trujillo   |
      | tarjeta | <tarjeta>  |
      | mes     | 11         |
      | anio    | 2026       |
    Then el sistema muestra la alerta "Please fill out Name and Creditcard."

    Examples:
      | nombre        | tarjeta          |
      |               | 4111111111111111 |
      | Danner Loyola |                  |