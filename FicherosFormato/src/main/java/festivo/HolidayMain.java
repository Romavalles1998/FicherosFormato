package festivo;

import com.google.gson.Gson;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HolidayMain {
    public static void main(String[] args) throws IOException {
        final Gson gson = new Gson();

        // URL del JSON de los días festivos en Austria 2024 usando Nager.Date v3
        URL holidaysUrl = new URL("https://date.nager.at/api/v3/PublicHolidays/2024/AT");

        // Leer y procesar el JSON
        BufferedReader in = new BufferedReader(new InputStreamReader(holidaysUrl.openStream(), StandardCharsets.UTF_8));
        Holiday[] holidays = gson.fromJson(in, Holiday[].class);

        // Mostrar menú
        System.out.println("0. Exit | 1. Elegir día por nombre | 2. Elegir día por fecha | 3. URL Web");

        // Leer opción del usuario
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingrese el número de opción: ");
        String option = reader.readLine();

        switch (option) {
            case "0":
                System.out.println("Saliendo...");
                break;
            case "1":
                chooseDayByName(holidays);
                break;
            case "2":
                chooseDayByDate(holidays);
                break;
            case "3":
                openHTML("file:///home/INFORMATICA/alu10187210/IdeaProjects/FicherosFormato/src/main/java/festivo/index.html");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void chooseDayByName(Holiday[] holidays) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingrese el nombre del día festivo: ");
        String name = reader.readLine();

        // Buscar el día festivo por nombre
        boolean found = false;
        for (Holiday holiday : holidays) {
            if (holiday.name.equalsIgnoreCase(name)) {
                found = true;
                displayHolidayDetails(holiday);
                break;
            }
        }
        if (!found) {
            System.out.println("No se encontró ningún día festivo con ese nombre.");
        }
    }

    private static void chooseDayByDate(Holiday[] holidays) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Ingrese la fecha del día festivo (formato YYYY-MM-DD): ");
        String date = reader.readLine();

        // Buscar el día festivo por fecha
        boolean found = false;
        for (Holiday holiday : holidays) {
            if (holiday.date.equals(date)) {
                found = true;
                displayHolidayDetails(holiday);
                break;
            }
        }
        if (!found) {
            System.out.println("No se encontró ningún día festivo en esa fecha.");
        }
    }
    private static void openHTML(String url) {
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void displayHolidayDetails(Holiday holiday) {
        System.out.println("Nombre: " + holiday.localName);
        System.out.println("Nombre Internacional: " + holiday.name);
        System.out.println("Fecha: " + holiday.date);
        System.out.println("Región: " + (holiday.county != null ? holiday.county : "Todas las regiones"));
        System.out.println();
    }
}

