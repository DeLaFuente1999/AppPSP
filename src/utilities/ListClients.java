package utilities;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListClients {

    private static final String fileUsers = "ficheros/usuarios.txt";

    public static void listClients(ObjectOutputStream oos, Socket client) {

        BufferedReader br;
        int lines;
        int count = 0;
        List<String> words;
        File usersFile = new File(fileUsers);

        try {
            br = new BufferedReader(new FileReader(usersFile));
            lines = AnalyzeFile.getNumberOfRows(fileUsers);
            String phrases[] = new String[lines];

            String line = br.readLine();
            // Obtener las lineas del fichero "usuarios.txt"
            while (line != null) {
                phrases[count] = line;
                line = br.readLine();
                count++;
            }

            Usuario[] arrayUsr = new Usuario[lines];
            for (int i = 0; i < phrases.length; i++) {
                String[] phrase = phrases[i].split("\\|");
                words = Arrays.asList(phrase);

                arrayUsr[i] = new Usuario(words.get(0), words.get(1), words.get(2));
            }

            // Enviar los nombres de usuario al cliente
            oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(arrayUsr);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
