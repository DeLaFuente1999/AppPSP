package utilities;

import java.io.*;
import java.net.Socket;

public class ListBalance {


    public static boolean CheckUserPwd(String dni, String pwd, Socket client) {

        Boolean result = false;

        try {
            Usuario[] arrayUsr = GenerateUsers.Generate();


            for (int i = 0; i < arrayUsr.length; i++) {
                if (arrayUsr[i].getDni().equals(dni) && arrayUsr[i].getPassword().equals(pwd)) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Balance ReadUserBalance(String dni, Socket client) throws IOException {

        String fileUsers = "ficheros/" + dni + ".txt";

        BufferedReader br;
        File usersFile = new File(fileUsers);

        String[] account = new String[3];

        int i = 0;


        br = new BufferedReader(new FileReader(usersFile));

        String line = "";


        while (line != null) {
            line = br.readLine();
            account[i] = line;
            i++;
        }

        Balance balance = new Balance(account[0], account[1]);

        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject(balance);


        return balance;


    }
}
