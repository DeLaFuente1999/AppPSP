package app;

import menu.menu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {

    private static final int PROGRAMPORT = 9500;
    private static ServerSocket server = null;
    private static Socket client = null;
    private static DataOutputStream dos = null;
    private static DataInputStream dis = null;

    public static void main(String[] args) {
        String ipConection = args[0];
        String conectionPort = args[1];
        String menuResult = String.valueOf(menu.MenuOptions());

        if (menuResult.equals("1") || menuResult.equals("2")) {
            try {
                client = new Socket(ipConection, Integer.parseInt(conectionPort));

                // Enviar datos al servidor
                dos = new DataOutputStream(client.getOutputStream());
                String data = menuResult;
                dos.writeUTF(data);

                // Recoger datos del servidor
                dis = new DataInputStream(client.getInputStream());
                System.out.println("O server devolve: " + dis.readUTF());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (menuResult.equals("3")) {
            System.exit(0);
        }
    }
}
