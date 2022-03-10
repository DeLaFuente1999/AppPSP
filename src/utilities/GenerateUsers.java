package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GenerateUsers {

    private static final String fileUsers = "ficheros/usuarios.txt";

    public static Usuario[] Generate() {

        BufferedReader br = null;
        int lines;
        int count = 0;
        List<String> words;
        File usersFile = new File(fileUsers);
        Usuario[] arrayUsr = new Usuario[0];

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

            arrayUsr = new Usuario[lines];
            for (int i = 0; i < phrases.length; i++) {
                String[] phrase = phrases[i].split("\\|");
                words = Arrays.asList(phrase);

                arrayUsr[i] = new Usuario(words.get(0), words.get(1), words.get(2));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayUsr;
    }


}
