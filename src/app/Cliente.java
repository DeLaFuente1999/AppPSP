package app;

import menu.menu;
import utilities.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {

    private static final String PROGRAMPORT = "9501";
    private static ServerSocket server = null;
    private static Socket client = null;
    private static DataOutputStream dos = null;
    private static ObjectInputStream ois = null;

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String ipConection = args[0];
        String conectionPort = PROGRAMPORT;
        String menuResult = "0";
        client = new Socket(ipConection, Integer.parseInt(conectionPort));
        while (!menuResult.equals("3")) {
            try {
                menuResult = String.valueOf(menu.MenuOptions());
                // Enviar datos al servidor
                dos = new DataOutputStream(client.getOutputStream());
                String data = menuResult;
                dos.writeUTF(data);

                // Recoger datos del servidor
                //dis = new DataInputStream(client.getInputStream());
                ois = new ObjectInputStream(client.getInputStream());
                Usuario[] arrayUsr = (Usuario[]) ois.readObject();
                System.out.println("Lista de Usuarios: ");
                for (int i = 0; i < arrayUsr.length; i++) {
                    System.out.println((i + 1) + ".- " + arrayUsr[i].getName());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        client.close();
        dos.close();
        ois.close();
    }
}
