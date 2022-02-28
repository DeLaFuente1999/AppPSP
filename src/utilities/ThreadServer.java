package utilities;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreadServer extends Thread{

    private static final String fileUsers = "D:\\CURSO21-22\\PSP\\PROJECTS\\AppPSP\\ficheros\\usuarios.txt";
    private static Socket client;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    public ThreadServer(Socket client) {
        this.client = client;
    }

    public static void listClients() {
        BufferedReader br;
        int lines;
        int count = 0;
        ArrayList<String> words;

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
            // Separar las lineas por el separador "|"
            for (int i = 0; i < phrases.length; i++) {
                words.add(Arrays.toString(phrases[i].split("\\|")));
            }

            // Enviar los nombres de usuario al cliente
            dos = new DataOutputStream(client.getOutputStream());
            for (int i = 0; i < words.size(); i += 3) {
                dos.writeUTF(words.get(i));
            }

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
