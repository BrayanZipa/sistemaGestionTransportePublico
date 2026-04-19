package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionTarifasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionMantenimientoManager;

public class Program {
	
	static Scanner sc = new Scanner(System.in);
	private static final GestionConductoresManager conductorManager = new GestionConductoresManager();
	private static final GestionEmpleadosManager empleadoManager = new GestionEmpleadosManager();
	private static final GestionPasajerosManager pasajeroManager = new GestionPasajerosManager();
	private static final GestionVehiculosManager vehiculoManager = new GestionVehiculosManager();
	private static final GestionParadasManager paradaManager = new GestionParadasManager();
	private static final GestionBoletosManager boletoManager = new GestionBoletosManager();
	private static final GestionRutasManager rutaManager = new GestionRutasManager();
	private static final GestionPagosManager pagosManager = new GestionPagosManager();
	private static final GestionTarifasManager tarifasManager = new GestionTarifasManager();
	private static final GestionMantenimientoManager mantenimientoManager = new GestionMantenimientoManager();

	public static void main(String[] args) {
		
		DataLoader.cargarTodo(empleadoManager, conductorManager, pasajeroManager, vehiculoManager, paradaManager, rutaManager, boletoManager, pagosManager, tarifasManager, mantenimientoManager );
		
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA DE GESTION DE TRANSPORTE ===");
            System.out.println("1. Gestionar Empleados");
            System.out.println("2. Gestionar Conductores");
            System.out.println("3. Gestionar Vehículos");
            System.out.println("4. Gestionar Paradas");
            System.out.println("5. Gestionar Rutas");
            System.out.println("6. Gestionar Pasajeros");
            System.out.println("7. Gestionar Boletos");
            System.out.println("8. Gestionar Pagos");
            System.out.println("9. Gestionar Tarifa");
            System.out.println("10. Gestionar Mantenimiento");
            System.out.println("11. Salir");
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
                	new MenuRutas(rutaManager, paradaManager, boletoManager, vehiculoManager, conductorManager).mostrarMenu();
                    break;
                    
                case 6:
                	new MenuPasajeros(pasajeroManager).mostrarMenu();
                    break;
                    
                case 7:
                	new MenuBoletos(boletoManager, pasajeroManager, rutaManager, pagosManager,tarifasManager).mostrarMenu();
                    break;
                    
               case 8:
            	    new MenuPagos(pagosManager).mostrarMenu();
                    break;
                    
                case 9:
                	new MenuTarifas(tarifasManager).mostrarMenu(); 
                    break;
                    
                case 10:
                	new MenuMantenimientos(mantenimientoManager, vehiculoManager).mostrarMenu();
                    break;
                    
                case 11:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 11); 

        sc.close();
	}
}