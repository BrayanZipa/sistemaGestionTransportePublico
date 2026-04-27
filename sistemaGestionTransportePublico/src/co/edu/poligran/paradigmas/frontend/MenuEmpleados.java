package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import co.edu.poligran.paradigmas.backend.vo.EmpleadoVO;

public class MenuEmpleados {

	static Scanner sc = new Scanner(System.in);
	private GestionEmpleadosManager empleadoManager;

	/**
	 * Constructor de la clase MenuEmpleados.
	 * 
	 * @param empleadoManager gestor encargado de las operaciones relacionadas con
	 *                        empleados
	 */
	public MenuEmpleados(GestionEmpleadosManager empleadoManager) {
		this.empleadoManager = empleadoManager;
	}

	/**
	 * Muestra el menú principal del módulo de empleados y maneja las opciones del
	 * usuario.
	 */
	public void mostrarMenu() {

		int opcion = 0;

		do {
			System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
			System.out.println("1. Crear empleado");
			System.out.println("2. Listar empleados");
			System.out.println("3. Obtener empleado por identificación");
			System.out.println("4. Actualizar empleado por identificación");
			System.out.println("5. Eliminar empleado por identificación");
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
	 * Crea un nuevo empleado solicitando los datos al usuario.
	 */
	private void crearEmpleado() {

	    System.out.println("\n=== CREAR EMPLEADO ===");

	    try {

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

	        String identificacion;
	        do {
	            System.out.print("Ingrese la identificación: ");
	            identificacion = sc.nextLine().trim();

	            if (identificacion.isEmpty()) {
	                System.out.println("La identificación no puede estar vacía.");
	            } else if (!identificacion.matches("\\d+")) {
	                System.out.println("La identificación solo debe contener números.");
	                identificacion = "";
	            }
	        } while (identificacion.isEmpty());

	        String password;
	        do {
	            System.out.print("Ingrese la contraseña: ");
	            password = sc.nextLine().trim();

	            if (password.isEmpty()) {
	                System.out.println("La contraseña no puede estar vacía.");
	            } else if (password.length() < 4) {
	                System.out.println("La contraseña debe tener al menos 4 caracteres.");
	                password = "";
	            }
	        } while (password.isEmpty());

	        EmpleadoVO e = new EmpleadoVO(nombre, email, "Empleado", identificacion, password);

	        empleadoManager.agregarEmpleado(e);

	        System.out.println("Empleado agregado correctamente.");

	    } catch (IllegalArgumentException e) {
	        System.out.println("Error de validación: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Error inesperado: " + e.getMessage());
	    }
	}

	/**
	 * Lista de todos los empleados registrados en el sistema.
	 */
	private void listarEmpleados() {

		System.out.println("\n=== LISTADO DE EMPLEADOS ===");

		for (EmpleadoVO e : empleadoManager.obtenerEmpleados()) {
			System.out.println(e);
		}
	}

	/**
	 * Busca y muestra un empleado específico por su identificación.
	 */
	private void obtenerEmpleado() {
		try {
			System.out.print("Ingrese la identificación del empleado a buscar: ");
			String id = sc.nextLine();
			
			if (!id.matches("\\d+")) {
			    System.out.println("La identificación debe contener solo números.");
			    return;
			}
			EmpleadoVO empleado = empleadoManager.buscarEmpleadoPorId(id);

			if (empleado != null) {
				System.out.println("\n=== EMPLEADO ENCONTRADO ===");
				System.out.println(empleado);
			} else {
				System.out.println("Empleado no encontrado.");
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
	 * Actualiza los datos de un empleado existente identificado por su
	 * identificación.
	 */
	private void actualizarEmpleado() {
		try {
			System.out.print("Ingrese la identificación del empleado a actualizar: ");
			String id = sc.nextLine();

			EmpleadoVO e = empleadoManager.buscarEmpleadoPorId(id);

			if (e != null) {

				System.out.print("Nuevo nombre (" + e.getNombre() + "): ");
				String nuevoNombre = sc.nextLine().trim();
				if (!nuevoNombre.isEmpty()) {
				    if (nuevoNombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
				        e.setNombre(nuevoNombre);
				    } else {
				        System.out.println("Nombre inválido. Solo letras. Se conserva el anterior.");
				    }
				}

				System.out.print("Nuevo email (" + e.getEmail() + "): ");
				String nuevoEmail = sc.nextLine().trim();
				if (!nuevoEmail.isEmpty()) {
				    if (nuevoEmail.contains("@")) {
				        e.setEmail(nuevoEmail);
				    } else {
				        System.out.println("Email inválido. Se conserva el anterior.");
				    }
				}
				System.out.print("Nueva contraseña: ");
				String nuevaPassword = sc.nextLine().trim();
				if (!nuevaPassword.isEmpty()) {
				    if (nuevaPassword.length() >= 4) {
				        e.setPassword(nuevaPassword);
				    } else {
				        System.out.println("La contraseña debe tener al menos 4 caracteres. Se conserva la anterior.");
				    }
				}

				empleadoManager.actualizarEmpleado(id, e);

				System.out.println("Empleado actualizado correctamente.");
			} else {
				System.out.println("Empleado no encontrado.");
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
	 * Elimina un empleado del sistema identificado por su identificación.
	 */
	private void eliminarEmpleado() {
		try {
			System.out.print("Ingrese la identificación del empleado a eliminar: ");
			String id = sc.nextLine();
			
			if (!id.matches("\\d+")) {
			    System.out.println("La identificación debe contener solo números.");
			    return;
			}

			boolean eliminado = empleadoManager.eliminarEmpleado(id);

			if (eliminado) {
				System.out.println("Empleado eliminado correctamente.");
			} else {
				System.out.println("Empleado no encontrado.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Error de validación: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}
	}
}
