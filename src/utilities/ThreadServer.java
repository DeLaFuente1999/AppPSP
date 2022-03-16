package utilities;

import java.io.*;
import java.net.Socket;

public class ThreadServer extends Thread {


    private Socket client;
    private DataInputStream dis;
    private ObjectOutputStream oos;
    private DataOutputStream dos = null;

    public ThreadServer(Socket client) {
        this.client = client;
        
		try {
			this.dis = new DataInputStream(client.getInputStream());
			this.dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {			
			e.printStackTrace();
			System.out.println("no voy");
		}
		
    }

    public void run() {
        int clientOption;

        try {
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
