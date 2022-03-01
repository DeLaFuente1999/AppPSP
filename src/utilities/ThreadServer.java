package utilities;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadServer extends Thread{

    private static final String fileUsers = "D:\\CURSO21-22\\PSP\\PROJECTS\\AppPSP\\ficheros\\usuarios.txt";
    private static Socket client;
    private static DataInputStream dis;
    private static ObjectOutputStream oos;

    public ThreadServer(Socket client) {
        this.client = client;
    }

    public static void listClients() {
        BufferedReader br;
        int lines;
        int count = 0;
        List<String> words;

        try {
            br = new BufferedReader(new FileReader(fileUsers));
            lines = getNumberOfRows(fileUsers);
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

    /***
     * Clase para obtener el número de filas que hay en un fichero
     * @param file
     * @return int con el numero de lineas
     */
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

    public void run() {
        String clientOption;
        try {
            dis = new DataInputStream(client.getInputStream());
            System.out.println("CLIENTE: " + client.toString() + " CONECTADO.");

            clientOption = dis.readUTF();
            if (!clientOption.equals("3")) {
                switch (clientOption) {
                    case "1":
                        System.out.println("El cliente ha seleccionado: LISTAR CLIENTES");
                        listClients();
                        break;
                    case "2":
                        System.out.println("El cliente ha seleccionado: CONSULTAR SALDO");
                        break;
                }
            }
            System.out.println("CLIENTE: " + client.toString() + " DESCONECTADO.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
