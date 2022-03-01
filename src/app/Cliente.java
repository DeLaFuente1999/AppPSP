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

    private static final int PROGRAMPORT = 9500;
    private static ServerSocket server = null;
    private static Socket client = null;
    private static DataOutputStream dos = null;
    private static ObjectInputStream ois = null;

    public static void main(String[] args) throws IOException, ClassNotFoundException{
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
                //dis = new DataInputStream(client.getInputStream());
                ois = new ObjectInputStream(client.getInputStream());
                Usuario[] arrayUsr = (Usuario[]) ois.readObject();
                System.out.println("Lista de Usuarios: ");
                for (int i = 0; i < arrayUsr.length / 3; i++) {
                    System.out.println((i + 1) + ".- " + arrayUsr[i].getName());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (menuResult.equals("3")) {
            System.exit(0);
        }
    }
}
