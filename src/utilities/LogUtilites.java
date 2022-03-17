package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtilites {

    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static LocalDateTime now = LocalDateTime.now();
    public static File file = null;
    public static BufferedWriter bw;

    public static void AddLogLine(String message) {

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();


        file = new File(s + "\\src\\log\\server.log");

        try {
            bw = new BufferedWriter(new FileWriter(file, true));

            if (message.equals("SERVIDOR INICIADO")) {
                bw.write("\r\n" + dtf.format(now) + "\t" + message + "\r\n");
            } else {
                bw.write(dtf.format(now) + "\t" + message + "\r\n");
            }

            String salida = dtf.format(now) + "\t" + message;
            System.out.println(salida);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
