package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;

public class Program {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA DE GESTION ===");
            System.out.println("1. Gestionar Empleados");
            System.out.println("2. Gestionar Conductores");
            System.out.println("3. Gestionar Vehículos");
            System.out.println("4. Gestionar Paradas");
            System.out.println("5. Gestionar Rutas");
            System.out.println("6. Gestionar Pasajeros");
            System.out.println("7. Gestionar Boletos");
            System.out.println("8. Salir");
            System.out.print("\nSeleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                	new MenuEmpleados().mostrarMenu();
                    break;

                case 2:
                	new MenuConductores().mostrarMenu();
                    break;

                case 3:
                	new MenuVehiculos().mostrarMenu();
                    break;

                case 4:
                	new MenuParadas().mostrarMenu();
                    break;
                    
                case 5:
                	System.out.println("Modulo de rutas");
                    break;
                    
                case 6:
                	System.out.println("Modulo de pasajeros");
                    break;
                    
                case 7:
                	System.out.println("Modulo de boletos");
                    break;
                    
                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 8);

        sc.close();
	}
}
