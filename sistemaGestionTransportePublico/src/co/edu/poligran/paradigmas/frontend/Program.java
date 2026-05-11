package co.edu.poligran.paradigmas.frontend;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

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
import co.edu.poligran.paradigmas.frontend.gui.PanelBoletos;
import co.edu.poligran.paradigmas.frontend.gui.PanelConductores;
import co.edu.poligran.paradigmas.frontend.gui.PanelEmpleados;
import co.edu.poligran.paradigmas.frontend.gui.PanelMantenimientos;
import co.edu.poligran.paradigmas.frontend.gui.PanelPagos;
import co.edu.poligran.paradigmas.frontend.gui.PanelParadas;
import co.edu.poligran.paradigmas.frontend.gui.PanelPasajeros;
import co.edu.poligran.paradigmas.frontend.gui.PanelRutas;
import co.edu.poligran.paradigmas.frontend.gui.PanelTarifas;
import co.edu.poligran.paradigmas.frontend.gui.PanelVehiculos;

public class Program {
	
	static Scanner sc = new Scanner(System.in);
	private static final GestionConductoresManager conductorManager = new GestionConductoresManager();
	private static final GestionEmpleadosManager empleadoManager = new GestionEmpleadosManager();
	private static final GestionPasajerosManager pasajeroManager = new GestionPasajerosManager();
	private static final GestionVehiculosManager vehiculoManager = new GestionVehiculosManager();
	private static final GestionParadasManager paradaManager = new GestionParadasManager();
	private static final GestionBoletosManager boletoManager = new GestionBoletosManager();
	private static final GestionRutasManager rutaManager = new GestionRutasManager();
	private static final GestionPagosManager pagosManager = new GestionPagosManager();
	private static final GestionTarifasManager tarifasManager = new GestionTarifasManager();
	private static final GestionMantenimientoManager mantenimientoManager = new GestionMantenimientoManager();

	/**
	 * Punto de entrada principal de la aplicación.
	 * Carga los datos iniciales y arranca la interfaz gráfica.
	 * 
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		DataLoader.cargarTodo(
			empleadoManager, 
			conductorManager, 
			pasajeroManager, 
			vehiculoManager, 
			paradaManager, 
			rutaManager,
			boletoManager, 
			pagosManager, 
			tarifasManager, 
			mantenimientoManager
		);

//        iniciarConsola();

		iniciarGui();
	}
	
	/**
	 * Inicia la interfaz de usuario basada en consola.
	 * Muestra un menú de opciones y gestiona la navegación del usuario.
	 */
	private static void iniciarConsola() {
	    Scanner sc = new Scanner(System.in);
	    int opcion = 0;

	    do {
	        System.out.println("\n=== SISTEMA DE GESTIÓN DE TRANSPORTE ===");
	        System.out.println("1. Gestionar Empleados");
	        System.out.println("2. Gestionar Conductores");
	        System.out.println("3. Gestionar Vehículos");
	        System.out.println("4. Gestionar Paradas");
	        System.out.println("5. Gestionar Rutas");
	        System.out.println("6. Gestionar Pasajeros");
	        System.out.println("7. Gestionar Boletos");
	        System.out.println("8. Gestionar Pagos");
	        System.out.println("9. Gestionar Tarifa");
	        System.out.println("10. Gestionar Mantenimiento");
	        System.out.println("11. Salir");
	        System.out.print("\nSeleccione una opción: ");

	        try {
	            opcion = sc.nextInt();
	            sc.nextLine();

	        } catch (InputMismatchException e) {
	            System.out.println("Entrada inválida. Por favor ingrese un número.");
	            sc.nextLine();
	            opcion = -1;
	        }

	        switch (opcion) {

	            case 1:
	                new MenuEmpleados(empleadoManager).mostrarMenu();
	                break;

	            case 2:
	                new MenuConductores(conductorManager).mostrarMenu();
	                break;

	            case 3:
	                new MenuVehiculos(vehiculoManager, rutaManager).mostrarMenu();
	                break;

	            case 4:
	                new MenuParadas(paradaManager, rutaManager).mostrarMenu();
	                break;

	            case 5:
	                new MenuRutas(
	                    rutaManager,
	                    paradaManager,
	                    boletoManager,
	                    vehiculoManager,
	                    conductorManager
	                ).mostrarMenu();
	                break;

	            case 6:
	                new MenuPasajeros(pasajeroManager).mostrarMenu();
	                break;

	            case 7:
	                new MenuBoletos(
	                    boletoManager,
	                    pasajeroManager,
	                    rutaManager,
	                    pagosManager,
	                    tarifasManager
	                ).mostrarMenu();
	                break;

	            case 8:
	                new MenuPagos(pagosManager).mostrarMenu();
	                break;

	            case 9:
	                new MenuTarifas(tarifasManager).mostrarMenu();
	                break;

	            case 10:
	                new MenuMantenimientos(
	                    mantenimientoManager,
	                    vehiculoManager
	                ).mostrarMenu();
	                break;

	            case 11:
	                System.out.println("Saliendo del sistema...");
	                break;

	            default:
	                System.out.println("Opcion invalida.");
	        }

	    } while (opcion != 11);

	    sc.close();
	}
	
