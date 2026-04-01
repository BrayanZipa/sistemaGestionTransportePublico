package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;

import co.edu.poligran.paradigmas.backend.negocio.GestionConductorManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadoManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;
import co.edu.poligran.paradigmas.backend.vo.EmpleadoVO;
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
            System.out.println("3. Gestionar Trabajadores");
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
                    submenuTrabajadores();
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

    
    // CREAR VEHICULO
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
    // Menu de Trabajadores 
    public static void submenuTrabajadores() {

        int opcion;

        do {
            System.out.println("\n=== GESTION TRABAJADORES ===");
            System.out.println("1. Gestionar Empleados");
            System.out.println("2. Gestionar Conductores");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    submenuEmpleados();
                    break;

                case 2:
                    submenuConductores();
                    break;

            }

        } while (opcion != 3);
    }

    // Crear Empleados 
    public static void submenuEmpleados() {

        GestionEmpleadoManager empleadoManager = new GestionEmpleadoManager();

        int opcion;

        do {
            System.out.println("\n--- EMPLEADOS ---");
            System.out.println("1. Crear empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Identificacion: ");
                String id = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                EmpleadoVO e =
                        new EmpleadoVO(nombre, email, "Empleado", id, password);

                empleadoManager.agregarEmpleado(e);

                System.out.println("Empleado agregado correctamente");
            }

            else if (opcion == 2) {

                for (EmpleadoVO e : empleadoManager.obtenerEmpleados()) {
                    System.out.println(e);
                }
            }

        } while (opcion != 3);
    }

    // Crear Conductores 
    public static void submenuConductores() {

        GestionConductorManager conductorManager = new GestionConductorManager();

        int opcion;

        do {
            System.out.println("\n--- CONDUCTORES ---");
            System.out.println("1. Crear conductor");
            System.out.println("2. Listar conductores");
            System.out.println("3. Volver");
            System.out.print("Seleccione: ");

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