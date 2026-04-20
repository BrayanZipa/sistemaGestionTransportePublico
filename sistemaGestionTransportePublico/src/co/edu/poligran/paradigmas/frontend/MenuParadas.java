package co.edu.poligran.paradigmas.frontend;

import java.util.InputMismatchException;
import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.vo.ParadaVO;
import co.edu.poligran.paradigmas.backend.vo.RutaVO;

public class MenuParadas {

    static Scanner sc = new Scanner(System.in);
    private GestionParadasManager paradaManager;
    private GestionRutasManager rutaManager;

    /**
     * Constructor de la clase MenuParadas.
     * 
     * @param paradaManager gestor encargado de las operaciones relacionadas con paradas
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     */
    public MenuParadas(GestionParadasManager paradaManager, GestionRutasManager rutaManager) {
		this.paradaManager = paradaManager;
		this.rutaManager = rutaManager;
	}

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
            System.out.println("6. Agregar parada a una ruta");
            System.out.println("7. Volver al menú principal");
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
                	asignarRutaParada();
                    break;
                case 7:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 7);
    }

    /**
     * Crea una nueva parada solicitando los datos al usuario.
     */
    private void crearParada() {
        System.out.println("\n=== CREAR PARADA ===");

        try {
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
        
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
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
        try {
            System.out.print("Ingrese el código de la parada a buscar: ");
            String codigo = sc.nextLine().trim();
            
        	ParadaVO parada = paradaManager.buscarParadaPorCodigo(codigo);
        	
            if (parada != null) {
                System.out.println("\n=== PARADA ENCONTRADA ===");
                System.out.println(parada);
            } else {
                System.out.println("Parada no encontrada.");
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (IllegalStateException e) {
	        System.out.println("Error: " + e.getMessage());
	    } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Actualiza los datos de una parada existente identificada por su código.
     */
    private void actualizarParada() {
        try {
            System.out.print("Ingrese el código de la parada a actualizar: ");
            String codigo = sc.nextLine().trim();
            
            ParadaVO p = paradaManager.buscarParadaPorCodigo(codigo);
            if (p == null) {
                System.out.println("Parada no encontrada.");
                return;
            }

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

        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    /**
     * Elimina una parada del sistema identificada por su código.
     */
    private void eliminarParada() {
        try {
            System.out.print("Ingrese el código de la parada a eliminar: ");
            String codigo = sc.nextLine().trim();
            
            boolean eliminada = paradaManager.eliminarParadaPorCodigo(codigo);

            if (eliminada) {
                System.out.println("Parada eliminada correctamente.");
            } else {
                System.out.println("Parada no encontrada.");
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Asigna una parada a una ruta existente.
     */
    private void asignarRutaParada() {
        System.out.println("\n=== ASIGNAR PARADA A RUTA ===");
        
        try {
	        System.out.print("Ingrese el código de la ruta: ");
	        int codigoRuta = sc.nextInt();
	        sc.nextLine();
	
	        RutaVO ruta = rutaManager.buscarRutaPorCodigo(codigoRuta);
	        if (ruta == null) {
	            System.out.println("Ruta no encontrada.");
	            return;
	        }
	
	        System.out.print("Ingrese el código de la parada: ");
	        String codigoParada = sc.nextLine();
	
	        ParadaVO parada = paradaManager.buscarParadaPorCodigo(codigoParada);
	        if (parada == null) {
	            System.out.println("Parada no encontrada.");
	            return;
	        }
	        
	        if (ruta.getParadas().contains(parada)) {
	            System.out.println("La parada ya está asignada a esta ruta.");
	            return;
	        }
	
	        parada.agregarRuta(ruta);
	        ruta.agregarParada(parada);
	        System.out.println("Parada asignada correctamente.");
        
	    } catch (InputMismatchException e) {
	        System.out.println("Debe ingresar un número válido.");
	        sc.nextLine();
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error de validación: " + e.getMessage());
	    } catch (IllegalStateException e) {
	        System.out.println("Error: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	    }
    }
}