package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.vo.PasajeroVO;

public class MenuPasajeros {

    static Scanner sc = new Scanner(System.in);
    private GestionPasajerosManager pasajeroManager;
    
    /**
     * Constructor de la clase MenuPasajeros.
     * 
     * @param pasajeroManager gestor encargado de las operaciones relacionadas con pasajeros
     */
    public MenuPasajeros(GestionPasajerosManager pasajeroManager) {
        this.pasajeroManager = pasajeroManager;
    }
    
    /**
     * Muestra el menú principal del módulo de pasajeros
     * y gestiona las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE PASAJEROS ===");
            System.out.println("1. Crear pasajero");
            System.out.println("2. Listar pasajeros");
            System.out.println("3. Buscar pasajero por identificación");
            System.out.println("4. Actualizar pasajero");
            System.out.println("5. Eliminar pasajero");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearPasajero();
                    break;
                case 2:
                    listarPasajeros();
                    break;
                case 3:
                    buscarPasajero();
                    break;
                case 4:
                    actualizarPasajero();
                    break;
                case 5:
                    eliminarPasajero();
                    break;
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    private void crearPasajero() {

        System.out.println("\n=== CREAR PASAJERO ===");

        String identificacion;
        do {
            System.out.print("Identificación: ");
            identificacion = sc.nextLine().trim();

            if (identificacion.isEmpty()) {
                System.out.println("La identificación no puede estar vacía.");
            }
        } while (identificacion.isEmpty());

        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = sc.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());

        PasajeroVO pasajero = new PasajeroVO(identificacion, nombre);
        pasajeroManager.agregarPasajero(pasajero);

        System.out.println("Pasajero creado correctamente.");
    }

    private void listarPasajeros() {

        System.out.println("\n=== LISTADO DE PASAJEROS ===");

        for (PasajeroVO p : pasajeroManager.obtenerPasajeros()) {
            System.out.println(p);
        }
    }

    private void buscarPasajero() {

        System.out.print("Ingrese la identificación del pasajero: ");
        String id = sc.nextLine();

        PasajeroVO pasajero = pasajeroManager.buscarPasajeroPorIdentificacion(id);

        if (pasajero != null) {
            System.out.println("\n=== PASAJERO ENCONTRADO ===");
            System.out.println(pasajero);
        } else {
            System.out.println("Pasajero no encontrado.");
        }
    }

    private void actualizarPasajero() {

        System.out.print("Ingrese la identificación del pasajero a actualizar: ");
        String id = sc.nextLine();

        PasajeroVO pasajero = pasajeroManager.buscarPasajeroPorIdentificacion(id);

        if (pasajero != null) {

            System.out.print("Nuevo nombre (" + pasajero.getNombre() + "): ");
            String nuevoNombre = sc.nextLine().trim();

            if (!nuevoNombre.isEmpty()) {
                pasajero.setNombre(nuevoNombre);
            }

            pasajeroManager.actualizarPasajeroPorIdentificacion(id, pasajero);

            System.out.println("Pasajero actualizado correctamente.");

        } else {
            System.out.println("Pasajero no encontrado.");
        }
    }

    private void eliminarPasajero() {

        System.out.print("Ingrese la identificación del pasajero a eliminar: ");
        String id = sc.nextLine();

        boolean eliminado = pasajeroManager.eliminarPasajeroPorIdentificacion(id);

        if (eliminado) {
            System.out.println("Pasajero eliminado correctamente.");
        } else {
            System.out.println("Pasajero no encontrado.");
        }
    }
}