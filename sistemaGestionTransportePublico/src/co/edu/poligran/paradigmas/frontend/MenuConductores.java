package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;

public class MenuConductores {

	static Scanner sc = new Scanner(System.in);
	private GestionConductoresManager conductorManager;

	/**
	 * Constructor de la clase MenuConductores.
	 * 
	 * @param conductorManager gestor encargado de las operaciones relacionadas con
	 *                         conductores
	 */
	public MenuConductores(GestionConductoresManager conductorManager) {
		this.conductorManager = conductorManager;
	}

	/**
	 * Muestra el menú principal del módulo de conductores y maneja las opciones
	 * seleccionadas por el usuario.
	 */
	public void mostrarMenu() {

		int opcion = 0;

		do {
			System.out.println("\n=== GESTIÓN DE CONDUCTORES ===");
			System.out.println("1. Crear conductor");
			System.out.println("2. Listar conductores");
			System.out.println("3. Obtener conductor por identificación");
			System.out.println("4. Actualizar conductor por identificación");
			System.out.println("5. Eliminar conductor por identificación");
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
	 * Crea un nuevo conductor solicitando los datos al usuario.
	 */
	private void crearConductor() {
	    try {
	        System.out.println("\n=== CREAR CONDUCTOR ===");

	        String nombre;
	        do {
	            System.out.print("Ingrese el nombre: ");
	            nombre = sc.nextLine().trim();

	            if (nombre.isEmpty()) {
	                System.out.println("El nombre no puede estar vacío.");
	            } else if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
	                System.out.println("El nombre solo debe contener letras.");
	                nombre = "";
	            }
	        } while (nombre.isEmpty());

	        String email;
	        do {
	            System.out.print("Ingrese el email: ");
	            email = sc.nextLine().trim();

	            if (email.isEmpty()) {
	                System.out.println("El email no puede estar vacío.");
	            } else if (!email.contains("@")) {
	                System.out.println("Ingrese un email válido.");
	                email = "";
	            }
	        } while (email.isEmpty());

	        String id;
	        do {
	            System.out.print("Ingrese la identificación: ");
	            id = sc.nextLine().trim();

	            if (id.isEmpty()) {
	                System.out.println("La identificación no puede estar vacía.");
	            } else if (!id.matches("\\d+")) {
	                System.out.println("La identificación solo debe contener números.");
	                id = "";
	            }
	        } while (id.isEmpty());

	        String licencia;
	        do {
	            System.out.print("Ingrese la licencia: ");
	            licencia = sc.nextLine().trim();

	            if (licencia.isEmpty()) {
	                System.out.println("La licencia no puede estar vacía.");
	            } else if (!licencia.matches("\\d+")) {
	                System.out.println("La licencia solo debe contener números.");
	                licencia = "";
	            }
	        } while (licencia.isEmpty());

	        ConductorVO c = new ConductorVO(nombre, email, "Conductor", id, licencia);
	        conductorManager.agregarConductor(c);

	        System.out.println("Conductor agregado correctamente.");

	    } catch (IllegalArgumentException e) {
	        System.out.println("Error de validación: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	    }
	}

	/**
	 * Lista todos los conductores registrados en el sistema.
	 */
	private void listarConductores() {

		System.out.println("\n=== LISTADO DE CONDUCTORES ===");

		for (ConductorVO c : conductorManager.obtenerConductores()) {
			System.out.println(c);
		}
	}

	/**
	 * Busca y muestra un conductor específico por su identificación.
	 */
	private void obtenerConductor() {
		try {
			System.out.print("Ingrese la identificación del conductor a buscar: ");
			String id = sc.nextLine();
			
			if (!id.matches("\\d+")) {
			    System.out.println("La identificación debe contener solo números.");
			    return;
			}
			ConductorVO conductor = conductorManager.buscarConductorPorId(id);

			if (conductor != null) {
				System.out.println("\n=== CONDUCTOR ENCONTRADO ===");
				System.out.println(conductor);
			} else {
				System.out.println("Conductor no encontrado.");
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
	 * Actualiza los datos de un conductor existente identificado por su
	 * identificación.
	 */
	private void actualizarConductor() {
		try {
			System.out.print("Ingrese la identificación del conductor a actualizar: ");
			String id = sc.nextLine();

			ConductorVO c = conductorManager.buscarConductorPorId(id);

			if (c != null) {

				System.out.print("Nuevo nombre (" + c.getNombre() + "): ");
				String nuevoNombre = sc.nextLine().trim();
				if (!nuevoNombre.isEmpty()) {
				    if (nuevoNombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
				        c.setNombre(nuevoNombre);
				    } else {
				        System.out.println("Nombre inválido. Solo letras. Se conserva el anterior.");
				    }
				}

				System.out.print("Nuevo email (" + c.getEmail() + "): ");
				String nuevoEmail = sc.nextLine().trim();
				if (!nuevoEmail.isEmpty()) {
				    if (nuevoEmail.contains("@")) {
				        c.setEmail(nuevoEmail);
				    } else {
				        System.out.println("Email inválido. Se conserva el anterior.");
				    }
				}

				System.out.print("Nueva licencia (" + c.getLicencia() + "): ");
				String nuevaLicencia = sc.nextLine().trim();
				if (!nuevaLicencia.isEmpty()) {
				    if (nuevaLicencia.matches("\\d+")) {
				        c.setLicencia(nuevaLicencia);
				    } else {
				        System.out.println("Licencia inválida. Solo números. Se conserva la anterior.");
				    }
				}

				conductorManager.actualizarConductor(id, c);
				System.out.println("Conductor actualizado correctamente.");

			} else {
				System.out.println("Conductor no encontrado.");
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
	 * Elimina un conductor del sistema identificado por su identificación.
	 */
	private void eliminarConductor() {
		try {
			System.out.print("Ingrese la identificación del conductor a eliminar: ");
			String id = sc.nextLine();
			
			if (!id.matches("\\d+")) {
			    System.out.println("La identificación debe contener solo números.");
			    return;
			}

			boolean eliminado = conductorManager.eliminarConductor(id);

			if (eliminado) {
				System.out.println("Conductor eliminado correctamente.");
			} else {
				System.out.println("Conductor no encontrado.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}
}