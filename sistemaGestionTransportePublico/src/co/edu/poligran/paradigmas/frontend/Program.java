package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;

public class Program {
	
	static Scanner sc = new Scanner(System.in);
	private static final GestionConductoresManager conductorManager = new GestionConductoresManager();
	private static final GestionEmpleadosManager empleadoManager = new GestionEmpleadosManager();
	private static final GestionPasajerosManager pasajeroManager = new GestionPasajerosManager();
	private static final GestionVehiculosManager vehiculoManager = new GestionVehiculosManager();
	private static final GestionParadasManager paradaManager = new GestionParadasManager();
	private static final GestionBoletosManager boletoManager = new GestionBoletosManager();
	private static final GestionRutasManager rutaManager = new GestionRutasManager();

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
                	new MenuEmpleados(empleadoManager).mostrarMenu();
                    break;

                case 2:
                	new MenuConductores(conductorManager).mostrarMenu();
                    break;

                case 3:
                	new MenuVehiculos(vehiculoManager, rutaManager).mostrarMenu();
                    break;

                case 4:
                	new MenuParadas(paradaManager, rutaManager).mostrarMenu();
                    break;
                    
                case 5:
                	new MenuRutas(rutaManager).mostrarMenu();
                    break;
                    
                case 6:
                	new MenuPasajeros(pasajeroManager).mostrarMenu();
                    break;
                    
                case 7:
                	new MenuBoletos(boletoManager, pasajeroManager, rutaManager).mostrarMenu();
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