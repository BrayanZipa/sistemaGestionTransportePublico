package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.vo.ParadaVO;

public class MenuParadas {

    static Scanner sc = new Scanner(System.in);
    static GestionParadasManager paradaManager = new GestionParadasManager();

    /**
     * Muestra el menú principal del módulo de paradas y maneja las opciones del usuario.
     */
    public void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE PARADAS ===");
            System.out.println("1. Crear parada");
            System.out.println("2. Listar paradas");
            System.out.println("3. Obtener parada por código");
            System.out.println("4. Actualizar parada por código");
            System.out.println("5. Eliminar parada por código");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearParada();
                    break;
                case 2:
                    listarParadas();
                    break;
                case 3:
                    obtenerParada();
                    break;
                case 4:
                    actualizarParada();
                    break;
                case 5:
                    eliminarParada();
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
     * Crea una nueva parada solicitando los datos al usuario.
     */
    private void crearParada() {
        System.out.println("\n=== CREAR PARADA ===");

        String codigo;
        
        do {
            System.out.print("Ingrese el código: ");
            codigo = sc.nextLine().trim();

            if (codigo.isEmpty()) {
                System.out.println("El código no puede estar vacío.");
            }
        } while (codigo.isEmpty());

        String nombre;
        
        do {
            System.out.print("Ingrese el nombre: ");
            nombre = sc.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (nombre.isEmpty());

        String ubicacion;
        
        do {
            System.out.print("Ingrese la ubicación: ");
            ubicacion = sc.nextLine().trim();

            if (ubicacion.isEmpty()) {
                System.out.println("La ubicación no puede estar vacía.");
            }
        } while (ubicacion.isEmpty());

        ParadaVO p = new ParadaVO(codigo, nombre, ubicacion);
        paradaManager.agregarParada(p);

        System.out.println("Parada agregada correctamente.");
    }

    /**
     * Lista todas las paradas registradas en el sistema.
     */
    private void listarParadas() {
        System.out.println("\n=== LISTADO DE PARADAS ===");

        for (ParadaVO p : paradaManager.obtenerParadas()) {
            System.out.println(p);
        }
    }

    /**
     * Busca y muestra una parada específica por su código.
     */
    private void obtenerParada() {
        System.out.print("Ingrese el código de la parada a buscar: ");
        String codigo = sc.nextLine();

        ParadaVO parada = paradaManager.buscarParadaPorCodigo(codigo);

        if (parada != null) {
            System.out.println("\n=== PARADA ENCONTRADA ===");
            System.out.println(parada);
        } else {
            System.out.println("Parada no encontrada.");
        }
    }

    /**
     * Actualiza los datos de una parada existente identificada por su código.
     */
    private void actualizarParada() {
        System.out.print("Ingrese el código de la parada a actualizar: ");
        String codigo = sc.nextLine();
        ParadaVO p = paradaManager.buscarParadaPorCodigo(codigo);

        if (p != null) {
            System.out.print("Nuevo nombre (" + p.getNombre() + "): ");
            String nuevoNombre = sc.nextLine().trim();

            if (!nuevoNombre.isEmpty()) {
                p.setNombre(nuevoNombre);
            }

            System.out.print("Nueva ubicación (" + p.getUbicacion() + "): ");
            String nuevaUbicacion = sc.nextLine().trim();

            if (!nuevaUbicacion.isEmpty()) {
                p.setUbicacion(nuevaUbicacion);
            }

            paradaManager.actualizarParadaPorCodigo(codigo, p);
            System.out.println("Parada actualizada correctamente.");
        } else {
            System.out.println("Parada no encontrada.");
        }
    }

    /**
     * Elimina una parada del sistema identificada por su código.
     */
    private void eliminarParada() {
        System.out.print("Ingrese el código de la parada a eliminar: ");
        String codigo = sc.nextLine();

        boolean eliminada = paradaManager.eliminarParadaPorCodigo(codigo);

        if (eliminada) {
            System.out.println("Parada eliminada correctamente.");
        } else {
            System.out.println("Parada no encontrada.");
        }
    }
}