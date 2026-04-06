package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.vo.BoletoVO;
import co.edu.poligran.paradigmas.backend.vo.PasajeroVO;

public class MenuBoletos {

    static Scanner sc = new Scanner(System.in);

    // Manager compartido del sistema para que pasajero aparezca en el menu boleto
    static GestionPasajerosManager pasajeroManager =
            Program.pasajeroManager;

    static GestionBoletosManager boletoManager =
            Program.boletoManager;

    /**
     * Muestra el menú principal del módulo de boletos
     * y gestiona las opciones seleccionadas por el usuario.
     */
    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE BOLETOS ===");
            System.out.println("1. Crear boleto");
            System.out.println("2. Listar boletos");
            System.out.println("3. Buscar boleto por código");
            System.out.println("4. Actualizar boleto");
            System.out.println("5. Eliminar boleto");
            System.out.println("6. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearBoleto();
                    break;
                case 2:
                    listarBoletos();
                    break;
                case 3:
                    buscarBoleto();
                    break;
                case 4:
                    actualizarBoleto();
                    break;
                case 5:
                    eliminarBoleto();
                    break;
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    private void crearBoleto() {

        System.out.println("\n=== CREAR BOLETO ===");

        System.out.print("Código del boleto: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        String asiento;
        do {
            System.out.print("Número de asiento: ");
            asiento = sc.nextLine().trim();

            if (asiento.isEmpty()) {
                System.out.println("El número de asiento no puede estar vacío.");
            }
        } while (asiento.isEmpty());

        System.out.print("Identificación del pasajero: ");
        String idPasajero = sc.nextLine();

        PasajeroVO pasajero =
                pasajeroManager.buscarPasajeroPorIdentificacion(idPasajero);

        if (pasajero == null) {
            System.out.println("El pasajero no existe. Debe crearlo primero.");
            return;
        }

        BoletoVO boleto = new BoletoVO(
                codigo,
                LocalDateTime.now(),
                asiento,
                pasajero
        );

        boletoManager.agregarBoleto(boleto);

        System.out.println("Boleto creado correctamente.");
    }

    private void listarBoletos() {

        System.out.println("\n=== LISTADO DE BOLETOS ===");

        for (BoletoVO b : boletoManager.obtenerBoletos()) {
            System.out.println(b);
        }
    }

    private void buscarBoleto() {

        System.out.print("Ingrese el código del boleto: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        BoletoVO boleto =
                boletoManager.buscarBoletoPorCodigo(codigo);

        if (boleto != null) {
            System.out.println("\n=== BOLETO ENCONTRADO ===");
            System.out.println(boleto);
        } else {
            System.out.println("Boleto no encontrado.");
        }
    }

    private void actualizarBoleto() {

        System.out.print("Ingrese el código del boleto a actualizar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        BoletoVO boleto =
                boletoManager.buscarBoletoPorCodigo(codigo);

        if (boleto != null) {

            System.out.print("Nuevo número de asiento (" 
                    + boleto.getNumeroAsiento() + "): ");

            String nuevoAsiento = sc.nextLine().trim();

            if (!nuevoAsiento.isEmpty()) {
                boleto.setNumeroAsiento(nuevoAsiento);
            }

            boletoManager.actualizarBoletoPorCodigo(codigo, boleto);

            System.out.println("Boleto actualizado correctamente.");

        } else {
            System.out.println("Boleto no encontrado.");
        }
    }

    private void eliminarBoleto() {

        System.out.print("Ingrese el código del boleto a eliminar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        boolean eliminado =
                boletoManager.eliminarBoletoPorCodigo(codigo);

        if (eliminado) {
            System.out.println("Boleto eliminado correctamente.");
        } else {
            System.out.println("Boleto no encontrado.");
        }
    }
}