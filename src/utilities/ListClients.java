package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListClients {

    //private static final String fileUsers = "D:\\CURSO21-22\\PSP\\PROJECTS\\AppPSP\\ficheros\\usuarios.txt";
    private static final String fileUsers = "E:\\Proyectos\\ProyectoPSP\\ficheros\\usuarios.txt";

    public static void listClients(ObjectOutputStream oos, Socket client) {

        BufferedReader br;
        int lines;
        int count = 0;
        List<String> words;

        try {
            br = new BufferedReader(new FileReader(fileUsers));
            lines = AnalyzeFile.getNumberOfRows(fileUsers);
            String phrases[] = new String[lines];

            String line = br.readLine();
            // Obtener las lineas del fichero "usuarios.txt"
            while (line != null) {
                phrases[count] = line;
                line = br.readLine();
                count++;
            }

            words = new ArrayList<>();
            Usuario[] arrayUsr = new Usuario[lines*3];
            for (int i = 0; i < phrases.length; i++) {
                String[] phrase = phrases[i].split("\\|");
                words = Arrays.asList(phrase);

                arrayUsr[i] = new Usuario(words.get(0), words.get(1), words.get(2));

            }

            // Separar las lineas por el separador "|"


            // Enviar los nombres de usuario al cliente
            oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(arrayUsr);


            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
