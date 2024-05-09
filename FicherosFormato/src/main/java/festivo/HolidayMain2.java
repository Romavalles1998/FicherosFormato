package festivo;

import com.google.gson.Gson;
import festivo.Holiday;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HolidayMain2 {
    public static void main(String[] args) throws IOException {
        final Gson gson = new Gson();

        // URL del JSON de los días festivos en Austria 2024 usando Nager.Date v3
        URL holidaysUrl = new URL("https://date.nager.at/api/v3/PublicHolidays/2024/AT");

        // Leer y procesar el JSON
        BufferedReader in = new BufferedReader(new InputStreamReader(holidaysUrl.openStream(), StandardCharsets.UTF_8));
        Holiday[] holidays = gson.fromJson(in, Holiday[].class);

        // Crear el contenido del HTML
        StringBuilder htmlContentBuilder = new StringBuilder();
        htmlContentBuilder.append("""
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Austrian Holidays</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f8f8f8;
                        margin: 0;
                        padding: 0;
                    }
                    
                    .container {
                        max-width: 800px;
                        margin: 20px auto;
                        padding: 20px;
                        background-color: rgba(255, 204, 204, 0.8); /* Rojo claro con transparencia */
                        border-radius: 10px;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    }
                    
                    h1 {
                        text-align: center;
                        color: #333;
                    }
                    
                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-top: 20px;
                    }
                    
                    th, td {
                        padding: 10px;
                        text-align: center;
                        border: 1px solid #ddd;
                    }
                    
                    th {
                        background-color: #fff;
                        color: #333;
                    }
                    
                    img {
                        max-width: 100px;
                        max-height: 100px;
                        border-radius: 5px;
                    }
                </style>
            </head>
            <body>
            
            <div class="container">
                <h1>Austrian Holidays</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Imagen</th>
                        </tr>
                    </thead>
                    <tbody>
            """);

        // Agregar filas de la tabla con las imágenes
        String[] imageNames = {"ascension.jpeg", "asu.jpeg", "austria.jpeg", "concepcion.jpg", "corpus.jpg", "easter.jpg", "epi.jpg", "national.jpeg", "stephan.jpg", "xmas.jpg", "saints.webp", "whit.webp"};
        for (String imageName : imageNames) {
            String displayName = getImageDisplayName(imageName);
            htmlContentBuilder.append("""
                        <tr>
                            <td>%s</td>
                            <td><img src="%s" alt="%s"></td>
                        </tr>
                    """.formatted(displayName, "file:///home/INFORMATICA/alu10187210/IdeaProjects/FicherosFormato/src/main/java/festivo/" + imageName, displayName));
        }

        // Cerrar el HTML
        htmlContentBuilder.append("""
                    </tbody>
                </table>
            </div>
            
            </body>
            </html>
            """);

        // Escribir el contenido en un archivo
        try (FileWriter fileWriter = new FileWriter("index.html")) {
            fileWriter.write(htmlContentBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Abrir el archivo en el navegador
        openHTML("index.html");
    }

    private static String getImageDisplayName(String imageName) {
        // Mapeo de nombres de imágenes a nombres de festividades
        switch (imageName) {
            case "ascension.jpeg":
                return "Día de Ascensión";
            case "asu.jpeg":
                return "Asunción de María";
            case "austria.jpeg":
                return "Día Nacional de Austria";
            case "concepcion.jpg":
                return "Inmaculada Concepción";
            case "corpus.jpg":
                return "Cuerpo de Cristo";
            case "easter.jpg":
                return "Pascua";
            case "epi.jpg":
                return "Epifanía";
            case "national.jpeg":
                return "Día Nacional de Austria";
            case "stephan.jpg":
                return "San Esteban";
            case "xmas.jpg":
                return "Navidad";
            case "saints.webp":
                return "Día de Todos los Santos";
            case "whit.webp":
                return "Día de la Ascensión de Jesús";
            default:
                return "";
        }
    }

    private static void openHTML(String url) {
        try {
            Desktop.getDesktop().browse(new File(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
