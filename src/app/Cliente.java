package app;

import menu.menu;
import utilities.Balance;
import utilities.Usuario;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Cliente {

    private static final String PROGRAMPORT = "9503";
    private static Socket client = null;
    private static DataOutputStream dos = null;
    private static ObjectInputStream ois = null;
    private static DataInputStream dis = null;

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        String ipConection = args[0];
        String conectionPort = PROGRAMPORT;
        client = new Socket(ipConection, Integer.parseInt(conectionPort));

        int menuResult = menu.MenuOptions();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        try {
                while (menuResult != 3) {

                    // Enviar datos al servidor
                    dos = new DataOutputStream(client.getOutputStream());
                    dos.writeInt(menuResult);


                    if (menuResult == 1) {
                        // Recoger datos del servidor
                        ois = new ObjectInputStream(client.getInputStream());
                        Usuario[] arrayUsr = (Usuario[]) ois.readObject();
                        System.out.println("Lista de Usuarios: ");

                        for (int i = 0; i < arrayUsr.length; i++) {
                            System.out.println((i + 1) + ".- " + arrayUsr[i].getName());
                        }
                        System.out.println("");

                    } else if (menuResult == 2) {


                        String user;
                        String passw;

                        System.out.println("Introduzca DNI: ");
                        user = br.readLine();
                        System.out.println("Introduzca contraseña: ");
                        passw = br.readLine();


                        // Enviar datos al servidor
                        System.out.println();
                        dos.writeUTF(user);
                        dos.writeUTF(passw);


                        // Recoger datos del servidor

                        ois = new ObjectInputStream(client.getInputStream());

                        Balance balance = (Balance) ois.readObject();
                        System.out.println(balance.toString());

                        System.out.println("");
                    }
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
