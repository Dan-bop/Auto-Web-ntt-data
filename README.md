# AutoWeb - Automatización web con Serenity BDD (Demoblaze)

Framework básico con **Java 17 + Maven + Selenium + Serenity Screenplay + Cucumber** que automatiza
el flujo de compra de https://www.demoblaze.com/.

## Alcance
- Escenario positivo: agregar 3 productos de categorías distintas (Phones, Laptops, Monitors),
  validar carrito y total, eliminar un producto y confirmar la compra.
- Escenario negativo: intentar comprar con el formulario vacío.

## Estructura
```
src/test/java/org/example
├── Utils/            ScreenShot, Parameters, Esperas
├── ui/               Targets (locators): HomePage, ProductPage, CartPage
├── tasks/            Acciones del usuario (AbrirTienda, AgregarProducto, ...)
├── questions/        Consultas de estado (ProductosEnCarrito, TotalDelCarrito, ...)
├── stepdefinitions/  Hooks y DemoblazeSteps
└── runners/          RunnerTest
src/test/resources/features/compra_demoblaze.feature
evidencias/           Capturas guardadas durante la ejecución
```

## Requisitos
- JDK 17, Maven 3.8+, Google Chrome actualizado (Selenium Manager descarga el driver).

## Ejecución
```
mvn clean verify
```
Un solo escenario: `mvn clean verify -Dcucumber.filter.tags="@CompraNegativa"`

## Evidencia
- **Capturas**: carpeta `evidencias/` (archivos `evidencia_<fecha>_<paso>.png`).
- **Reporte Serenity**: `target/site/serenity/index.html` (escenarios, pasos, screenshots, estado final).

## Notas
- Los datos de prueba están en el `.feature`. La tarjeta es ficticia (Demoblaze es un sitio de demo).
- Cambios al pom del profesor: se quitó el plugin serenity duplicado y los plugins PMD/SpotBugs
  (fallan sin `findbugs-exclude.xml`), y se agregó `commons-io`, que usa `ScreenShot`.
