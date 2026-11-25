package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class MensajeDeCompra implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return actor.recall("mensajeCompra");
    }

    public static MensajeDeCompra confirmacion() {
        return new MensajeDeCompra();
    }
}