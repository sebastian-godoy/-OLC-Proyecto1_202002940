package Analizadores;
import java.io.*;
import java.security.SecureRandom;

public class DotToPngConverter {
   
        
    
    
    
    
    
    public static void convertToPng(String dotCode, String filename) throws IOException, InterruptedException {
        // Generar numeros aleatorios irrepetibles para nombrar los archivos
        SecureRandom rand = new SecureRandom();
        int upperbound = 1000;  
        String path = "";
        int counter = rand.nextInt(upperbound);     
        if (counter == 0) {
            if (filename == "Arbol"){
                path = "./ARBOLES_202002940/";
            } else if (filename == "TablaSiguientes") {
                path = "./SIGUIENTES_202002940/";
            }
            // Escribir codigo a un archivo dot
            File dotFile = new File(path + filename + counter +".dot");
            FileWriter dotWriter = new FileWriter(dotFile);
            dotWriter.write(dotCode);
            dotWriter.close();
            
            // Generar png del archivo dot
            ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tpng", "-o", path + filename + ".png", path + filename + ".dot");
            Process process = processBuilder.start();
            process.waitFor();
        } else {
            if (filename == "Arbol"){
                path = "./ARBOLES_202002940/";
            } else if (filename == "TablaSiguientes") {
                path = "./SIGUIENTES_202002940/";
            }
            // Escribir codigo a un archivo dot
            counter += 1;
            File dotFile = new File(path + filename + counter +".dot");
            FileWriter dotWriter = new FileWriter(dotFile);
            dotWriter.write(dotCode);
            dotWriter.close();
            
            // Generar png del archivo dot
            ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tpng", "-o", path + filename + counter + ".png", path +filename + counter + ".dot");
            Process process = processBuilder.start();
            process.waitFor();
        }
        
        
        
    }
}