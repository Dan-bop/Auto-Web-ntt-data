package org.example.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Tasks;
import org.example.Utils.Esperas;
import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

public class TomarEvidencia implements Task {

    private final String nombre;

    public TomarEvidencia(String nombre) {
        this.nombre = nombre;
    }

    @Override
    @Step("{0} guarda evidencia: #nombre")
    public <T extends Actor> void performAs(T actor) {
        ScreenShot.createFolder(Parameters.CARPETA_EVIDENCIAS);
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(actor).getDriver(),
                "/" + Parameters.CARPETA_EVIDENCIAS,
                Esperas.nombreArchivo(nombre));
    }

    public static TomarEvidencia de(String nombre) {
        return Tasks.instrumented(TomarEvidencia.class, nombre);
    }
}
