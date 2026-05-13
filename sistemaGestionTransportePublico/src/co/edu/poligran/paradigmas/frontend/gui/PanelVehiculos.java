package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
    
 
    
    private void configurarPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(C_SECUNDARIO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario = createPanel("Gestión de Vehículos");
        panelFormulario.setLayout(new GridBagLayout());
        
        // ── FORMULARIO ─────────────────────────────────────
        
        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        g.fill = GridBagConstraints.HORIZONTAL;
        
        

//        JTextField txtPlaca = new JTextField(15);
//
//        JTextField txtModelo = new JTextField(15);
//
//        JTextField txtCapacidad = new JTextField(15);
//
//        JCheckBox chkDisponible = new JCheckBox("Disponible");
//
//        chkDisponible.setBackground(C_SUPERFICIE);
        
        
        txtPlaca = new JTextField(15);
        
        txtModelo = new JTextField(15);

        txtCapacidad = new JTextField(15);

        chkDisponible = new JCheckBox("Disponible");
        
        chkDisponible.setBackground(C_SUPERFICIE);


        
        

        // ── FILA 1 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 0;

        panelFormulario.add(
            new JLabel("Placa:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtPlaca,
            g
        );

        // ── FILA 2 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 1;

        panelFormulario.add(
            new JLabel("Modelo:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtModelo,
            g
        );

        // ── FILA 3 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 2;

        panelFormulario.add(
            new JLabel("Capacidad:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtCapacidad,
            g
        );

        // ── FILA 4 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 3;

        panelFormulario.add(
            new JLabel("Estado:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            chkDisponible,
            g
        );

        // ── BOTONES ────────────────────────────────────────

//        JPanel panelBotones = new JPanel(
//            new FlowLayout(FlowLayout.CENTER, 5, 4)
//        );    
//        
//        panelBotones.setBackground(C_SUPERFICIE);
//
//        JButton btnAgregar = new JButton("Agregar");
//
//        JButton btnActualizar = new JButton("Actualizar");
//
//        JButton btnEliminar = new JButton("Eliminar");
//
//        JButton btnLimpiar = new JButton("Limpiar");
//
//        panelBotones.add(btnAgregar);
//
//        panelBotones.add(btnActualizar);
//
//        panelBotones.add(btnEliminar);
//
//        panelBotones.add(btnLimpiar);
        
        
        
        JPanel panelBotones = createButtonRow(

        		createBtn(
        	        "Agregar",
        	        C_EXITO,
        	        e -> agregarVehiculo()
        	    ),

        		createBtn(
        	        "Actualizar",
        	        C_PRIMARIO,
        	        e -> actualizarVehiculo()
        	    ),

        		createBtn(
        	        "Eliminar",
        	        C_PELIGRO,
        	        e -> eliminarVehiculo()
        	    ),

        		createBtn(
        	        "Limpiar",
        	        C_GRIS,
        	        e -> limpiarFormulario()
        	    )
        	);
        
        
        

        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 2;

        panelFormulario.add(
            panelBotones,
            g
        );

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

//        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
//        JTable tablaVehiculos = new JTable(modeloTabla);
        
        // ── PANEL TABLA ────────────────────────────────
        
        JPanel panelTabla = createPanel("Vehículos");
        panelTabla.setLayout(new BorderLayout(0, 6));
        panelTabla.add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            panelFormulario,
            panelTabla
        );

        split.setDividerLocation(350);

        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

//        Runnable refrescarTabla = () -> {
        refrescarTabla = () -> {

            modeloTabla.setRowCount(0);

            for (int i = 0;
                 i < vehiculoManager.obtenerVehiculos().size();
                 i++) {

                VehiculoVO v =
                    vehiculoManager.obtenerVehiculos().get(i);

                modeloTabla.addRow(new Object[] {
                    i,
                    v.getPlaca(),
                    v.getModelo(),
                    v.getCapacidadPasajeros(),
                    v.isEstadoDisponibilidad()
                        ? "Sí"
                        : "No",
                    v.getRutas().size()
                });
            }
        };

        refrescarTabla.run();

        // ── AGREGAR ────────────────────────────────────────

//        btnAgregar.addActionListener(e -> {
//
//            try {
//
//                VehiculoVO v = new VehiculoVO(
//                    txtPlaca.getText().trim(),
//                    txtModelo.getText().trim(),
//                    Integer.parseInt(
//                        txtCapacidad.getText().trim()
//                    ),
//                    chkDisponible.isSelected()
//                );
//
//                vehiculoManager.agregarVehiculo(v);
//
//                refrescarTabla.run();
//
//                JOptionPane.showMessageDialog(
//                    this,
//                    "Vehículo agregado correctamente."
//                );
//
//            } catch (Exception ex) {
//
//                JOptionPane.showMessageDialog(
//                    this,
//                    ex.getMessage(),
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE
//                );
//            }
//        });

        // ── ELIMINAR ───────────────────────────────────────

//        btnEliminar.addActionListener(e -> {
//
//            int fila = tablaVehiculos.getSelectedRow();
//
//            if (fila == -1) {
//
//                JOptionPane.showMessageDialog(
//                    this,
//                    "Seleccione un vehículo."
//                );
//
//                return;
//            }
//
//            vehiculoManager.eliminarVehiculo(fila);
//
//            refrescarTabla.run();
//        });

        // ── CARGAR SELECCIÓN ───────────────────────────────

        tablaVehiculos.getSelectionModel()
            .addListSelectionListener(e -> {

            int fila = tablaVehiculos.getSelectedRow();

            if (fila == -1) {
                return;
            }

            VehiculoVO v =
                vehiculoManager.obtenerVehiculos()
                    .get(fila);

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

        // ── ACTUALIZAR ─────────────────────────────────────

//        btnActualizar.addActionListener(e -> {
//
//            int fila =
//                tablaVehiculos.getSelectedRow();
//
//            if (fila == -1) {
//
//                JOptionPane.showMessageDialog(
//                    this,
//                    "Seleccione un vehículo."
//                );
//
//                return;
//            }
//
//            try {
//
//                VehiculoVO v = new VehiculoVO(
//                    txtPlaca.getText().trim(),
//                    txtModelo.getText().trim(),
//                    Integer.parseInt(
//                        txtCapacidad.getText().trim()
//                    ),
//                    chkDisponible.isSelected()
//                );
//
//                vehiculoManager.actualizarVehiculo(
//                    fila,
//                    v
//                );
//
//                refrescarTabla.run();
//
//            } catch (Exception ex) {
//
//                JOptionPane.showMessageDialog(
//                    this,
//                    ex.getMessage(),
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE
//                );
//            }
//        });

        // ── LIMPIAR ────────────────────────────────────────

//        btnLimpiar.addActionListener(e -> {
//
//            txtPlaca.setText("");
//
//            txtModelo.setText("");
//
//            txtCapacidad.setText("");
//
//            chkDisponible.setSelected(false);
//
//            tablaVehiculos.clearSelection();
//        });
    }
    
    private void agregarVehiculo() {
        try {
            VehiculoVO v = new VehiculoVO(
                txtPlaca.getText().trim(),
                txtModelo.getText().trim(),
                Integer.parseInt(
                    txtCapacidad.getText().trim()
                ),
                chkDisponible.isSelected()
            );

            vehiculoManager.agregarVehiculo(v);

            refrescarTabla.run();

            JOptionPane.showMessageDialog(
                this,
                "Vehículo agregado correctamente."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void actualizarVehiculo() {
        int fila = tablaVehiculos.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Seleccione un vehículo."
            );

            return;
        }

        try {

            VehiculoVO v = new VehiculoVO(
                txtPlaca.getText().trim(),
                txtModelo.getText().trim(),
                Integer.parseInt(
                    txtCapacidad.getText().trim()
                ),
                chkDisponible.isSelected()
            );

            vehiculoManager.actualizarVehiculo(
                fila,
                v
            );

            refrescarTabla.run();

            JOptionPane.showMessageDialog(
                this,
                "Vehículo actualizado correctamente."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void eliminarVehiculo() {
        int fila = tablaVehiculos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Seleccione un vehículo."
            );

            return;
        }

        vehiculoManager.eliminarVehiculo(fila);
        refrescarTabla.run();
    }
    
    private void limpiarFormulario() {
        txtPlaca.setText("");
        txtModelo.setText("");
        txtCapacidad.setText("");
        chkDisponible.setSelected(false);
        tablaVehiculos.clearSelection();
    }
}
