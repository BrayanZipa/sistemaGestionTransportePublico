package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import co.edu.poligran.paradigmas.backend.vo.EmpleadoVO;

public class MenuEmpleados {

    static Scanner sc = new Scanner(System.in);
    static GestionEmpleadosManager empleadoManager = new GestionEmpleadosManager();

    /**
     * Muestra el menú principal del módulo de empleados
     * y gestiona las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
            System.out.println("1. Crear empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Obtener empleado por identificación");
            System.out.println("4. Actualizar empleado");
            System.out.println("5. Eliminar empleado");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    crearEmpleado();
                    break;

                case 2:
                    listarEmpleados();
                    break;

                case 3:
                    obtenerEmpleado();
                    break;

                case 4:
                    actualizarEmpleado();
                    break;

                case 5:
                    eliminarEmpleado();
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
     * Solicita los datos al usuario y crea un nuevo empleado.
     */
    private void crearEmpleado() {

        System.out.println("\n=== CREAR EMPLEADO ===");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Identificacion: ");
        String id = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        EmpleadoVO e =
                new EmpleadoVO(nombre, email, "Empleado", id, password);

        empleadoManager.agregarEmpleado(e);

        System.out.println("Empleado agregado correctamente.");
    }

    /**
     * Lista todos los empleados registrados.
     */
    private void listarEmpleados() {

        System.out.println("\n=== LISTADO DE EMPLEADOS ===");

        for (EmpleadoVO e : empleadoManager.obtenerEmpleados()) {
            System.out.println(e);
        }
    }

    /**
     * Busca y muestra un empleado por su identificación.
     */
    private void obtenerEmpleado() {

        System.out.print("Ingrese la identificación del empleado: ");
        String id = sc.nextLine();

        EmpleadoVO empleado =
                empleadoManager.buscarEmpleadoPorId(id);

        if (empleado != null) {
            System.out.println("\n=== EMPLEADO ENCONTRADO ===");
            System.out.println(empleado);
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    /**
     * Actualiza los datos de un empleado existente.
     */
    private void actualizarEmpleado() {

        System.out.print("Ingrese la identificación del empleado a actualizar: ");
        String id = sc.nextLine();

        EmpleadoVO e =
                empleadoManager.buscarEmpleadoPorId(id);

        if (e != null) {

            System.out.print("Nuevo nombre (" + e.getNombre() + "): ");
            String nuevoNombre = sc.nextLine().trim();
            if (!nuevoNombre.isEmpty()) {
                e.setNombre(nuevoNombre);
            }

            System.out.print("Nuevo email (" + e.getEmail() + "): ");
            String nuevoEmail = sc.nextLine().trim();
            if (!nuevoEmail.isEmpty()) {
                e.setEmail(nuevoEmail);
            }

            System.out.print("Nuevo password: ");
            String nuevoPassword = sc.nextLine().trim();
            if (!nuevoPassword.isEmpty()) {
                e.setPassword(nuevoPassword);
            }

            empleadoManager.actualizarEmpleado(id, e);

            System.out.println("Empleado actualizado correctamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    /**
     * Elimina un empleado del sistema usando su identificación.
     */
    private void eliminarEmpleado() {

        System.out.print("Ingrese la identificación del empleado a eliminar: ");
        String id = sc.nextLine();

        boolean eliminado =
                empleadoManager.eliminarEmpleado(id);

        if (eliminado) {
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }
}