	/**
	 * Inicia la interfaz gráfica de usuario (GUI) usando Swing.
	 * Configura la ventana principal y sus componentes.
	 */
	private static void iniciarGui() {
	    try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName()
	        );
	    } catch (Exception e) {}

	    SwingUtilities.invokeLater(() -> {
	        JFrame frame = new JFrame("Sistema de Gestión de Transporte");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1400, 800);
	        frame.setLocationRelativeTo(null);
	        frame.setLayout(new BorderLayout());
	
	        frame.add(crearHeader(), BorderLayout.NORTH);
	        frame.add(crearTabs(), BorderLayout.CENTER);
	
	        frame.setVisible(true);
	    });
	}
	
	/**
	 * Crea el panel superior (cabecera) de la interfaz gráfica.
	 * Contiene el título del sistema y subtítulos.
	 * 
	 * @return JPanel que representa la cabecera.
	 */
	private static JPanel crearHeader() {
	    JPanel p = new JPanel(new BorderLayout());

	    p.setBackground(new Color(18, 78, 160));

	    p.setBorder(
	        new EmptyBorder(14, 22, 14, 22)
	    );

	    JLabel titulo = new JLabel(
	        "Sistema de Gestión de Transporte"
	    );

	    titulo.setFont(
	        new Font("Segoe UI", Font.BOLD, 20)
	    );

	    titulo.setForeground(Color.WHITE);

	    JLabel sub = new JLabel(
	        "Politécnico Grancolombiano — Paradigmas de Programación"
	    );

	    sub.setFont(
	        new Font("Segoe UI", Font.PLAIN, 11)
	    );

	    sub.setForeground(
	        new Color(180, 210, 255)
	    );

	    p.add(titulo, BorderLayout.WEST);
	    p.add(sub, BorderLayout.EAST);

	    return p;
	}
	
	/**
	 * Crea el panel de pestañas para navegar entre los diferentes módulos de gestión.
	 * 
	 * @return JTabbedPane con las pestañas de la aplicación.
	 */
	private static JTabbedPane crearTabs() {
	    JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);

	    tabs.setFont(
	        new Font("Segoe UI", Font.BOLD, 13)
	    );

	    tabs.setBorder(
	        new EmptyBorder(6, 8, 6, 8)
	    );

	    tabs.addTab(
	        "Empleados",
	        new PanelEmpleados(empleadoManager)
	    );

	    tabs.addTab(
	        "Conductores",
	        new PanelConductores(conductorManager)
	    );

	    tabs.addTab(
	        "Vehículos",
	        new PanelVehiculos(vehiculoManager, rutaManager)
	    );

	    tabs.addTab(
	        "Paradas",
	        new PanelParadas(paradaManager, rutaManager)
	    );

	    tabs.addTab(
	        "Rutas",
	        new PanelRutas(
	            rutaManager,
	            paradaManager,
	            boletoManager,
	            vehiculoManager,
	            conductorManager
	        )
	    );
	    
	    tabs.addTab(
	    	"Pasajeros",
		    new PanelPasajeros(pasajeroManager)
		);

	    tabs.addTab(
	        "Boletos",
	        new PanelBoletos(
	            boletoManager,
	            pasajeroManager,
	            rutaManager,
	            pagosManager,
	            tarifasManager
	        )
	    );

	    tabs.addTab(
	        "Pagos",
	        new PanelPagos(pagosManager)
	    );

	    tabs.addTab(
	        "Tarifas",
	        new PanelTarifas(tarifasManager)
	    );

	    tabs.addTab(
	        "Mantenimiento",
	        new PanelMantenimientos(
	            mantenimientoManager,
	            vehiculoManager
	        )
	    );

	    return tabs;
	}
}