package app;

import menu.menu;

public class Cliente {

    private static final int PROGRAMPORT = 9500;

    public static void main(String[] args) {
        String ipConection = args[0];
        String conectionPort = args[1];

        String menuResult  = String.valueOf(menu.MenuOptions());

        if (menuResult.equals("1") || menuResult.equals("2")) {

        }



    }
}
