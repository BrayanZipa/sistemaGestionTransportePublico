package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class PanelVehiculos extends JPanel {
	
    private GestionVehiculosManager vehiculoManager;
    private GestionRutasManager rutaManager;
    
    private JTextField txtPlaca;
    private JTextField txtModelo;
    private JTextField txtCapacidad;

    private JCheckBox chkDisponible;

    private JTable tablaVehiculos;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;
	
    /**
     * Constructor de la clase PanelVehiculos.
     * 
     * @param vehiculoManager gestor encargado de las operaciones relacionadas con vehículos
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     */
    public PanelVehiculos(GestionVehiculosManager vehiculoManager, GestionRutasManager rutaManager) {
		this.vehiculoManager = vehiculoManager;
		this.rutaManager = rutaManager;

		configurarPanel();
	}
    
    /**
     * Configura la interfaz gráfica del panel, incluyendo formulario, tabla, botones.
     */
    private void configurarPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(C_SECUNDARIO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario = createPanel("Gestión de Vehículos");
        panelFormulario.setLayout(new GridBagLayout());
        
        // ── FORMULARIO ─────────────────────────────────────
        
        GridBagConstraints g = createGbc();
        
        txtPlaca = createTextField(15);
        txtModelo = createTextField(15);
        txtCapacidad = createTextField(15);
        chkDisponible = createCheckBox("Disponible");

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────────
        
        createFormRow(panelFormulario, g, 0, "Placa:", txtPlaca);
        createFormRow(panelFormulario, g, 1, "Modelo:", txtModelo);
        createFormRow(panelFormulario, g, 2, "Capacidad:", txtCapacidad);
        createFormRow(panelFormulario, g, 3, "Estado:", chkDisponible);

        // ── BOTONES ────────────────────────────────────────
        
        JPanel panelBotones = createButtonRow(
        	createBtn("Agregar", C_EXITO, e -> agregarVehiculo()),
        	createBtn("Actualizar", C_PRIMARIO, e -> actualizarVehiculo()),
        	createBtn("Eliminar", C_PELIGRO, e -> eliminarVehiculo()),
        	createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
        );

        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 2;

        panelFormulario.add(panelBotones, g);

        // ── TABLA ──────────────────────────────────────────

        String[] columnas = {
            "Índice",
            "Placa",
            "Modelo",
            "Capacidad",
            "Disponible",
            "Rutas"
        };
        
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaVehiculos = new JTable(modeloTabla);
        
        // ── PANEL TABLA ────────────────────────────────
        
        JPanel panelTabla = createPanel("Vehículos");
        panelTabla.setLayout(new BorderLayout(0, 6));
        panelTabla.add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelFormulario, panelTabla);
        split.setDividerLocation(350);
        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        refrescarTabla = () -> {
            modeloTabla.setRowCount(0);

            for (int i = 0; i < vehiculoManager.obtenerVehiculos().size(); i++) {
                VehiculoVO v = vehiculoManager.obtenerVehiculos().get(i);

                modeloTabla.addRow(new Object[] {
                    i,
                    v.getPlaca(),
                    v.getModelo(),
                    v.getCapacidadPasajeros(),
                    v.isEstadoDisponibilidad() ? "Sí" : "No",
                    v.getRutas().size()
                });
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }
    
    /**
     * Inicializa el listener de selección de la tabla para cargar los datos del vehículo seleccionado en el formulario.
     */
    private void initEventosSeleccion() {
        tablaVehiculos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaVehiculos.getSelectedRow();

            if (fila == -1) return;

            VehiculoVO v = vehiculoManager.obtenerVehiculos().get(fila);

            txtPlaca.setText(v.getPlaca());

            txtModelo.setText(v.getModelo());

            txtCapacidad.setText(
                String.valueOf(
                    v.getCapacidadPasajeros()
                )
            );

            chkDisponible.setSelected(
                v.isEstadoDisponibilidad()
            );
        });
    }

    /**
     * Obtiene los datos del formulario y construye un objeto VehiculoVO.
     * 
     * @return VehiculoVO con los datos ingresados
     */
    private VehiculoVO obtenerVehiculoFormulario() {
        return new VehiculoVO(
            txtPlaca.getText().trim(),
            txtModelo.getText().trim(),
            Integer.parseInt(
                txtCapacidad.getText().trim()
            ),
            chkDisponible.isSelected()
        );
    }
    
    /**
     * Agrega un nuevo vehículo al sistema con los datos del formulario.
     * Muestra un mensaje de éxito o error según el resultado.
     */
    private void agregarVehiculo() {
        try {
        	VehiculoVO v = obtenerVehiculoFormulario();
            vehiculoManager.agregarVehiculo(v);
            refrescarTabla.run();
            
            showInfoMessage(this, "Vehículo agregado correctamente.");

        } catch (Exception ex) {
        	showErrorMessage(this, ex.getMessage());
        }
    }
    
    /**
     * Actualiza el vehículo seleccionado en la tabla con los datos del formulario.
     * Valida que haya una fila seleccionada antes de actualizar.
     */
    private void actualizarVehiculo() {
        int fila = tablaVehiculos.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione un vehículo.");
            return;
        }

        try {
        	VehiculoVO v = obtenerVehiculoFormulario();
            vehiculoManager.actualizarVehiculo(fila, v);
            refrescarTabla.run();
            
            showInfoMessage(this, "Vehículo actualizado correctamente.");

        } catch (Exception ex) {
        	showErrorMessage(this, ex.getMessage());
        }
    }
    
    /**
     * Elimina el vehículo seleccionado en la tabla, previa confirmación del usuario.
     */
    private void eliminarVehiculo() {
        int fila = tablaVehiculos.getSelectedRow();

        if (fila == -1) {
        	showInfoMessage(this, "Seleccione un vehículo.");
            return;
        }
        
        if (!confirmMessage(this, "¿Está seguro de eliminar el vehículo seleccionado?")) return;
     
        vehiculoManager.eliminarVehiculo(fila);
        refrescarTabla.run();
        
        showInfoMessage(this, "Vehículo eliminado correctamente.");
    }
    
    /**
     * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {
        txtPlaca.setText("");
        txtModelo.setText("");
        txtCapacidad.setText("");
        chkDisponible.setSelected(false);
        tablaVehiculos.clearSelection();
    }
}
