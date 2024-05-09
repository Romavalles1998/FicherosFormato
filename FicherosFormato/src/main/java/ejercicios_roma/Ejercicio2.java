package ejercicios_roma;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        try {
            FileReader f_in = new FileReader("src/main/java/resources/f_in");
            File f = File.listRoots()[0];
            Scanner sc = new Scanner(System.in);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            int opcion = -1;
            do {
                int cont = 0;
                System.out.println("Lista de ficheros y directorios del directorio: " + f.getCanonicalPath());
                System.out.println("-------------------------------------------------");
                System.out.println("0. Directorio padre");

                File[] files = f.listFiles();
                for (File file : files) {
                    cont++;
                    String permissions = getPermissions(file);
                    String size = file.isDirectory() ? "<DIR>" : String.valueOf(file.length());
                    String modifiedDate = sdf.format(new Date(file.lastModified()));
                    System.out.printf("%d.- %s %s %s %s\n", cont, permissions, size, modifiedDate, file.getName());
                }

                try {
                    opcion = sc.nextInt();
                    if (opcion == 0 && f.getParentFile() != null && f.getParentFile().canRead()) {
                        f = f.getParentFile();
                    } else if (opcion > 0 && opcion <= files.length) {
                        File selectedFile = files[opcion - 1];
                        if (selectedFile.isDirectory() && selectedFile.canRead()) {
                            f = selectedFile;
                        } else if (selectedFile.isFile()) {
                            System.out.println("El elemento seleccionado es un archivo y no se puede cambiar a él.");
                        }
                    }
                } catch (InputMismatchException ime) {
                    System.out.println("Introduzca sólo números");
                    sc.nextLine(); // Limpiar el buffer del scanner
                }
            } while (opcion != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPermissions(File file) {
        return (file.canRead() ? "r" : "-") +
                (file.canWrite() ? "w" : "-") +
                (file.canExecute() ? "x" : "-");
    }
}

