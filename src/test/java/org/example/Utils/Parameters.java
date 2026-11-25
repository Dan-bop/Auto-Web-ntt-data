package org.example.Utils;

import java.io.File;

public class Parameters {
    // Carpeta raiz donde se guardan las evidencias (capturas) de la ejecucion
    public static final String CARPETA_EVIDENCIAS = "evidencias";
    public static final String RutaEvidencia =
            System.getProperty("user.dir") + File.separator + CARPETA_EVIDENCIAS + File.separator;
}
