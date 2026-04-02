package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;

public class MenuConductores {

    static Scanner sc = new Scanner(System.in);
    static GestionConductoresManager conductorManager = new GestionConductoresManager();

    /**
     * Muestra el menú principal del módulo de conductores
     * y maneja las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE CONDUCTORES ===");
            System.out.println("1. Crear conductor");
            System.out.println("2. Listar conductores");
            System.out.println("3. Obtener conductor por identificación");
            System.out.println("4. Actualizar conductor");
            System.out.println("5. Eliminar conductor");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    crearConductor();
                    break;

                case 2:
                    listarConductores();
                    break;

                case 3:
                    obtenerConductor();
                    break;

                case 4:
                    actualizarConductor();
                    break;

                case 5:
                    eliminarConductor();
                    break;

                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    /**
     * Solicita los datos al usuario y crea un nuevo conductor.
     */
    private void crearConductor() {

        System.out.println("\n=== CREAR CONDUCTOR ===");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Identificacion: ");
        String id = sc.nextLine().trim();

        System.out.print("Licencia: ");
        String licencia = sc.nextLine().trim();

        ConductorVO c =
                new ConductorVO(nombre, email, "Conductor", id, licencia);

        conductorManager.agregarConductor(c);

        System.out.println("Conductor agregado correctamente.");
    }

    /**
     * Lista todos los conductores registrados.
     */
    private void listarConductores() {

        System.out.println("\n=== LISTADO DE CONDUCTORES ===");

        for (ConductorVO c : conductorManager.obtenerConductores()) {
            System.out.println(c);
        }
    }

    /**
     * Busca y muestra un conductor por su identificación.
     */
    private void obtenerConductor() {

        System.out.print("Ingrese la identificación del conductor: ");
        String id = sc.nextLine();

        ConductorVO conductor =
                conductorManager.buscarConductorPorId(id);

        if (conductor != null) {
            System.out.println("\n=== CONDUCTOR ENCONTRADO ===");
            System.out.println(conductor);
        } else {
            System.out.println("Conductor no encontrado.");
        }
    }

    /**
     * Actualiza los datos de un conductor existente.
     */
    private void actualizarConductor() {

        System.out.print("Ingrese la identificación del conductor a actualizar: ");
        String id = sc.nextLine();

        ConductorVO c =
                conductorManager.buscarConductorPorId(id);

        if (c != null) {

            System.out.print("Nuevo nombre (" + c.getNombre() + "): ");
            String nuevoNombre = sc.nextLine().trim();
            if (!nuevoNombre.isEmpty()) {
                c.setNombre(nuevoNombre);
            }

            System.out.print("Nuevo email (" + c.getEmail() + "): ");
            String nuevoEmail = sc.nextLine().trim();
            if (!nuevoEmail.isEmpty()) {
                c.setEmail(nuevoEmail);
            }

            conductorManager.actualizarConductor(id, c);

            System.out.println("Conductor actualizado correctamente.");
        } else {
            System.out.println("Conductor no encontrado.");
        }
    }

    /**
     * Elimina un conductor del sistema usando su identificación.
     */
    private void eliminarConductor() {

        System.out.print("Ingrese la identificación del conductor a eliminar: ");
        String id = sc.nextLine();

        boolean eliminado =
                conductorManager.eliminarConductor(id);

        if (eliminado) {
            System.out.println("Conductor eliminado correctamente.");
        } else {
            System.out.println("Conductor no encontrado.");
        }
    }
}