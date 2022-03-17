package app;

import utilities.LogUtilites;
import utilities.ThreadServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Servidor extends Thread {

    private static final int PORT = 9500;

    public static void main(String[] args) {


        try {
            ServerSocket server = new ServerSocket(PORT);
            LogUtilites.AddLogLine("SERVIDOR INICIADO");
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
