package utilities;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadServer extends Thread{


    private static Socket client;
    private static DataInputStream dis;
    private static ObjectOutputStream oos;

    public ThreadServer(Socket client) {
        this.client = client;
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
                        ListClients.listClients(oos, client);
                        break;
                    case "2":
                        System.out.println("El cliente ha seleccionado: CONSULTAR SALDO");
                        break;
                    case "3":
                        System.out.println("CLIENTE: " + client.toString() + " DESCONECTADO.");
                        client.close();
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
