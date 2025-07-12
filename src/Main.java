import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // === MENÚ CRUD DE RESERVAS ===
        System.out.println("\n¿Desea abrir el CRUD del hotel? (s/n): ");
        String opcionCrud = sc.nextLine();
        if (opcionCrud.equalsIgnoreCase("s")) {
            CrudHotel crud = new CrudHotel();
            crud.menuCrud(); // Abre el menú CRUD del hotel
        }

        sc.close();
    }
}
