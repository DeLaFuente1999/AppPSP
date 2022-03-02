package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AnalyzeFile {
    public static int getNumberOfRows(String file) {
        BufferedReader br;
        int lines = 0;
        try {
            br = new BufferedReader(new FileReader(file));
            while (br.readLine() != null) lines++;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static int getNumberOfWords(String file) throws IOException {
        int lines = AnalyzeFile.getNumberOfRows(file);
        String phrases[] = new String[lines];
        List<String> words = null;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        int count = 0;
        // Obtener las lineas del fichero "usuarios.txt"
        while (line != null) {
            phrases[count] = line;
            line = br.readLine();
            count++;
        }

        for (int i = 0; i < phrases.length; i++) {
            String[] phrase = phrases[i].split("\\|");
            words = Arrays.asList(phrase);
        }

        return words.size();
    }
}
