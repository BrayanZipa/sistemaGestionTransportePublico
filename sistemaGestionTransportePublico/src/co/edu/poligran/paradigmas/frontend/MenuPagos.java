package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
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
			System.out.println("3. Buscar pago por ID");
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
				buscarPago();
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

	private void crearPago() {

		System.out.println("\n=== CREAR PAGO ===");

		String idPago;
		do {
			System.out.print("ID del pago: ");
			idPago = sc.nextLine().trim();

			if (idPago.isEmpty()) {
				System.out.println("El ID no puede estar vacío.");
			}
		} while (idPago.isEmpty());

		System.out.print("Monto: ");
		double monto = sc.nextDouble();
		sc.nextLine();

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
	}

	private void listarPagos() {

		System.out.println("\n=== LISTADO DE PAGOS ===");

		for (PagoVO p : pagosManager.obtenerPagos()) {
			System.out.println(p);
		}
	}

	private void buscarPago() {

		System.out.print("Ingrese el ID del pago: ");
		String id = sc.nextLine();

		PagoVO pago = pagosManager.buscarPagoPorId(id);

		if (pago != null) {
			System.out.println("\n=== PAGO ENCONTRADO ===");
			System.out.println(pago);
		} else {
			System.out.println("Pago no encontrado.");
		}
	}

	private void actualizarPago() {

		System.out.print("Ingrese el ID del pago a actualizar: ");
		String id = sc.nextLine();

		PagoVO pago = pagosManager.buscarPagoPorId(id);

		if (pago != null) {

			System.out.print("Nuevo monto (" + pago.getMonto() + "): ");
			String nuevoMonto = sc.nextLine().trim();

			if (!nuevoMonto.isEmpty()) {
				pago.setMonto(Double.parseDouble(nuevoMonto));
			}

			System.out.print("Nuevo método (" + pago.getMetodoPago() + "): ");
			String nuevoMetodo = sc.nextLine().trim();

			if (!nuevoMetodo.isEmpty()) {
				pago.setMetodoPago(nuevoMetodo);
			}

			pagosManager.actualizarPagoPorId(id, pago);

			System.out.println("Pago actualizado correctamente.");

		} else {
			System.out.println("Pago no encontrado.");
		}
	}

	private void eliminarPago() {

		System.out.print("Ingrese el ID del pago a eliminar: ");
		String id = sc.nextLine();

		boolean eliminado = pagosManager.eliminarPagoPorId(id);

		if (eliminado) {
			System.out.println("Pago eliminado correctamente.");
		} else {
			System.out.println("Pago no encontrado.");
		}
	}
}
