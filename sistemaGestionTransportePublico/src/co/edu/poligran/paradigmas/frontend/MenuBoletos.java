package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
import java.util.Scanner;
import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.vo.BoletoVO;
import co.edu.poligran.paradigmas.backend.vo.PasajeroVO;
import co.edu.poligran.paradigmas.backend.vo.RutaVO;

public class MenuBoletos {

    static Scanner sc = new Scanner(System.in);
    private GestionBoletosManager boletoManager;
    private GestionPasajerosManager pasajeroManager;
    private GestionRutasManager rutaManager;
    
    /**
     * Constructor de la clase MenuBoletos.
     * 
     * @param boletoManager gestor encargado de las operaciones relacionadas con boletos
     * @param pasajeroManager gestor encargado de las operaciones relacionadas con pasajeros
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     */
    public MenuBoletos(GestionBoletosManager boletoManager, GestionPasajerosManager pasajeroManager, GestionRutasManager rutaManager) {
		this.boletoManager = boletoManager;
		this.pasajeroManager = pasajeroManager;
		this.rutaManager = rutaManager;
	}

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
            System.out.println("3. Obtener boleto por código");
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

        PasajeroVO pasajero;
        do {
            System.out.print("Identificación del pasajero: ");
            String idPasajero = sc.nextLine();

            pasajero = pasajeroManager.buscarPasajeroPorIdentificacion(idPasajero);

            if (pasajero == null) {
                System.out.println("El pasajero no existe. Intente nuevamente.");
            }
        } while (pasajero == null);

        RutaVO ruta;
        do {
            System.out.print("Código de la ruta: ");
            int codigoRuta = sc.nextInt();
            sc.nextLine();

            ruta = rutaManager.buscarRutaPorCodigo(codigoRuta);

            if (ruta == null) {
                System.out.println("La ruta no existe. Intente nuevamente.");
            }
        } while (ruta == null);

        BoletoVO boleto = new BoletoVO(
                codigo,
                LocalDateTime.now(),
                asiento,
                pasajero,
                ruta
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
        
        BoletoVO boleto = boletoManager.buscarBoletoPorCodigo(codigo);

        if (boleto == null) {
            System.out.println("Boleto no encontrado.");
            return;
        }

        System.out.print("Nuevo número de asiento (" + boleto.getNumeroAsiento() + "): ");
        String nuevoAsiento = sc.nextLine().trim();

        if (!nuevoAsiento.isEmpty()) {
        	boleto.setNumeroAsiento(nuevoAsiento);
        }
        
        System.out.print("Identificación del pasajero: ");
        String idPasajero = sc.nextLine().trim();

        if (!idPasajero.isEmpty()) {
            PasajeroVO nuevoPasajero = pasajeroManager.buscarPasajeroPorIdentificacion(idPasajero);

            if (nuevoPasajero != null) {
                boleto.setPasajero(nuevoPasajero);
            } else {
                System.out.println("Pasajero no encontrado. Se conserva el actual.");
            }
        }
        
        System.out.print("Código de la ruta: ");
        String codigoRuta = sc.nextLine().trim();

        if (!codigoRuta.isEmpty()) {
            int nuevoCodigoRuta = Integer.parseInt(codigoRuta);

            RutaVO nuevaRuta = rutaManager.buscarRutaPorCodigo(nuevoCodigoRuta);

            if (nuevaRuta != null) {
                boleto.setRuta(nuevaRuta);
            } else {
                System.out.println("Ruta no encontrada. Se conserva la actual.");
            }
        }
        
        boletoManager.actualizarBoletoPorCodigo(codigo, boleto);

        System.out.println("Boleto actualizado correctamente.");
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
