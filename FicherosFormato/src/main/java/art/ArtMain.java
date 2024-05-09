package art;

import com.google.gson.Gson;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ArtMain {
    public static void main(String[] args) throws IOException {
        final Gson gson = new Gson();

        // URL del JSON de la obra "Starry Night and the Astronauts"
        URL artworkUrl = new URL("https://api.artic.edu/api/v1/artworks/129884");

        // Leer y procesar el JSON
        BufferedReader in = new BufferedReader(new InputStreamReader(artworkUrl.openStream(), StandardCharsets.UTF_8));
        ArtworkResponse response = gson.fromJson(in, ArtworkResponse.class);

        // Mostrar menú
        System.out.println("0. Exit | 1. Mostrar detalles de la obra");
        System.out.println("2. Consultar Galería | 3. Consultar Medio");
        System.out.println("4. Consultar Lugar de Origen | 5. Consultar Dimensiones");
        System.out.println("6. Abrir HTML");

        // Leer opción del usuario
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingrese el número de opción: ");
        String option = reader.readLine();

        switch (option) {
            case "0":
                System.out.println("Saliendo...");
                break;
            case "1":
                displayArtworkDetails(response.data, response.config);
                break;
            case "2":
                System.out.println("Galería: " + response.data.gallery_title);
                break;
            case "3":
                System.out.println("Medio: " + response.data.medium_display);
                break;
            case "4":
                System.out.println("Lugar de Origen: " + response.data.place_of_origin);
                break;
            case "5":
                System.out.println("Dimensiones: " + response.data.dimensions);
                break;
            case "6":
                openHTML("file:///home/INFORMATICA/alu10187210/IdeaProjects/FicherosFormato/src/main/java/art/index.html");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void displayArtworkDetails(Artwork artwork, Config config) {
        System.out.println("Título: " + artwork.title);
        System.out.println("Artista: " + artwork.artist_display);
        System.out.println("Año de creación: " + artwork.date_display);
        System.out.println("Descripción: " + artwork.description);
        System.out.println("Lugar de origen: " + artwork.place_of_origin);
        System.out.println("Dimensiones: " + artwork.dimensions);
        System.out.println("Medio: " + artwork.medium_display);
        System.out.println("Galería: " + artwork.gallery_title);
        System.out.println("URL Imagen: " + config.iiif_url + "/" + artwork.image_id + "/full/843,/0/default.jpg");
    }

    private static void openHTML(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ArtworkResponse {
    Artwork data;
    Config config;
}

class Config {
    String iiif_url;
    String website_url;
}

class Artwork {
    String title;
    String artist_display;
    String date_display;
    String description;
    String place_of_origin;
    String dimensions;
    String medium_display;
    String gallery_title;
    String image_id;
}
