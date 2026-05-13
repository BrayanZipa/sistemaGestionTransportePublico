package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class PanelConductores extends JPanel {
	
	private GestionConductoresManager conductorManager;

	/**
	 * Constructor de la clase PanelConductores.
	 * 
	 * @param conductorManager gestor encargado de las operaciones relacionadas con conductores
	 */
	public PanelConductores(GestionConductoresManager conductorManager) {
		this.conductorManager = conductorManager;
		
		configurarPanel();
	}
    
    
    private void configurarPanel() {
    	
        setLayout(new BorderLayout(10, 10));
        
        setBackground(C_SECUNDARIO);
        
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario = createPanel("Gestión de Conductores");
        
        panelFormulario.setLayout(new GridBagLayout());

        // ── FORMULARIO ─────────────────────────────────────

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        g.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNombre = new JTextField(15);

        JTextField txtEmail = new JTextField(15);

        JTextField txtIdentificacion = new JTextField(15);

        JTextField txtLicencia = new JTextField(15);

        // ── FILA 1 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 0;

        panelFormulario.add(
            new JLabel("Nombre:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtNombre,
            g
        );

        // ── FILA 2 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 1;

        panelFormulario.add(
            new JLabel("Email:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtEmail,
            g
        );

        // ── FILA 3 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 2;

        panelFormulario.add(
            new JLabel("Identificación:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtIdentificacion,
            g
        );

        // ── FILA 4 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 3;

        panelFormulario.add(
            new JLabel("Licencia:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtLicencia,
            g
        );

        // ── FILA 5 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 4;

        g.gridwidth = 2;

        panelFormulario.add(
        	new JLabel(
        	   "* Licencia válida: A1, B2, C1..."
        	),
            g
        );

        // ── BOTONES ────────────────────────────────────────

        JPanel panelBotones = new JPanel(
            new FlowLayout(FlowLayout.CENTER, 5, 4)
        );

        panelBotones.setBackground(C_SUPERFICIE);

        JButton btnAgregar =
            btn("Agregar", C_EXITO, null);

        JButton btnActualizar =
            btn("Actualizar", C_ADVERTENCIA, null);

        JButton btnEliminar =
            btn("Eliminar", C_PELIGRO, null);

        JButton btnLimpiar =
            btn("Limpiar", Color.GRAY, null);

        panelBotones.add(btnAgregar);

        panelBotones.add(btnActualizar);

        panelBotones.add(btnEliminar);

        panelBotones.add(btnLimpiar);

        g.gridx = 0;
        g.gridy = 5;

        g.gridwidth = 2;

        panelFormulario.add(
            panelBotones,
            g
        );

        // ── TABLA ──────────────────────────────────────────

        String[] columnas = {
            "Índice",
            "Nombre",
            "Email",
            "Identificación",
            "Licencia"
        };

        DefaultTableModel modeloTabla =
            new DefaultTableModel(columnas, 0);

        JTable tablaConductores =
            new JTable(modeloTabla);

        // ── PANEL TABLA ────────────────────────────────

        JPanel panelTabla =
            createPanel("Conductores");

        panelTabla.setLayout(
            new BorderLayout(0, 6)
        );

        panelTabla.add(
            new JScrollPane(tablaConductores),
            BorderLayout.CENTER
        );

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            panelFormulario,
            panelTabla
        );

        split.setDividerLocation(400);

        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        Runnable refrescarTabla = () -> {

            modeloTabla.setRowCount(0);

            for (int i = 0;
                 i < conductorManager.obtenerConductores().size();
                 i++) {

                ConductorVO c =
                    conductorManager.obtenerConductores().get(i);

                modeloTabla.addRow(new Object[] {
                    i,
                    c.getNombre(),
                    c.getEmail(),
                    c.getIdentificacion(),
                    c.getLicencia()
                });
            }
        };

        refrescarTabla.run();

        // ── AGREGAR ────────────────────────────────────────

        btnAgregar.addActionListener(e -> {

            try {

                String nombre =
                    txtNombre.getText().trim();

                String email =
                    txtEmail.getText().trim();

                String identificacion =
                    txtIdentificacion.getText().trim();

                String licencia =
                    txtLicencia.getText().trim();

                if (nombre.isEmpty()) {

                    JOptionPane.showMessageDialog(
                        this,
                        "El nombre no puede estar vacío."
                    );

                    return;
                }

                if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

                    JOptionPane.showMessageDialog(
                        this,
                        "El nombre solo debe contener letras."
                    );

                    return;
                }

                if (!email.contains("@")) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un email válido."
                    );

                    return;
                }

                if (!licencia.matches("[A-Z]\\d")) {
                    JOptionPane.showMessageDialog(this,
                        "Licencia inválida. Ejemplo válido: A1, B2, C1.",
                        "Error de validación", JOptionPane.ERROR_MESSAGE);
                    return;
                }

 
                ConductorVO c = new ConductorVO(
                    nombre,
                    email,
                    "Conductor",
                    identificacion,
                    licencia
                );

                conductorManager.agregarConductor(c);

                refrescarTabla.run();

                JOptionPane.showMessageDialog(
                    this,
                    "Conductor agregado correctamente."
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ── ELIMINAR ───────────────────────────────────────

        btnEliminar.addActionListener(e -> {

            int fila =
                tablaConductores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un conductor."
                );

                return;
            }

            conductorManager.eliminarConductor(fila);

            refrescarTabla.run();
        });

        // ── CARGAR SELECCIÓN ───────────────────────────────

        tablaConductores.getSelectionModel()
            .addListSelectionListener(e -> {

            int fila =
                tablaConductores.getSelectedRow();

            if (fila == -1) {
                return;
            }

            ConductorVO c =
                conductorManager.obtenerConductores()
                    .get(fila);

            txtNombre.setText(c.getNombre());

            txtEmail.setText(c.getEmail());

            txtIdentificacion.setText(
                c.getIdentificacion()
            );

            txtLicencia.setText(
                c.getLicencia()
            );
        });

        // ── ACTUALIZAR ─────────────────────────────────────

        btnActualizar.addActionListener(e -> {

            int fila =
                tablaConductores.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un conductor."
                );

                return;
            }

            try {

                ConductorVO c =
                    conductorManager.obtenerConductores()
                        .get(fila);

                String nuevoNombre =
                    txtNombre.getText().trim();

                if (!nuevoNombre.isEmpty()) {

                    if (nuevoNombre.matches(
                        "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"
                    )) {

                        c.setNombre(nuevoNombre);

                    } else {

                        JOptionPane.showMessageDialog(
                            this,
                            "El nombre solo debe contener letras. Se conserva el anterior."
                        );
                    }
                }

                String nuevoEmail =
                    txtEmail.getText().trim();

                if (!nuevoEmail.isEmpty()) {

                    if (nuevoEmail.contains("@")) {

                        c.setEmail(nuevoEmail);

                    } else {

                        JOptionPane.showMessageDialog(
                            this,
                            "Email inválido. Se conserva el anterior."
                        );
                    }
                }

                String nuevaLicencia =
                    txtLicencia.getText()
                        .trim()
                        .toUpperCase();

                if (!nuevaLicencia.isEmpty()) {

                    if (nuevaLicencia.matches(
                        "[A-Z]\\d"
                    )) {

                        c.setLicencia(nuevaLicencia);

                    } else {

                        JOptionPane.showMessageDialog(
                            this,
                            "Licencia inválida. Ejemplo válido: A1, B2, C1. Se conserva la anterior."
                        );
                    }
                }

                conductorManager.actualizarConductor(
                    fila,
                    c
                );

                refrescarTabla.run();

                JOptionPane.showMessageDialog(
                    this,
                    "Conductor actualizado correctamente."
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        // ── LIMPIAR ────────────────────────────────────────

        btnLimpiar.addActionListener(e -> {

            txtNombre.setText("");

            txtEmail.setText("");

            txtIdentificacion.setText("");

            txtLicencia.setText("");

            tablaConductores.clearSelection();
        });
    }

    
    private JButton btn(
        String texto,
        Color color,
        java.awt.event.ActionListener accion
    ) {

        JButton boton = new JButton(texto);

        boton.setBackground(color);

        boton.setForeground(Color.WHITE);

        boton.setFocusPainted(false);

        boton.setBorderPainted(false);

        boton.setOpaque(true);

        if (accion != null) {

            boton.addActionListener(accion);
        }

        return boton;
    }
}
