package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import co.edu.poligran.paradigmas.backend.vo.PagoVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;


public class PanelPagos extends JPanel {
	
    private GestionPagosManager pagosManager;

    /**
     * Constructor de la clase PanelPagos.
     * 
     * @param pagosManager gestor encargado de las operaciones relacionadas con pagos
     */
    public PanelPagos(GestionPagosManager pagosManager) {
		this.pagosManager = pagosManager;
		
		configurarPanel();
	}
    
    
    private void configurarPanel() {
    	
        setLayout(new BorderLayout(10, 10));
        
        setBackground(C_SECUNDARIO);
        
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario = createPanel("Gestión de Pagos");
        
        panelFormulario.setLayout(new GridBagLayout());

        // ── FORMULARIO ─────────────────────────────────────

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        g.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtIdPago = new JTextField(15);

        JTextField txtMonto = new JTextField(15);

        JComboBox<String> cmbMetodoPago = new JComboBox<>();

        cmbMetodoPago.addItem("");

        cmbMetodoPago.addItem("Efectivo");

        cmbMetodoPago.addItem("Tarjeta");

        cmbMetodoPago.addItem("Nequi");

        cmbMetodoPago.addItem("Daviplata");

        cmbMetodoPago.addItem("Transferencia");

        cmbMetodoPago.setBackground(C_SUPERFICIE);

        // ── FILA 1 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 0;

        panelFormulario.add(
            new JLabel("ID Pago:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtIdPago,
            g
        );

        // ── FILA 2 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 1;

        panelFormulario.add(
            new JLabel("Monto:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            txtMonto,
            g
        );

        // ── FILA 3 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 2;

        panelFormulario.add(
            new JLabel("Método Pago:"),
            g
        );

        g.gridx = 1;

        panelFormulario.add(
            cmbMetodoPago,
            g
        );

        // ── FILA 4 ─────────────────────────────────────────

        g.gridx = 0;
        g.gridy = 3;

        g.gridwidth = 2;

        panelFormulario.add(
            new JLabel(
                "* La fecha se registra automáticamente."
            ),
            g
        );

        // ── BOTONES ────────────────────────────────────────

        JPanel panelBotones = new JPanel(
        	    new FlowLayout(FlowLayout.CENTER, 12, 8)
        );

        panelBotones.setBackground(C_SUPERFICIE);

        JButton btnAgregar =
        	    btn("Agregar", C_EXITO, null);

        	JButton btnActualizar =
        	    btn(" Actualizar", C_ADVERTENCIA, null);

        	JButton btnEliminar =
        	    btn(" Eliminar", C_PELIGRO, null);

        	JButton btnLimpiar =
        	    btn(" Limpiar", Color.GRAY, null);

        panelBotones.add(btnAgregar);

        panelBotones.add(btnActualizar);

        panelBotones.add(btnEliminar);

        panelBotones.add(btnLimpiar);

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
            "ID Pago",
            "Fecha",
            "Monto",
            "Método"
        };

        DefaultTableModel modeloTabla =
            new DefaultTableModel(columnas, 0);

        JTable tablaPagos = new JTable(modeloTabla);

        // ── PANEL TABLA ────────────────────────────────

        JPanel panelTabla = createPanel("Pagos");

        panelTabla.setLayout(new BorderLayout(0, 6));

        panelTabla.add(
            new JScrollPane(tablaPagos),
            BorderLayout.CENTER
        );

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            panelFormulario,
            panelTabla
        );

        split.setDividerLocation(430); 

        add(split, BorderLayout.CENTER);

        // ── FORMATO FECHA ────────────────────────────────

        DateTimeFormatter formato =
            DateTimeFormatter.ofPattern(
                "dd/MM/yyyy HH:mm"
            );

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        Runnable refrescarTabla = () -> {

            modeloTabla.setRowCount(0);

            for (int i = 0;
                 i < pagosManager.obtenerPagos().size();
                 i++) {

                PagoVO p =
                    pagosManager.obtenerPagos().get(i);

                modeloTabla.addRow(new Object[] {
                    i,
                    p.getIdPago(),
                    p.getFecha().format(formato),
                    p.getMonto(),
                    p.getMetodoPago()
                });
            }
        };

        refrescarTabla.run();

        // ── AGREGAR ────────────────────────────────────────

        btnAgregar.addActionListener(e -> {

            try {

                String id =
                    txtIdPago.getText().trim();

                if (id.isEmpty()) {

                    JOptionPane.showMessageDialog(
                        this,
                        "El ID no puede estar vacío."
                    );

                    return;
                }

                double monto =
                    Double.parseDouble(
                        txtMonto.getText()
                            .trim()
                            .replace(".", "")
                    );

                if (monto < 1000) {

                    JOptionPane.showMessageDialog(
                        this,
                        "El monto debe ser mínimo 1.000."
                    );

                    return;
                }

                PagoVO p = new PagoVO(
                    id,
                    LocalDateTime.now(),
                    monto,
                    cmbMetodoPago
                        .getSelectedItem()
                        .toString()
                );

                pagosManager.agregarPago(p);

                refrescarTabla.run();

                JOptionPane.showMessageDialog(
                    this,
                    "Pago agregado correctamente."
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un monto válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
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

            int fila = tablaPagos.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un pago."
                );

                return;
            }

            pagosManager.eliminarPago(fila);

            refrescarTabla.run();
        });

        // ── CARGAR SELECCIÓN ───────────────────────────────

        tablaPagos.getSelectionModel()
            .addListSelectionListener(e -> {

            int fila = tablaPagos.getSelectedRow();

            if (fila == -1) {
                return;
            }

            PagoVO p =
                pagosManager.obtenerPagos()
                    .get(fila);

            txtIdPago.setText(
                p.getIdPago()
            );

            txtMonto.setText(
                String.valueOf(
                    p.getMonto()
                )
            );

            cmbMetodoPago.setSelectedItem(
                p.getMetodoPago()
            );
        });

        // ── ACTUALIZAR ─────────────────────────────────────

        btnActualizar.addActionListener(e -> {

            int fila =
                tablaPagos.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un pago."
                );

                return;
            }

            try {

                String id =
                    txtIdPago.getText().trim();

                double monto =
                    Double.parseDouble(
                        txtMonto.getText()
                            .trim()
                            .replace(".", "")
                    );

                if (monto < 1000) {

                    JOptionPane.showMessageDialog(
                        this,
                        "El monto debe ser mínimo 1.000."
                    );

                    return;
                }

                PagoVO p = new PagoVO(
                    id,
                    pagosManager.obtenerPagos()
                        .get(fila)
                        .getFecha(),
                    monto,
                    cmbMetodoPago
                        .getSelectedItem()
                        .toString()
                );

                pagosManager.actualizarPago(
                    fila,
                    p
                );

                refrescarTabla.run();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un monto válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
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

            txtIdPago.setText("");

            txtMonto.setText("");

            cmbMetodoPago.setSelectedIndex(0);

            tablaPagos.clearSelection();
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