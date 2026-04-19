package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.BoletoVO;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;
import co.edu.poligran.paradigmas.backend.vo.ParadaVO;
import co.edu.poligran.paradigmas.backend.vo.RutaVO;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;

public class MenuRutas {

    static Scanner sc = new Scanner(System.in);
    private GestionRutasManager rutaManager;
    private GestionParadasManager paradaManager;
    private GestionBoletosManager boletoManager;
    private GestionVehiculosManager vehiculoManager;
    private GestionConductoresManager conductorManager;
    
    /**
     * Constructor de la clase MenuRutas.
     * 
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     * @param paradaManager gestor encargado de las operaciones relacionadas con paradas
     * @param boletoManager gestor encargado de las operaciones relacionadas con boletos
     * @param vehiculoManager gestor encargado de las operaciones relacionadas con vehiculos
     * @param conductorManager gestor encargado de las operaciones relacionadas con conductores
     */
    public MenuRutas(GestionRutasManager rutaManager, GestionParadasManager paradaManager, GestionBoletosManager boletoManager, GestionVehiculosManager vehiculoManager, GestionConductoresManager conductorManager) {
        this.rutaManager = rutaManager;
        this.paradaManager = paradaManager;
        this.boletoManager = boletoManager;
        this.vehiculoManager = vehiculoManager;
        this.conductorManager = conductorManager;
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
            System.out.println("4. Actualizar ruta");
            System.out.println("5. Eliminar ruta por código");
            System.out.println("6. Agregar parada a una ruta");
            System.out.println("7. Agregar boleto a una ruta");
            System.out.println("8. Volver al menú principal");
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
                	actualizarRuta();
                    break;
                case 5:
                    eliminarRuta();
                    break;
                case 6:
                	asignarParadaRuta();
                    break;
                case 7:
                	asignarBoletoRuta();
                    break;
                case 8:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 8);
    }

    /**
     * Crea una nueva ruta solicitando los datos al usuario.
     */
    private void crearRuta() {
        System.out.println("\n=== CREAR RUTA ===");

        System.out.print("Ingrese el código de la ruta: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        ParadaVO origen;
        do {
            System.out.print("Código parada origen: ");
            String codOrigen = sc.nextLine();
            origen = paradaManager.buscarParadaPorCodigo(codOrigen);

            if (origen == null) {
                System.out.println("Parada origen no encontrada.");
            }
        } while (origen == null);

        ParadaVO destino;
        do {
            System.out.print("Código parada destino: ");
            String codDestino = sc.nextLine();
            destino = paradaManager.buscarParadaPorCodigo(codDestino);

            if (destino == null) {
                System.out.println("Parada destino no encontrada.");
            }
        } while (destino == null);

        System.out.print("Distancia en km: ");
        float distancia = sc.nextFloat();
        sc.nextLine();

        VehiculoVO vehiculo;
        do {
            System.out.print("Placa del vehículo: ");
            String placa = sc.nextLine();
            vehiculo = vehiculoManager.buscarVehiculoPorPlaca(placa);

            if (vehiculo == null) {
                System.out.println("Vehículo no encontrado.");
            }
        } while (vehiculo == null);

        ConductorVO conductor;
        do {
            System.out.print("ID del conductor: ");
            String id = sc.nextLine();
            conductor = conductorManager.buscarConductorPorId(id);

            if (conductor == null) {
                System.out.println("Conductor no encontrado.");
            }
        } while (conductor == null);

        RutaVO ruta = new RutaVO(
                codigo,
                origen,
                destino,
                distancia,
                LocalDateTime.now(),
                vehiculo,
                conductor
        );

        rutaManager.agregarRuta(ruta);
        System.out.println("Ruta creada correctamente.");
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
     * Actualiza los datos de una ruta existente identificada por su código.
     */
    private void actualizarRuta() {
        System.out.print("Ingrese el código de la ruta a actualizar: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        RutaVO ruta = rutaManager.buscarRutaPorCodigo(codigo);

        if (ruta == null) {
            System.out.println("Ruta no encontrada.");
            return;
        }

        System.out.print("Nueva distancia (" + ruta.getDistancia() + "): ");
        String distanciaStr = sc.nextLine();

        if (!distanciaStr.isEmpty()) {
            ruta.setDistancia(Float.parseFloat(distanciaStr));
        }

        System.out.print("Placa del vehículo: ");
        String placa = sc.nextLine();

        if (!placa.isEmpty()) {
            VehiculoVO vehiculo = vehiculoManager.buscarVehiculoPorPlaca(placa);
            if (vehiculo != null) {
                ruta.setVehiculo(vehiculo);
            } else {
                System.out.println("Vehículo no encontrado. Se conserva el actual.");
            }
        }

        System.out.print("Identificación del conductor: ");
        String id = sc.nextLine();

        if (!id.isEmpty()) {
            ConductorVO conductor = conductorManager.buscarConductorPorId(id);
            if (conductor != null) {
                ruta.setConductor(conductor);
            } else {
                System.out.println("Conductor no encontrado. Se conserva el actual.");
            }
        }

        rutaManager.actualizarRuta(codigo, ruta);

        System.out.println("Ruta actualizada correctamente.");
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
    
    /**
     * Asigna una ruta a una parada existente.
     */
    private void asignarParadaRuta() {
    	System.out.println("\n=== ASIGNAR PARADA A RUTA ===");
    	
        System.out.print("Código de la ruta: ");
        int codigoRuta = sc.nextInt();
        sc.nextLine();

        RutaVO ruta = rutaManager.buscarRutaPorCodigo(codigoRuta);

        if (ruta == null) {
            System.out.println("Ruta no encontrada.");
            return;
        }

        System.out.print("Código de la parada: ");
        String codParada = sc.nextLine();

        ParadaVO parada = paradaManager.buscarParadaPorCodigo(codParada);

        if (parada == null) {
            System.out.println("Parada no encontrada.");
            return;
        }

        ruta.agregarParada(parada);
        parada.agregarRuta(ruta);
        
        System.out.println("Parada agregada correctamente.");
    }
    
    /**
     * Asigna una ruta a un boleto existente.
     */
    private void asignarBoletoRuta() {
        System.out.println("\n=== ASIGNAR BOLETO A RUTA ===");
        
        System.out.print("Ingrese el código de la ruta: ");
        int codigoRuta = sc.nextInt();
        sc.nextLine();

        RutaVO ruta = rutaManager.buscarRutaPorCodigo(codigoRuta);

        if (ruta == null) {
            System.out.println("Ruta no encontrada.");
            return;
        }

        System.out.print("Ingrese el código del boleto: ");
        int codigoBoleto = sc.nextInt();
        sc.nextLine();

        BoletoVO boleto = boletoManager.buscarBoletoPorCodigo(codigoBoleto);

        if (boleto == null) {
            System.out.println("Boleto no encontrado.");
            return;
        }

        boleto.setRuta(ruta);
        ruta.agregarBoleto(boleto);
        
        System.out.println("Boleto asignado correctamente.");
    }
}