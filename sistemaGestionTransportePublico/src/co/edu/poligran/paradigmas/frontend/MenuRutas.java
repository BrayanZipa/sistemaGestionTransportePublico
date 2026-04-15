package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;
import co.edu.poligran.paradigmas.backend.vo.ParadaVO;
import co.edu.poligran.paradigmas.backend.vo.RutaVO;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;

public class MenuRutas {

    static Scanner sc = new Scanner(System.in);
    private GestionRutasManager rutaManager;
    
    /**
     * Constructor de la clase MenuRutas.
     * 
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     */
    public MenuRutas(GestionRutasManager rutaManager) {
        this.rutaManager = rutaManager;
    }

    /**
     * Muestra el menú principal del módulo de rutas y maneja las opciones del usuario.
     */
    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE RUTAS ===");
            System.out.println("1. Crear ruta");
            System.out.println("2. Listar rutas");
            System.out.println("3. Obtener ruta por código");
            System.out.println("4. Eliminar ruta por código");
            System.out.println("5. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    crearRuta();
                    break;
                case 2:
                    listarRutas();
                    break;
                case 3:
                    obtenerRuta();
                    break;
                case 4:
                    eliminarRuta();
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    /**
     * Crea una nueva ruta solicitando los datos al usuario.
     */
    private void crearRuta() {

        System.out.println("\n=== CREAR RUTA ===");

        System.out.print("Ingrese el código de la ruta: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese código de parada origen: ");
        String codOrigen = sc.nextLine();

        ParadaVO origen = Program.paradaManager.buscarParadaPorCodigo(codOrigen);

        if (origen == null) {
            System.out.println("Parada origen no encontrada.");
            return;
        }

        System.out.print("Ingrese código de parada destino: ");
        String codDestino = sc.nextLine();

        ParadaVO destino = Program.paradaManager.buscarParadaPorCodigo(codDestino);

        if (destino == null) {
            System.out.println("Parada destino no encontrada.");
            return;
        }

        System.out.print("Ingrese la distancia en km: ");
        float distancia = sc.nextFloat();
        sc.nextLine();

        System.out.print("Ingrese la placa del vehículo: ");
        String placa = sc.nextLine();

        VehiculoVO vehiculo = Program.vehiculoManager.buscarVehiculoPorPlaca(placa);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return;
        }

        System.out.print("Ingrese identificación del conductor: ");
        String id = sc.nextLine();

        ConductorVO conductor = Program.conductorManager.buscarConductorPorId(id);

        if (conductor == null) {
            System.out.println("Conductor no encontrado.");
            return;
        }

        LocalDateTime fecha = LocalDateTime.now();

        RutaVO ruta = new RutaVO(
                codigo,
                origen,
                destino,
                distancia,
                fecha,
                vehiculo,
                conductor
        );

        rutaManager.agregarRuta(ruta);

        System.out.println("Ruta agregada correctamente.");
    }

    /**
     * Lista todas las rutas registradas en el sistema.
     */
    private void listarRutas() {

        System.out.println("\n=== LISTADO DE RUTAS ===");

        for (RutaVO r : rutaManager.obtenerRutas()) {
            System.out.println(r);
        }
    }

    /**
     * Busca y muestra una ruta específica por su código.
     */
    private void obtenerRuta() {

        System.out.print("Ingrese el código de la ruta a buscar: ");
        int codigo = sc.nextInt();

        RutaVO ruta = rutaManager.buscarRutaPorCodigo(codigo);

        if (ruta != null) {
            System.out.println("\n=== RUTA ENCONTRADA ===");
            System.out.println(ruta);
        } else {
            System.out.println("Ruta no encontrada.");
        }
    }

    /**
     * Elimina una ruta del sistema identificada por su código.
     */
    private void eliminarRuta() {

        System.out.print("Ingrese el código de la ruta a eliminar: ");
        int codigo = sc.nextInt();

        boolean eliminado = rutaManager.eliminarRutaPorCodigo(codigo);

        if (eliminado) {
            System.out.println("Ruta eliminada correctamente.");
        } else {
            System.out.println("Ruta no encontrada.");
        }
    }
}