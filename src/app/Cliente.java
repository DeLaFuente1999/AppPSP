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
    private static Socket client = null;
    private static DataOutputStream dos = null;
    private static ObjectInputStream ois = null;

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        String ipConection = args[0];
        String conectionPort = PROGRAMPORT;
        client = new Socket(ipConection, Integer.parseInt(conectionPort));

        int menuResult = menu.MenuOptions();


        try {
                while (menuResult != 3) {

                    // Enviar datos al servidor
                    dos = new DataOutputStream(client.getOutputStream());
                    dos.writeInt(menuResult);

                    // Recoger datos del servidor
                    ois = new ObjectInputStream(client.getInputStream());
                    Usuario[] arrayUsr = (Usuario[]) ois.readObject();
                    System.out.println("Lista de Usuarios: ");

                    for (int i = 0; i < arrayUsr.length; i++) {
                        System.out.println((i + 1) + ".- " + arrayUsr[i].getName());
                    }
                    System.out.println("");
                    menuResult = menu.MenuOptions();
                }
                dos.writeInt(3);

        } catch (IOException e) {
            e.printStackTrace();
        }
        client.close();
        dos.close();
        ois.close();
    }
}
