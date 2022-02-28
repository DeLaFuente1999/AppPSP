package app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final int PORT = 9500;
    private static ServerSocket server = null;
    private static Socket client = null;
    private static DataOutputStream dos = null;
    private static DataInputStream dis = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            System.out.println("SERVIDOR INICIADO...");
            while (true) {
                client = server.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
