package app;

import utilities.ThreadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final int PORT = 9501;
    private static ServerSocket server = null;
    private static Socket client = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(PORT);
            System.out.println("SERVIDOR INICIADO...");
            while (true) {
                client = server.accept();
                ThreadServer thread = new ThreadServer(client);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
