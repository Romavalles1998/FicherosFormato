package ejercicios_roma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TransformaImagen {
    private File f;

    public TransformaImagen(File fEnt) {
        if (fEnt.exists() && fEnt.getName().toLowerCase().endsWith(".bmp")) {
            this.f = fEnt;
        } else {
            System.err.println("Error: El archivo no existe o no es un archivo .bmp");
        }
    }

    public void transformaNegativo() throws IOException {
        if (this.f == null) return;
       
        String outputFileName = getNombreSinExtension() + "_n.bmp";
        try (FileInputStream in = new FileInputStream(f);
             FileOutputStream out = new FileOutputStream(outputFileName)) {
           
            byte[] header = new byte[54];
            in.read(header);
            out.write(header);

            int b;
            while ((b = in.read()) != -1) {
                out.write(255 - b);
            }
        }
    }

    public void transformaOscuro() throws IOException {
        if (this.f == null) return;

        String outputFileName = getNombreSinExtension() + "_o.bmp";
        try (FileInputStream in = new FileInputStream(f);
             FileOutputStream out = new FileOutputStream(outputFileName)) {
           
            byte[] header = new byte[54];
            in.read(header);
            out.write(header);

            int b;
            while ((b = in.read()) != -1) {
                out.write(b / 2);
            }
        }
    }

    public void transformaBlancoNegro() throws IOException {
        if (this.f == null) return;

        String outputFileName = getNombreSinExtension() + "_bn.bmp";
        try (FileInputStream in = new FileInputStream(f);
             FileOutputStream out = new FileOutputStream(outputFileName)) {
           
            byte[] header = new byte[54];
            in.read(header);
            out.write(header);

            int r, g, b, gray;
            while ((r = in.read()) != -1 && (g = in.read()) != -1 && (b = in.read()) != -1) {
                gray = (r + g + b) / 3;
                out.write(gray);
                out.write(gray);
                out.write(gray);
            }
        }
    }

    private String getNombreSinExtension() {
        String fileName = f.getName();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0 && pos < (fileName.length() - 1)) {
            return fileName.substring(0, pos);
        }
        return fileName;
    }
}