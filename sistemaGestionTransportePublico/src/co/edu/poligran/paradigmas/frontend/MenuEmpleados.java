package co.edu.poligran.paradigmas.frontend;

import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import co.edu.poligran.paradigmas.backend.vo.EmpleadoVO;

public class MenuEmpleados {
	
	static Scanner sc = new Scanner(System.in);
    static GestionEmpleadosManager empleadoManager = new GestionEmpleadosManager();
	
	/**
     * Muestra el menú principal del módulo de empleados y maneja las opciones del usuario.
     */
	public void mostrarMenu() {
		
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE EMPLEADOS ===");
            System.out.println("1. Crear empleado");
            System.out.println("2. Listar empleados");
            System.out.println("3. Volver");
            System.out.print("\nSeleccione: ");

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Identificacion: ");
                String id = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                EmpleadoVO e = new EmpleadoVO(nombre, email, "Empleado", id, password);

                empleadoManager.agregarEmpleado(e);

                System.out.println("Empleado agregado correctamente");
            }

            else if (opcion == 2) {

                for (EmpleadoVO e : empleadoManager.obtenerEmpleados()) {
                    System.out.println(e);
                }
            }

        } while (opcion != 3);
    }
}
