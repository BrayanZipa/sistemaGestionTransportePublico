package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionMantenimientoManager; 
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.MantenimientoVO;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;



public class MenuMantenimientos {

    static Scanner sc = new Scanner(System.in);
    private GestionMantenimientoManager mantenimientoManager;
    private GestionVehiculosManager vehiculoManager;

    /**
     * Constructor de la clase MenuMantenimientos.
     * @param mantenimientoManager gestor encargado de mantenimientos
     * @param vehiculoManager gestor encargado de vehículos
     */
    public MenuMantenimientos(GestionMantenimientoManager mantenimientoManager,
                              GestionVehiculosManager vehiculoManager) {
        this.mantenimientoManager = mantenimientoManager;
        this.vehiculoManager = vehiculoManager;
    }

    /**
     * Muestra el menú principal del módulo de mantenimientos
     * y gestiona las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE MANTENIMIENTOS ===");
            System.out.println("1. Crear mantenimiento");
            System.out.println("2. Listar mantenimientos");
            System.out.println("3. Buscar mantenimiento por ID");
            System.out.println("4. Actualizar mantenimiento");
            System.out.println("5. Eliminar mantenimiento");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearMantenimiento();
                    break;
                case 2:
                    listarMantenimientos();
                    break;
                case 3:
                    buscarMantenimiento();
                    break;
                case 4:
                    actualizarMantenimiento();
                    break;
                case 5:
                    eliminarMantenimiento();
                    break;
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    private void crearMantenimiento() {

        System.out.println("\n=== CREAR MANTENIMIENTO ===");

        String id;
        do {
            System.out.print("Id del mantenimiento: ");
            id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("El Id no puede estar vacío.");
            }
        } while (id.isEmpty());

        System.out.print("Fecha: ");
        String fecha = sc.nextLine();

        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        System.out.print("Costo: ");
        double costo = sc.nextDouble();
        sc.nextLine();

        VehiculoVO vehiculo;
        do {
            System.out.print("Placa del vehículo: ");
            String placa = sc.nextLine();

            vehiculo = vehiculoManager.buscarVehiculoPorPlaca(placa);

            if (vehiculo == null) {
                System.out.println("El vehículo no existe. Intente nuevamente.");
            }
        } while (vehiculo == null);

        MantenimientoVO m = new MantenimientoVO(id, fecha, descripcion, costo, vehiculo);
        vehiculo.agregarMantenimiento(m);
        mantenimientoManager.agregarMantenimiento(m);

        System.out.println("Mantenimiento creado correctamente.");
    }

    private void listarMantenimientos() {

        System.out.println("\n=== LISTADO DE MANTENIMIENTOS ===");

        for (MantenimientoVO m : mantenimientoManager.obtenerMantenimientos()) {
            System.out.println(m);
        }
    }

    private void buscarMantenimiento() {

        System.out.print("Ingrese el ID del mantenimiento: ");
        String id = sc.nextLine();

        MantenimientoVO m = mantenimientoManager.buscarMantenimientoPorId(id);

        if (m != null) {
            System.out.println("\n=== MANTENIMIENTO ENCONTRADO ===");
            System.out.println(m);
        } else {
            System.out.println("Mantenimiento no encontrado.");
        }
    }

    private void actualizarMantenimiento() {

        System.out.print("Ingrese el ID del mantenimiento a actualizar: ");
        String id = sc.nextLine();

        MantenimientoVO m = mantenimientoManager.buscarMantenimientoPorId(id);

        if (m != null) {

            System.out.print("Nueva descripción (" + m.getDescripcion() + "): ");
            String desc = sc.nextLine().trim();

            if (!desc.isEmpty()) {
                m.setDescripcion(desc);
            }

            System.out.print("Nuevo costo (" + m.getCosto() + "): ");
            String costo = sc.nextLine().trim();

            if (!costo.isEmpty()) {
                m.setCosto(Double.parseDouble(costo));
            }

            mantenimientoManager.actualizarMantenimientoPorId(id, m);

            System.out.println("Mantenimiento actualizado correctamente.");

        } else {
            System.out.println("Mantenimiento no encontrado.");
        }
    }

    private void eliminarMantenimiento() {

        System.out.print("Ingrese el ID del mantenimiento a eliminar: ");
        String id = sc.nextLine();

        boolean eliminado = mantenimientoManager.eliminarMantenimientoPorId(id);

        if (eliminado) {
            System.out.println("Mantenimiento eliminado correctamente.");
        } else {
            System.out.println("Mantenimiento no encontrado.");
        }
    }
}
