package menu;

import java.util.Scanner;

public class menu {

    /***
     * Clase que crea un menu de 3 opciones.
     * @return int con la opcion seleccionada.
     */

    public static int MenuOptions() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        System.out.println("1. Listar Clientes");
        System.out.println("2. Consultar Saldo");
        System.out.println("3. Salir");

        System.out.println("Escribe una de las opciones");
        option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Procediendo al listado de clientes, espere por favor...");
                break;
            case 2:
                System.out.println("Se procedera a consultar el saldo, espere por favor...");
                break;
            case 3:
                System.out.println("Desconectado del server.");
                break;
            default:
                System.out.println("Opción erronea, solo valores entre 1 y 3");
        }
        return option;
    }
}
