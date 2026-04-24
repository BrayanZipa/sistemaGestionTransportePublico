package co.edu.poligran.paradigmas.frontend;

import java.time.LocalDate;
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
        empleadoManager.agregarEmpleado(new EmpleadoVO("Andrés Rodríguez", "andres@empresa.com", "OPERADOR", "2003", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("María Fernández", "maria@empresa.com", "ADMIN", "2004", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("Juan Martínez", "juan@empresa.com", "OPERADOR", "2005", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("Sofía Ramírez", "sofia@empresa.com", "OPERADOR", "2006", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("Daniel Torres", "daniel@empresa.com", "ADMIN", "2007", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("Valentina Castro", "valentina@empresa.com", "OPERADOR", "2008", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("Felipe Herrera", "felipe@empresa.com", "OPERADOR", "2009", "1234"));
        empleadoManager.agregarEmpleado(new EmpleadoVO("Camila Vargas", "camila@empresa.com", "ADMIN", "2010", "1234"));
    }

    
    /**
     * Precarga un conjunto de conductores de prueba en memoria
     */
    private static void precargarConductores(GestionConductoresManager conductorManager) {
        conductorManager.agregarConductor(new ConductorVO("Luis García", "luis@empresa.com", "CONDUCTOR", "3001", "C1"));
        conductorManager.agregarConductor(new ConductorVO("Pedro López", "pedro@empresa.com", "CONDUCTOR", "3002", "C2"));
        conductorManager.agregarConductor(new ConductorVO("Jorge Díaz", "jorge@empresa.com", "CONDUCTOR", "3003", "C1"));
        conductorManager.agregarConductor(new ConductorVO("Miguel Torres", "miguel@empresa.com", "CONDUCTOR", "3004", "C2"));
        conductorManager.agregarConductor(new ConductorVO("Ricardo Sánchez", "ricardo@empresa.com", "CONDUCTOR", "3005", "C1"));
        conductorManager.agregarConductor(new ConductorVO("Fernando Rojas", "fernando@empresa.com", "CONDUCTOR", "3006", "C2"));
        conductorManager.agregarConductor(new ConductorVO("Diego Herrera", "diego@empresa.com", "CONDUCTOR", "3007", "C1"));
        conductorManager.agregarConductor(new ConductorVO("Álvaro Castro", "alvaro@empresa.com", "CONDUCTOR", "3008", "C2"));
        conductorManager.agregarConductor(new ConductorVO("Óscar Vargas", "oscar@empresa.com", "CONDUCTOR", "3009", "C1"));
        conductorManager.agregarConductor(new ConductorVO("Hugo Morales", "hugo@empresa.com", "CONDUCTOR", "3010", "C2"));
    }

    
    /**
     * Precarga un conjunto de pasajeros de prueba en memoria
     */
    private static void precargarPasajeros(GestionPasajerosManager pasajeroManager) {
        pasajeroManager.agregarPasajero(new PasajeroVO("1001", "Carlos Pérez"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1002", "Laura Gómez"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1003", "Andrés Rodríguez"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1004", "María Fernández"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1005", "Juan Martínez"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1006", "Sofía Ramírez"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1007", "Daniel Torres"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1008", "Valentina Castro"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1009", "Felipe Herrera"));
        pasajeroManager.agregarPasajero(new PasajeroVO("1010", "Camila Vargas"));
    }

    /**
     * Precarga un conjunto de vehículos de prueba en memoria
     */
    private static void precargarVehiculos(GestionVehiculosManager vehiculoManager) {
        vehiculoManager.agregarVehiculo(new VehiculoVO("ABC123", "Volvo 2020", 40, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("DEF456", "Mercedes 2019", 35, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("GHI789", "Scania 2021", 45, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("JKL012", "Volvo 2018", 35, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("MNO345", "Mercedes 2022", 40, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("PQR678", "Scania 2017", 35, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("STU901", "BYD 2023", 50, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("VWX234", "Volvo 2019", 40, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("YZA567", "Mercedes 2020", 40, true));
        vehiculoManager.agregarVehiculo(new VehiculoVO("BCD890", "Scania 2021", 40, true));
    }
    
    /**
     * Precarga un conjunto de paradas de prueba en memoria
     */
    private static void precargarParadas(GestionParadasManager paradaManager) {
        paradaManager.agregarParada(new ParadaVO("P1", "Portal Norte", "Autopista Norte #232-35"));
        paradaManager.agregarParada(new ParadaVO("P2", "Calle 100", "Av. Suba #100-15"));
        paradaManager.agregarParada(new ParadaVO("P3", "Calle 72", "Carrera 7 #72-10"));
        paradaManager.agregarParada(new ParadaVO("P4", "Aeropuerto El Dorado", "Av. El Dorado #103-09"));
        paradaManager.agregarParada(new ParadaVO("P5", "Portal El Dorado", "Av. Calle 26 #86-05"));
        paradaManager.agregarParada(new ParadaVO("P6", "Banderas", "Av. Américas #78-90"));
        paradaManager.agregarParada(new ParadaVO("P7", "Portal Sur", "Autopista Sur #65-20"));
        paradaManager.agregarParada(new ParadaVO("P8", "Restrepo", "Av. Caracas #19-50 Sur"));
        paradaManager.agregarParada(new ParadaVO("P9", "San Victorino", "Carrera 10 #12-45"));
        paradaManager.agregarParada(new ParadaVO("P10", "Museo Nacional", "Carrera 7 #28-66"));
        paradaManager.agregarParada(new ParadaVO("P11", "Portal Suba", "Av. Suba #145-60"));
        paradaManager.agregarParada(new ParadaVO("P12", "Granja - Carrera 77", "Av. Calle 80 #77-05"));
        paradaManager.agregarParada(new ParadaVO("P13", "Minuto de Dios", "Av. Calle 80 #73A-40"));
        paradaManager.agregarParada(new ParadaVO("P14", "Calle 45", "Carrera 13 #45-20"));
        paradaManager.agregarParada(new ParadaVO("P15", "Universidades", "Carrera 3 #22-45"));
        paradaManager.agregarParada(new ParadaVO("P16", "Av. Jiménez", "Av. Jiménez #8-15"));
        paradaManager.agregarParada(new ParadaVO("P17", "Héroes", "Autopista Norte #80-50"));
        paradaManager.agregarParada(new ParadaVO("P18", "Virrey", "Autopista Norte #88-10"));
        paradaManager.agregarParada(new ParadaVO("P19", "Pepe Sierra", "Autopista Norte #116-25"));
        paradaManager.agregarParada(new ParadaVO("P20", "Toberín", "Autopista Norte #163-50"));
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
                paradaManager.buscarParadaPorCodigo("P3"),
                12.5f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("ABC123"),
                conductorManager.buscarConductorPorId("3001")
        );
        r1.agregarParada(paradaManager.buscarParadaPorCodigo("P2"));

        RutaVO r2 = new RutaVO(
                2,
                paradaManager.buscarParadaPorCodigo("P7"),
                paradaManager.buscarParadaPorCodigo("P9"),
                15.0f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("DEF456"),
                conductorManager.buscarConductorPorId("3002")
        );
        r2.agregarParada(paradaManager.buscarParadaPorCodigo("P8"));

        RutaVO r3 = new RutaVO(
                3,
                paradaManager.buscarParadaPorCodigo("P5"),
                paradaManager.buscarParadaPorCodigo("P4"),
                10.2f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("GHI789"),
                conductorManager.buscarConductorPorId("3003")
        );

        RutaVO r4 = new RutaVO(
                4,
                paradaManager.buscarParadaPorCodigo("P11"),
                paradaManager.buscarParadaPorCodigo("P14"),
                18.7f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("JKL012"),
                conductorManager.buscarConductorPorId("3004")
        );
        r4.agregarParada(paradaManager.buscarParadaPorCodigo("P13"));

        RutaVO r5 = new RutaVO(
                5,
                paradaManager.buscarParadaPorCodigo("P17"),
                paradaManager.buscarParadaPorCodigo("P19"),
                9.3f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("MNO345"),
                conductorManager.buscarConductorPorId("3005")
        );
        r5.agregarParada(paradaManager.buscarParadaPorCodigo("P18"));

        RutaVO r6 = new RutaVO(
                6,
                paradaManager.buscarParadaPorCodigo("P6"),
                paradaManager.buscarParadaPorCodigo("P10"),
                14.1f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("PQR678"),
                conductorManager.buscarConductorPorId("3006")
        );

        RutaVO r7 = new RutaVO(
                7,
                paradaManager.buscarParadaPorCodigo("P2"),
                paradaManager.buscarParadaPorCodigo("P16"),
                11.8f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("STU901"),
                conductorManager.buscarConductorPorId("3007")
        );
        r7.agregarParada(paradaManager.buscarParadaPorCodigo("P15"));

        RutaVO r8 = new RutaVO(
                8,
                paradaManager.buscarParadaPorCodigo("P12"),
                paradaManager.buscarParadaPorCodigo("P1"),
                16.4f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("VWX234"),
                conductorManager.buscarConductorPorId("3008")
        );

        RutaVO r9 = new RutaVO(
                9,
                paradaManager.buscarParadaPorCodigo("P3"),
                paradaManager.buscarParadaPorCodigo("P20"),
                20.0f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("YZA567"),
                conductorManager.buscarConductorPorId("3009")
        );

        RutaVO r10 = new RutaVO(
                10,
                paradaManager.buscarParadaPorCodigo("P4"),
                paradaManager.buscarParadaPorCodigo("P7"),
                13.6f,
                LocalDateTime.now(),
                vehiculoManager.buscarVehiculoPorPlaca("BCD890"),
                conductorManager.buscarConductorPorId("3010")
        );

        rutaManager.agregarRuta(r1);
        rutaManager.agregarRuta(r2);
        rutaManager.agregarRuta(r3);
        rutaManager.agregarRuta(r4);
        rutaManager.agregarRuta(r5);
        rutaManager.agregarRuta(r6);
        rutaManager.agregarRuta(r7);
        rutaManager.agregarRuta(r8);
        rutaManager.agregarRuta(r9);
        rutaManager.agregarRuta(r10);
    }  

    /**
     * Precarga un conjunto de pagos de prueba en memoria
     */
    private static void precargarPagos(GestionPagosManager pagosManager) {

        pagosManager.agregarPago(new PagoVO("P001", LocalDateTime.now(), 2500, "Efectivo"));
        pagosManager.agregarPago(new PagoVO("P002", LocalDateTime.now(), 3000, "Tarjeta"));
        pagosManager.agregarPago(new PagoVO("P003", LocalDateTime.now(), 2800, "Nequi"));
        pagosManager.agregarPago(new PagoVO("P004", LocalDateTime.now(), 3200, "Daviplata"));
        pagosManager.agregarPago(new PagoVO("P005", LocalDateTime.now(), 2700, "Efectivo"));
        pagosManager.agregarPago(new PagoVO("P006", LocalDateTime.now(), 3500, "Tarjeta"));
        pagosManager.agregarPago(new PagoVO("P007", LocalDateTime.now(), 2600, "Nequi"));
        pagosManager.agregarPago(new PagoVO("P008", LocalDateTime.now(), 2900, "Daviplata"));
        pagosManager.agregarPago(new PagoVO("P009", LocalDateTime.now(), 3100, "Efectivo"));
        pagosManager.agregarPago(new PagoVO("P010", LocalDateTime.now(), 3300, "Tarjeta"));
        pagosManager.agregarPago(new PagoVO("P011", LocalDateTime.now(), 2750, "Nequi"));
        pagosManager.agregarPago(new PagoVO("P012", LocalDateTime.now(), 2950, "Daviplata"));
        
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
    	        LocalDate.of(2026, 1, 10),
    	        "Cambio de aceite",
    	        150000,
    	        vehiculoManager.buscarVehiculoPorPlaca("ABC123")
    	    );

    	    MantenimientoVO m2 = new MantenimientoVO(
    	        "M002",
    	        LocalDate.of(2026, 2, 15),
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
    	BoletoVO b2 = new BoletoVO(2, LocalDateTime.now(), "A2",
                pasajeroManager.buscarPasajeroPorIdentificacion("1002"),
                rutaManager.buscarRutaPorCodigo(2),pagosManager.buscarPagoPorId("P002"), null);

        BoletoVO b3 = new BoletoVO(3, LocalDateTime.now(), "A3",
                pasajeroManager.buscarPasajeroPorIdentificacion("1003"),
                rutaManager.buscarRutaPorCodigo(3),pagosManager.buscarPagoPorId("P003"), null);

        BoletoVO b4 = new BoletoVO(4, LocalDateTime.now(), "B1",
                pasajeroManager.buscarPasajeroPorIdentificacion("1004"),
                rutaManager.buscarRutaPorCodigo(4),pagosManager.buscarPagoPorId("P004"), null);

        BoletoVO b5 = new BoletoVO(5, LocalDateTime.now(), "B2",
                pasajeroManager.buscarPasajeroPorIdentificacion("1005"),
                rutaManager.buscarRutaPorCodigo(5), pagosManager.buscarPagoPorId("P005"), null);

        BoletoVO b6 = new BoletoVO(6, LocalDateTime.now(), "B3",
                pasajeroManager.buscarPasajeroPorIdentificacion("1006"),
                rutaManager.buscarRutaPorCodigo(6),pagosManager.buscarPagoPorId("P006"), null);

        BoletoVO b7 = new BoletoVO(7, LocalDateTime.now(), "C1",
                pasajeroManager.buscarPasajeroPorIdentificacion("1007"),
                rutaManager.buscarRutaPorCodigo(7),pagosManager.buscarPagoPorId("P007"), null);

        BoletoVO b8 = new BoletoVO(8, LocalDateTime.now(), "C2",
                pasajeroManager.buscarPasajeroPorIdentificacion("1008"),
                rutaManager.buscarRutaPorCodigo(8), pagosManager.buscarPagoPorId("P009"), null);

        BoletoVO b9 = new BoletoVO(9, LocalDateTime.now(), "C3",
                pasajeroManager.buscarPasajeroPorIdentificacion("1009"),
                rutaManager.buscarRutaPorCodigo(9),pagosManager.buscarPagoPorId("P010"), null);

        BoletoVO b10 = new BoletoVO(10, LocalDateTime.now(), "D1",
                pasajeroManager.buscarPasajeroPorIdentificacion("1010"),
                rutaManager.buscarRutaPorCodigo(10),pagosManager.buscarPagoPorId("P011"), null);

        boletoManager.agregarBoleto(b1);
        boletoManager.agregarBoleto(b2);
        boletoManager.agregarBoleto(b3);
        boletoManager.agregarBoleto(b4);
        boletoManager.agregarBoleto(b5);
        boletoManager.agregarBoleto(b6);
        boletoManager.agregarBoleto(b7);
        boletoManager.agregarBoleto(b8);
        boletoManager.agregarBoleto(b9);
        boletoManager.agregarBoleto(b10);

        b1.getRuta().agregarBoleto(b1);
        b2.getRuta().agregarBoleto(b2);
        b3.getRuta().agregarBoleto(b3);
        b4.getRuta().agregarBoleto(b4);
        b5.getRuta().agregarBoleto(b5);
        b6.getRuta().agregarBoleto(b6);
        b7.getRuta().agregarBoleto(b7);
        b8.getRuta().agregarBoleto(b8);
        b9.getRuta().agregarBoleto(b9);
        b10.getRuta().agregarBoleto(b10);
    }
}