package utilities;

import java.io.*;
import java.net.Socket;

public class ListClients {


    public static void listClients(Socket client) {

        try {

            Usuario[] arrayUsr = GenerateUsers.Generate();
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(arrayUsr);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
