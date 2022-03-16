package app;

import utilities.ThreadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

    private static final int PORT = 9503;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("SERVIDOR INICIADO...");
            while (true) {
                Socket client = server.accept();
                ThreadServer thread = new ThreadServer(client);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
