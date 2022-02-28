package utilities;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreadServer extends Thread{

    private static final String fileUsers = "D:\\CURSO21-22\\PSP\\PROJECTS\\AppPSP\\ficheros\\usuarios.txt";
    private static Socket client;
    private DataInputStream dis;
    private static DataOutputStream dos;
    public ThreadServer(Socket client) {
        this.client = client;
    }

    public static void listClients() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileUsers));
            int lines = getNumberOfRows(fileUsers);
            String phrases[] = new String[lines];
            ArrayList<String> words = new ArrayList<>();
            int cont = 0;
            String line = br.readLine();
            // OBTENER LINEAS FICHERO
            while (line != null) {
                phrases[cont] = line;
                line = br.readLine();
                cont++;
            }

            for (int i = 0; i < phrases.length; i++) {
                words.add(Arrays.toString(phrases[i].split("\\|")));
            }

            dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF(words.get(1));

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static int getNumberOfRows(String fileUsers) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(fileUsers));
        int lines = 0;
        try {
            while (br.readLine() != null) lines++;
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void run() {
        try {
            dis = new DataInputStream(client.getInputStream());
            System.out.println("CLIENTE: " + client.toString() + " CONECTADO.");
            String clientOption = dis.readUTF();
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
