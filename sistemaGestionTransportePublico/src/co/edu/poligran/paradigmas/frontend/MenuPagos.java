package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import co.edu.poligran.paradigmas.backend.vo.PagoVO;

public class MenuPagos {

	static Scanner sc = new Scanner(System.in);
	private GestionPagosManager pagosManager;

	/**
	 * Constructor de la clase MenuPagos.
	 * 
	 * @param pagosManager gestor encargado de las operaciones relacionadas con
	 *                     pagos
	 */
	public MenuPagos(GestionPagosManager pagosManager) {
		this.pagosManager = pagosManager;
	}

	/**
	 * Muestra el menú principal del módulo de pagos y gestiona las opciones
	 * seleccionadas por el usuario.
	 */
	public void mostrarMenu() {

		int opcion = 0;

		do {
			System.out.println("\n=== GESTIÓN DE PAGOS ===");
			System.out.println("1. Crear pago");
			System.out.println("2. Listar pagos");
			System.out.println("3. Obtener pago por código");
			System.out.println("4. Actualizar pago");
			System.out.println("5. Eliminar pago");
			System.out.println("6. Volver al menú principal");
			System.out.print("\nSeleccione una opción: ");

			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				crearPago();
				break;
			case 2:
				listarPagos();
				break;
			case 3:
				obtenerPago();
				break;
			case 4:
				actualizarPago();
				break;
			case 5:
				eliminarPago();
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
     * Crea un nuevo pago solicitando los datos al usuario.
     */

	private void crearPago() {

		System.out.println("\n=== CREAR PAGO ===");
		try {
			String idPago;
			do {
				System.out.print("ID del pago: ");
				idPago = sc.nextLine().trim();

				if (idPago.isEmpty()) {
					System.out.println("El Id no puede estar vacío.");
				}
			} while (idPago.isEmpty());

			double monto = 0;

			do {
			    System.out.print("Monto (mínimo 1.000): ");
			    String input = sc.nextLine().trim();

			    if (input.isEmpty()) {
			        System.out.println("El monto no puede estar vacío.");
			        continue;
			    }

			    try {
			        String limpio = input.replace(".", "");
			        monto = Double.parseDouble(limpio);

			        if (monto < 1000) {
			            System.out.println("El monto debe ser mínimo 1.000.");
			            monto = 0;
			        }

			    } catch (NumberFormatException e) {
			        System.out.println("Debe ingresar un número válido (ej: 3000 o 3.000).");
			        monto = 0;
			    }

			} while (monto < 1000);

			String metodo;
			do {
				System.out.print("Método de pago: ");
				metodo = sc.nextLine().trim();

				if (metodo.isEmpty()) {
					System.out.println("El método no puede estar vacío.");
				}
			} while (metodo.isEmpty());

			PagoVO pago = new PagoVO(idPago, LocalDateTime.now(), monto, metodo);

			pagosManager.agregarPago(pago);

			System.out.println("Pago creado correctamente.");

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

	private void listarPagos() {

		System.out.println("\n=== LISTADO DE PAGOS ===");

		for (PagoVO p : pagosManager.obtenerPagos()) {
			System.out.println(p);
		}
	}
	
	private void obtenerPago() {

	    String id;

	    do {
	        System.out.print("Ingrese el ID del pago: ");
	        id = sc.nextLine().trim();

	        if (id.isEmpty()) {
	            System.out.println("El ID no puede estar vacío.");
	        }

	    } while (id.isEmpty());

	    PagoVO pago = pagosManager.buscarPagoPorId(id);

	    if (pago != null) {
	        System.out.println("\n=== PAGO ENCONTRADO ===");
	        System.out.println(pago);
	    } else {
	        System.out.println("Pago no encontrado.");
	    }
	}
	

	private void actualizarPago() {

		try {
			System.out.print("Ingrese el ID del pago a actualizar: ");
			String id = sc.nextLine();

			PagoVO pago = pagosManager.buscarPagoPorId(id);

			if (pago == null) {
				System.out.println("Pago no encontrado.");
				return;
			}

			System.out.print("Nuevo monto (" + pago.getMonto() + "): ");
			String nuevoMonto = sc.nextLine().trim();

			if (!nuevoMonto.isEmpty()) {
			    try {
			        String limpio = nuevoMonto.replace(".", "");
			        double monto = Double.parseDouble(limpio);

			        if (monto < 1000) {
			            System.out.println("El monto debe ser mínimo 1.000. Se conserva el valor anterior.");
			        } else {
			            pago.setMonto(monto);
			        }

			    } catch (NumberFormatException e) {
			        System.out.println("Monto inválido. Se conserva el valor anterior.");
			    }
			}

			System.out.print("Nuevo método (" + pago.getMetodoPago() + "): ");
			String nuevoMetodo = sc.nextLine().trim();

			if (!nuevoMetodo.isEmpty()) {
				pago.setMetodoPago(nuevoMetodo);
			}

			pagosManager.actualizarPagoPorId(id, pago);

			System.out.println("Pago actualizado correctamente.");

		} catch (NumberFormatException e) {
			System.out.println("El monto debe ser un número válido.");
		} catch (IllegalArgumentException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (IllegalStateException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}

	private void eliminarPago() {
		try {
			System.out.print("Ingrese el ID del pago a eliminar: ");
			String id = sc.nextLine();

			boolean eliminado = pagosManager.eliminarPagoPorId(id);

			if (eliminado) {
				System.out.println("Pago eliminado correctamente.");
			} else {
				System.out.println("Pago no encontrado.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}
}
