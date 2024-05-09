package ejercicios_roma;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            // Obtener el directorio raíz del sistema
            File f = File.listRoots()[0];
            Scanner sc = new Scanner(System.in);
            int opcion = -1;
            do {
                // Mostrar los ficheros y directorios del directorio actual
                System.out.println("Lista de ficheros y directorios del directorio: " + f.getCanonicalPath());
                System.out.println("--------------------------------------------");

                // Opción para ir al directorio padre
                System.out.println("0 - Directorio padre");
                // Listar el contenido del directorio actual
                File[] files = f.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile())
                        System.out.println((i + 1) + ".- " + files[i].getName() + " " + files[i].length() + " bytes");
                    if (files[i].isDirectory())
                        System.out.println((i + 1) + ".- " + files[i].getName() + " <Directorio>");
                }
                // Leer la opción del usuario
                try {
                    opcion = sc.nextInt();
                    // Controlar que la opción es un número válido
                    if (opcion != -1) {
                        if (opcion == 0) {
                            // Opción para subir al directorio padre
                            if (f.getParentFile() != null && f.getParentFile().canRead()) {
                                f = f.getParentFile();
                            }
                        } else if (opcion > 0 && opcion <= files.length) {
                            // Opción para cambiar al directorio o archivo seleccionado
                            File selectedFile = files[opcion - 1];
                            if (selectedFile.isDirectory() && selectedFile.canRead()) {
                                f = selectedFile;
                            } else if (selectedFile.isFile()) {
                                System.out.println("El elemento seleccionado es un archivo y no se puede cambiar a él.");
                            }
                        } else {
                            System.out.println("Número no válido, por favor intente de nuevo.");
                        }
                    }
                } catch (InputMismatchException ime) {
                    // Manejar el error si el usuario no introduce un número
                    System.out.println("Introduzca sólo números");
                    sc.nextLine();
                }
            } while (opcion != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
