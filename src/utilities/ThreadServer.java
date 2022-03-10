package utilities;

import java.io.*;
import java.net.Socket;

public class ThreadServer extends Thread {


    private static Socket client;
    private static DataInputStream dis;
    private static ObjectOutputStream oos;
    private static DataOutputStream dos = null;

    public ThreadServer(Socket client) {
        this.client = client;
    }

    public void run() {
        int clientOption;

        try {
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());
            System.out.println("CLIENTE: " + client.toString() + " CONECTADO.");

            clientOption = dis.readInt();

            while (clientOption != 3 ) {
                switch (clientOption) {
                    case 1:
                        System.out.println("El cliente ha seleccionado: LISTAR CLIENTES");
                        ListClients.listClients(client);
                        break;
                    case 2:
                        System.out.println("El cliente ha seleccionado: CONSULTAR SALDO");
                        String dni;
                        Boolean result = ListBalance.CheckUserPwd(dni = dis.readUTF(),dis.readUTF(), client);
                        if (result) {
                            ListBalance.ReadUserBalance(dni, client);
                        }
                        break;
                }

                clientOption = dis.readInt();

            }
            System.out.println("CLIENTE: " + client.toString() + " DESCONECTADO.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
