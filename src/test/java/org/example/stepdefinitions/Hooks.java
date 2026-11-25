package org.example.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

public class Hooks {

    @Before
    public void prepararEscenario() {
        ScreenShot.createFolder(Parameters.CARPETA_EVIDENCIAS);
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Danner");
    }

    @After
    public void cerrarEscenario() {
        OnStage.drawTheCurtain();
    }
}
