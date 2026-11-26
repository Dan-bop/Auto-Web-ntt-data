# AutoWeb - Automatización web con Serenity BDD (Demoblaze)

Framework de automatización web con **Java 17 + Maven + Selenium + Serenity Screenplay + Cucumber (Gherkin en español)**
que automatiza el flujo de compra de https://www.demoblaze.com/.

## Alcance

| Escenario | Tipo | Tag |
|---|---|---|
| Comprar productos de distintas categorías, eliminar uno y confirmar | Positivo | `@CompraExitosa` |
| Agregar un producto de cada categoría (Phones, Laptops, Monitors) | Positivo (Esquema del escenario, 3 ejemplos) | `@AgregarPorCategoria` |
| Agregar el mismo producto dos veces | Positivo | `@ProductoRepetido` |
| Comprar con el formulario vacío | Negativo | `@CompraNegativa` |
| Comprar con datos obligatorios faltantes (sin nombre / sin tarjeta) | Negativo (Esquema del escenario, 2 ejemplos) | `@CompraNegativa` |

Total: 5 escenarios, 8 ejecuciones. Todos llevan además los tags `@Demoblaze` y `@Regresion`.

## Estructura

```
src/test/java/org/example
├── Utils/            ScreenShot, Parameters, Esperas
├── ui/               Targets (locators): HomePage, ProductPage, CartPage
├── tasks/            Acciones del usuario:
│                     AbrirTienda, AgregarProducto, IrAlCarrito, EliminarProducto,
│                     ConfirmarCompra, ComprarConDatosIncompletos, TomarEvidencia
├── questions/        Consultas de estado: ProductosEnCarrito, TotalDelCarrito, MensajeDeCompra
├── stepdefinitions/  Hooks y DemoblazeSteps
└── runners/          RunnerTest
src/test/resources/features/compra_demoblaze.feature
evidencias/           Capturas guardadas durante la ejecución
serenity.properties   Configuración de Serenity y del navegador
```

## Requisitos

- JDK 17
- Maven 3.8+
- Google Chrome actualizado (Selenium Manager descarga el driver automáticamente)

## Ejecución

Todos los escenarios y reporte:

```
mvn clean verify
```

Por tag:

```
mvn clean verify -Dcucumber.filter.tags="@CompraExitosa"
mvn clean verify -Dcucumber.filter.tags="@AgregarPorCategoria"
mvn clean verify -Dcucumber.filter.tags="@CompraNegativa"
```

## Evidencia

- **Capturas**: carpeta `evidencias/`, con archivos `evidencia_<fecha>_<paso>.png` generados en los momentos clave
  (home, categoría, detalle de producto, carrito, antes/después de eliminar, formulario, confirmación o alerta).
- **Reporte Serenity**: `target/site/serenity/index.html` (escenarios, pasos, screenshots y estado final).

## Datos de prueba

Los datos están en el `.feature` (tablas y ejemplos). La tarjeta es ficticia, Demoblaze es un sitio de práctica.

## Notas técnicas

- Patrón Screenplay: Tasks (acciones), Questions (consultas), UI (locators) y Steps (Gherkin) están separados.
- La alerta JS "Product added" se acepta en `AgregarProducto` y se valida en el step.
- El mensaje de confirmación de compra se guarda antes de cerrar el popup y luego lo lee `MensajeDeCompra`.
- `ComprarConDatosIncompletos` cubre los negativos: con un mapa vacío deja el formulario en blanco.
- Cambios al pom del profesor: se quitó el plugin serenity duplicado y los plugins PMD/SpotBugs
  (fallan sin `findbugs-exclude.xml`), y se agregó `commons-io`, que usa `ScreenShot`.

## Checklist de cierre

- [x] Entorno: Java, Maven e IDE validados
- [x] Features: Gherkin claro y etiquetado
- [x] Locators: ID/CSS/XPath estables
- [x] Framework: Tasks, Questions y UI separados
- [x] Ejecución: `mvn clean verify` funciona
- [x] Reporte: Serenity report generado
- [x] README: pasos para ejecutar documentados