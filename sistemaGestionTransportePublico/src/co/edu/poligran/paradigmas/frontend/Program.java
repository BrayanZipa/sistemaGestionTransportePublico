package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;

public class Program {
	
	static Scanner sc = new Scanner(System.in);
    static GestionVehiculosManager vehiculoManager = new GestionVehiculosManager();

	public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA DE GESTION ===");
            System.out.println("1. Gestionar Vehiculos");
            System.out.println("2. Gestionar Rutas");
            System.out.println("3. Gestionar Conductores");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearVehiculo();
                    break;

                case 2:
                    System.out.println("Modulo de rutas");
                    break;

                case 3:
                    System.out.println("Modulo de conductores");
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 4);

        sc.close();
	}
	
    public static void crearVehiculo() {

        VehiculoVO v = new VehiculoVO();

        System.out.println("\n=== CREAR VEHICULO ===");

        System.out.print("Ingrese la placa: ");
        v.setPlaca(sc.nextLine());

        System.out.print("Ingrese el modelo: ");
        v.setModelo(sc.nextLine());

        System.out.print("Ingrese la capacidad de pasajeros: ");
        v.setCapacidadPasajeros(sc.nextInt());
        sc.nextLine();

        System.out.print("¿Está disponible? (true/false): ");
        v.setEstadoDisponibilidad(sc.nextBoolean());
        sc.nextLine();

        vehiculoManager.agregarVehiculo(v);

        System.out.println("Vehiculo agregado correctamente");
        
        for (VehiculoVO ve : vehiculoManager.obtenerVehiculos()) {
            System.out.println(ve);
        }
    }
}
