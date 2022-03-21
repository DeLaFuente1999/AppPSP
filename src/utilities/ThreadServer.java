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
		}
		
    }

    public void run() {
        int clientOption;

        try {
            LogUtilites.AddLogLine("CLIENTE: " + client.toString() + " CONECTADO.");

            clientOption = dis.readInt();

            while (clientOption != 3 ) {
                switch (clientOption) {
                    case 1:
                    	LogUtilites.AddLogLine("El cliente " + client.getInetAddress() + " ha seleccionado: LISTAR CLIENTES");
                        ListClients.listClients(client);
                        break; 
                    case 2:
                        LogUtilites.AddLogLine("El cliente " + client.getInetAddress() + " ha seleccionado: CONSULTAR SALDO");
                        String dni;
                        Boolean result = ListBalance.CheckUserPwd(dni = dis.readUTF(),dis.readUTF(), client);
                        if (result) {
                            ListBalance.ReadUserBalance(dni, client);
                        } else {
                        	ListBalance.ErrorUser(client);
                        }
                        break;
                }

                clientOption = dis.readInt();

            }
            LogUtilites.AddLogLine("CLIENTE: " + client.toString() + " DESCONECTADO.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
