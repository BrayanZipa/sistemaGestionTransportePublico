package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDateTime;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionTarifasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionMantenimientoManager;
import co.edu.poligran.paradigmas.backend.vo.*;

public class DataLoader {
	
	/**
	 * Precarga todos los datos de prueba necesarios en memoria para el correcto funcionamiento del sistema durante su ejecución
	 * @param empleadoManager gestor encargado de las operaciones de empleados
	 * @param conductorManager gestor encargado de las operaciones de conductores
	 * @param pasajeroManager gestor encargado de las operaciones de pasajeros
	 * @param vehiculoManager gestor encargado de las operaciones de vehículos
	 * @param paradaManager gestor encargado de las operaciones de paradas
	 * @param rutaManager gestor encargado de las operaciones de rutas
	 * @param boletoManager gestor encargado de las operaciones de boletos
	 * @param pagosManager gestor encargado de las operaciones de pagos
	 * @param tarifasManager gestor encargado de las operaciones de tarifas
	 * @param mantenimientoManager gestor encargado de las operaciones de mantenimientos
	 */
    public static void cargarTodo(
    		GestionEmpleadosManager empleadoManager,
        	GestionConductoresManager conductorManager,
        	GestionPasajerosManager pasajeroManager,
            GestionVehiculosManager vehiculoManager,
            GestionParadasManager paradaManager,
            GestionRutasManager rutaManager,
            GestionBoletosManager boletoManager,
            GestionPagosManager pagosManager,
            GestionTarifasManager tarifasManager,
            GestionMantenimientoManager mantenimientoManager
    ) {
    	precargarEmpleados(empleadoManager);
    	precargarConductores(conductorManager);
    	precargarPasajeros(pasajeroManager);
        precargarVehiculos(vehiculoManager);
        precargarParadas(paradaManager);
        precargarRutas(rutaManager, paradaManager, vehiculoManager, conductorManager);
        precargarPagos(pagosManager);
        precargarTarifas(tarifasManager);
        precargarMantenimientos(mantenimientoManager, vehiculoManager);
        precargarBoletos(boletoManager, pasajeroManager, rutaManager, pagosManager);
    }
  
    /**
     * Precarga un conjunto de empleados de prueba en memoria
     */
    private static void precargarEmpleados(GestionEmpleadosManager empleadoManager) {
        empleadoManager.agregarEmpleado(new EmpleadoVO("Carlos Pérez", "carlos@empresa.com", "ADMIN", "2001", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("Laura Gómez", "laura@empresa.com", "OPERADOR", "2002", "1234"));
    }
    
    /**
     * Precarga un conjunto de conductores de prueba en memoria
     */
    private static void precargarConductores(GestionConductoresManager conductorManager) {
        conductorManager.agregarConductor(new ConductorVO("Luis García", "luis@empresa.com", "CONDUCTOR", "3001", "C1"));
        conductorManager.agregarConductor(new ConductorVO("Pedro López", "pedro@empresa.com", "CONDUCTOR", "3002", "C2"));
    }
    
    /**
     * Precarga un conjunto de pasajeros de prueba en memoria
     */
    private static void precargarPasajeros(GestionPasajerosManager pasajeroManager) {
        pasajeroManager.agregarPasajero(new PasajeroVO("1001", "Carlos Pérez"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1002", "Laura Gómez"));
    }

    /**
     * Precarga un conjunto de vehículos de prueba en memoria
     */
    private static void precargarVehiculos(GestionVehiculosManager vehiculoManager) {
        vehiculoManager.agregarVehiculo(new VehiculoVO("ABC123", "Volvo 2020", 40, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("DEF456", "Mercedes 2019", 35, true));
    }
    
    /**
     * Precarga un conjunto de paradas de prueba en memoria
     */
    private static void precargarParadas(GestionParadasManager paradaManager) {
        paradaManager.agregarParada(new ParadaVO("P1", "Portal Norte", "Autopista Norte"));
        paradaManager.agregarParada(new ParadaVO("P2", "Calle 100", "Av Suba"));
    }
  
    /**
     * Precarga un conjunto de rutas de prueba en memoria
     */
    private static void precargarRutas(
    	GestionRutasManager rutaManager,
	    GestionParadasManager paradaManager,
	    GestionVehiculosManager vehiculoManager,
	    GestionConductoresManager conductorManager
    ) {
    	
	    RutaVO r1 = new RutaVO(
	        1,
	        paradaManager.buscarParadaPorCodigo("P1"),
	        paradaManager.buscarParadaPorCodigo("P2"),
	        12.5f,
	        LocalDateTime.now(),
	        vehiculoManager.buscarVehiculoPorPlaca("ABC123"),
	        conductorManager.buscarConductorPorId("3001")
	    );

    	rutaManager.agregarRuta(r1);
    }

    /**
     * Precarga un conjunto de pagos de prueba en memoria
     */
    private static void precargarPagos(GestionPagosManager pagosManager) {

        pagosManager.agregarPago(new PagoVO("P001", LocalDateTime.now(), 2500, "Efectivo"));
        pagosManager.agregarPago(new PagoVO("P002", LocalDateTime.now(), 3000, "Tarjeta"));
        pagosManager.agregarPago(new PagoVO("P003", LocalDateTime.now(), 2800, "Nequi"));
    }

    /**
     * Precarga un conjunto de tarifas de prueba en memoria
     */
    private static void precargarTarifas(GestionTarifasManager tarifasManager) {

        tarifasManager.agregarTarifa(new TarifasVO(1, LocalDateTime.now(), 2500, "Urbano"));
        tarifasManager.agregarTarifa(new TarifasVO(2, LocalDateTime.now(), 3500, "Intermunicipal"));
        tarifasManager.agregarTarifa(new TarifasVO(3, LocalDateTime.now(), 2000, "Especial"));
    }

    /**
     * Precarga un conjunto de mantenimientos de prueba en memoria
     */
    private static void precargarMantenimientos(
        GestionMantenimientoManager mantenimientoManager,
        GestionVehiculosManager vehiculoManager
    ) {

        MantenimientoVO m1 = new MantenimientoVO(
            "M001",
            "2026-01-10",
            "Cambio de aceite",
            150000,
            vehiculoManager.buscarVehiculoPorPlaca("ABC123")
        );

        MantenimientoVO m2 = new MantenimientoVO(
            "M002",
            "2026-02-15",
            "Revisión de frenos",
            200000,
            vehiculoManager.buscarVehiculoPorPlaca("DEF456")
        );

        mantenimientoManager.agregarMantenimiento(m1);
        mantenimientoManager.agregarMantenimiento(m2);

        m1.getVehiculo().agregarMantenimiento(m1);
        m2.getVehiculo().agregarMantenimiento(m2);
    }
  
    /**
     * Precarga un conjunto de boletos de prueba en memoria
     */
    private static void precargarBoletos(
    	GestionBoletosManager boletoManager,
	    GestionPasajerosManager pasajeroManager,
	    GestionRutasManager rutaManager,
	    GestionPagosManager pagosManager
    ) {
    	
    	BoletoVO b1 = new BoletoVO(
    		    1,
    		    LocalDateTime.now(),
    		    "A1",
    		    pasajeroManager.buscarPasajeroPorIdentificacion("1001"),
    		    rutaManager.buscarRutaPorCodigo(1),
    		    pagosManager.buscarPagoPorId("P001"), null
    		);

	    boletoManager.agregarBoleto(b1);
	    b1.getRuta().agregarBoleto(b1);
    }
}