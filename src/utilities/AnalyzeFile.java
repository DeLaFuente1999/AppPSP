package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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


}
