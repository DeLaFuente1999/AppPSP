package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadServer extends Thread{

    private BufferedReader br;
    private PrintWriter bw;
    private Socket client;

    public ThreadServer(Socket client) {
        this.client = client;
        try {
            this.br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.bw = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        String clientOption = "0";

        try {
            System.out.println("CLIENTE: " + client.toString() + " CONECTADO.");
            while (!clientOption.equalsIgnoreCase("3")) {
                clientOption = br.readLine();

                switch (clientOption) {
                    case "1":
                        System.out.println("El cliente ha seleccionado: LISTAR CLIENTES");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
