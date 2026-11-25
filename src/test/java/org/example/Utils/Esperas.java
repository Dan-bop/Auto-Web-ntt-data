package org.example.Utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Esperas {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static String aceptarAlerta(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        String texto = alerta.getText();
        alerta.accept();
        return texto;
    }

    public static void desapareceTexto(WebDriver driver, String texto) {
        new WebDriverWait(driver, TIMEOUT).until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//tbody[@id='tbodyid']//td[text()='" + texto + "']")));
    }

    public static String nombreArchivo(String texto) {
        return texto.trim().replaceAll("[^a-zA-Z0-9]+", "_");
    }
}
