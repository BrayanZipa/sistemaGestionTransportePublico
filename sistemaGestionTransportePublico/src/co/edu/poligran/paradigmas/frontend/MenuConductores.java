package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;

public class MenuConductores {
	
	static Scanner sc = new Scanner(System.in);
    static GestionConductoresManager conductorManager = new GestionConductoresManager();
	
	/**
     * Muestra el menú principal del módulo de conductores y maneja las opciones del usuario.
     */
	public void mostrarMenu() {
		
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE CONDUCTORES ===");
            System.out.println("1. Crear conductor");
            System.out.println("2. Listar conductores");
            System.out.println("3. Volver");
            System.out.print("\nSeleccione: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Identificacion: ");
                    String id = sc.nextLine();

                    System.out.print("Licencia: ");
                    String licencia = sc.nextLine();

                    ConductorVO c =
                            new ConductorVO(nombre, email, "Conductor", id, licencia);

                    conductorManager.agregarConductor(c);

                    System.out.println("Conductor agregado correctamente");
                    break;

                case 2:

                    for (ConductorVO c1 : conductorManager.obtenerConductores()) {
                        System.out.println(c1);
                    }
                    break;
            }

        } while (opcion != 3);
    }
}
