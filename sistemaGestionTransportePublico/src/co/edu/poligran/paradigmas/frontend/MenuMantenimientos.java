package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
	 * 
	 * @param mantenimientoManager gestor encargado de mantenimientos
	 * @param vehiculoManager      gestor encargado de vehículos
	 */
	public MenuMantenimientos(GestionMantenimientoManager mantenimientoManager,
			GestionVehiculosManager vehiculoManager) {
		this.mantenimientoManager = mantenimientoManager;
		this.vehiculoManager = vehiculoManager;
	}

	/**
	 * Muestra el menú principal del módulo de mantenimientos y gestiona las
	 * opciones seleccionadas por el usuario.
	 */
	public void mostrarMenu() {

		int opcion = 0;

		do {
			System.out.println("\n=== GESTIÓN DE MANTENIMIENTOS ===");
			System.out.println("1. Crear mantenimiento");
			System.out.println("2. Listar mantenimientos");
			System.out.println("3. Obtener mantenimiento por ID");
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
			    obtenerMantenimiento();
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
	
	/**
     * Crea un nuevo mantenimiento solicitando los datos al usuario.
     */
	private void crearMantenimiento() {

		System.out.println("\n=== CREAR MANTENIMIENTO ===");

		try {

			String id;
			do {
				System.out.print("Id del mantenimiento: ");
				id = sc.nextLine().trim();

				if (id.isEmpty()) {
				    System.out.println("El Id no puede estar vacío.");
				} else if (id.length() < 3) {
				    System.out.println("El Id debe tener al menos 3 caracteres.");
				    id = "";
				}
			} while (id.isEmpty());

			LocalDate fecha = null;

			do {
			    try {
			        System.out.print("Fecha (YYYY-MM-DD): ");
			        String fechaStr = sc.nextLine();

			        fecha = LocalDate.parse(fechaStr);

			    } catch (DateTimeParseException e) {
			        System.out.println("Formato de fecha inválido. Use YYYY-MM-DD.");
			    }
			} while (fecha == null);
			
			String descripcion;
			do {
			    System.out.print("Descripción: ");
			    descripcion = sc.nextLine().trim();

			    if (descripcion.isEmpty()) {
			        System.out.println("La descripción no puede estar vacía.");
			    }
			} while (descripcion.isEmpty());
			
			Double costo = null;

			do {
			    System.out.print("Costo (mínimo 1.000): ");
			    String input = sc.nextLine().trim();

			    if (input.isEmpty()) {
			        System.out.println("El costo no puede estar vacío.");
			        continue;
			    }

			    try {
			        String limpio = input.replace(".", "");
			        costo = Double.parseDouble(limpio);

			        if (costo < 1000) {
			            System.out.println("El costo debe ser mínimo 1.000.");
			            costo = null;
			        }

			    } catch (NumberFormatException e) {
			        System.out.println("Debe ingresar un número válido.");
			    }

			} while (costo == null);

			VehiculoVO vehiculo = null;
			String placa;

			do {
			    System.out.print("Placa del vehículo: ");
			    placa = sc.nextLine().trim();

			    if (placa.isEmpty()) {
			        System.out.println("La placa no puede estar vacía.");
			        continue;
			    }

			    vehiculo = vehiculoManager.buscarVehiculoPorPlaca(placa);

			    if (vehiculo == null) {
			        System.out.println("El vehículo no existe. Intente nuevamente.");
			    }

			} while (vehiculo == null);

			MantenimientoVO m = new MantenimientoVO(id, fecha, descripcion, costo, vehiculo);
			vehiculo.agregarMantenimiento(m);
			mantenimientoManager.agregarMantenimiento(m);

			System.out.println("Mantenimiento creado correctamente.");

		} catch (java.util.InputMismatchException e) {
			System.out.println("Debe ingresar un valor numérico válido para el costo.");
			sc.nextLine();
		} catch (IllegalArgumentException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (IllegalStateException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}

	private void listarMantenimientos() {

		System.out.println("\n=== LISTADO DE MANTENIMIENTOS ===");

		for (MantenimientoVO m : mantenimientoManager.obtenerMantenimientos()) {
			System.out.println(m);
		}
	}
	
	private void obtenerMantenimiento() {

	    String id;

	    do {
	        System.out.print("Ingrese el ID del mantenimiento: ");
	        id = sc.nextLine().trim();

	        if (id.isEmpty()) {
	            System.out.println("El ID no puede estar vacío.");
	        }

	    } while (id.isEmpty());

	    MantenimientoVO m = mantenimientoManager.buscarMantenimientoPorId(id);

	    if (m != null) {
	        System.out.println("\n=== MANTENIMIENTO ENCONTRADO ===");
	        System.out.println(m);
	    } else {
	        System.out.println("Mantenimiento no encontrado.");
	    }
	}
	

	private void actualizarMantenimiento() {
		try {
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
				    try {
				        String limpio = costo.replace(".", "");
				        double nuevoCosto = Double.parseDouble(limpio);

				        if (nuevoCosto < 1000) {
				            System.out.println("El costo debe ser mínimo 1.000. Se conserva el anterior.");
				        } else {
				            m.setCosto(nuevoCosto);
				        }

				    } catch (NumberFormatException e) {
				        System.out.println("Costo inválido. Se conserva el anterior.");
				    }
				}

				if (!costo.isEmpty()) {
				    try {
				        String limpio = costo.replace(".", "");
				        double nuevoCosto = Double.parseDouble(limpio);

				        if (nuevoCosto < 1000) {
				            System.out.println("El costo debe ser mínimo 1.000. Se conserva el anterior.");
				        } else {
				            m.setCosto(nuevoCosto);
				        }

				    } catch (NumberFormatException e) {
				        System.out.println("Costo inválido. Se conserva el anterior.");
				    }
				}
				mantenimientoManager.actualizarMantenimientoPorId(id, m);

				System.out.println("Mantenimiento actualizado correctamente.");

			} else {
				System.out.println("Mantenimiento no encontrado.");
			}

		} catch (NumberFormatException e) {
			System.out.println("El costo debe ser un número válido.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (IllegalStateException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}

	private void eliminarMantenimiento() {
		try {
			System.out.print("Ingrese el ID del mantenimiento a eliminar: ");
			String id = sc.nextLine();

			boolean eliminado = mantenimientoManager.eliminarMantenimientoPorId(id);

			if (eliminado) {
				System.out.println("Mantenimiento eliminado correctamente.");
			} else {
				System.out.println("Mantenimiento no encontrado.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}
}
