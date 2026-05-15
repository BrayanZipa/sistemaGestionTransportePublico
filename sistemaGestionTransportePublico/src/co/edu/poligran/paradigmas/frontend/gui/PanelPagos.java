package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import co.edu.poligran.paradigmas.backend.vo.PagoVO;

import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class PanelPagos extends JPanel {

    private GestionPagosManager pagosManager;

    private JTextField txtIdPago;
    private JTextField txtMonto;
    private JTextField txtBuscar;

    private JComboBox<String> cmbMetodoPago;

    private JTable tablaPagos;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;

    /**
     * Constructor de la clase PanelPagos.
     * 
     * @param pagosManager gestor encargado de las operaciones relacionadas con pagos
     */
    public PanelPagos(GestionPagosManager pagosManager) {
        this.pagosManager = pagosManager;

        configurarPanel();
    }

    /**
     * Configura la interfaz gráfica del panel,
     * incluyendo formulario, buscador, tabla y botones.
     */
    private void configurarPanel() {

        setLayout(new BorderLayout(10, 10));
        setBackground(C_SECUNDARIO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ── PANEL ZONA IZQUIERDA ─────────────────────────────────────

        JPanel leftPanel = createPanel("panel");

        leftPanel.setLayout(
            new BoxLayout(leftPanel, BoxLayout.Y_AXIS)
        );

        leftPanel.setBorder(
            new EmptyBorder(4, 2, 4, 6)
        );

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario =
            createPanel("Gestión de Pagos");

        panelFormulario.setLayout(
            new GridBagLayout()
        );

        // ── FORMULARIO ─────────────────────────────────────

        GridBagConstraints g = createGbc();

        txtIdPago = createTextField(15);

        txtMonto = createTextField(15);

        txtBuscar = createTextField(15);

        cmbMetodoPago = new JComboBox<>();

        cmbMetodoPago.addItem("");
        cmbMetodoPago.addItem("Efectivo");
        cmbMetodoPago.addItem("Tarjeta");
        cmbMetodoPago.addItem("Nequi");
        cmbMetodoPago.addItem("Daviplata");
        cmbMetodoPago.addItem("Transferencia");

        cmbMetodoPago.setBackground(C_SUPERFICIE);

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────

        createFormRow(
            panelFormulario,
            g,
            0,
            "ID Pago:",
            txtIdPago
        );

        createFormRow(
            panelFormulario,
            g,
            1,
            "Monto:",
            txtMonto
        );

        createFormRow(
            panelFormulario,
            g,
            2,
            "Método Pago:",
            cmbMetodoPago
        );

        // ── BOTONES ────────────────────────────────────────

        JPanel panelBotones = createButtonRow(

            createBtn(
                "Agregar",
                C_EXITO,
                e -> agregarPago()
            ),

            createBtn(
                "Actualizar",
                C_PRIMARIO,
                e -> actualizarPago()
            ),

            createBtn(
                "Eliminar",
                C_PELIGRO,
                e -> eliminarPago()
            ),

            createBtn(
                "Limpiar",
                C_GRIS,
                e -> limpiarFormulario()
            )
        );

        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;

        panelFormulario.add(
            panelBotones,
            g
        );

        // ── PANEL BUSCADOR ────────────────────────────────────────

        JPanel panelBuscador =
            createPanel("Buscar pago");

        panelBuscador.setLayout(
            new GridBagLayout()
        );

        GridBagConstraints g2 = createGbc();

        createFormRow(
            panelBuscador,
            g2,
            0,
            "Buscar ID:",
            txtBuscar
        );

        JPanel rowBuscar = createButtonRow(

            createBtn(
                "Buscar",
                C_PRIMARIO,
                e -> buscarPago()
            )
        );

        g2.gridx = 0;
        g2.gridy = 1;
        g2.gridwidth = 2;

        panelBuscador.add(
            rowBuscar,
            g2
        );

        leftPanel.add(panelFormulario);

        leftPanel.add(
            Box.createVerticalStrut(10)
        );

        leftPanel.add(panelBuscador);

        // ── TABLA ──────────────────────────────────────────

        String[] columnas = {
            "Índice",
            "ID Pago",
            "Fecha",
            "Monto",
            "Método"
        };

        modeloTabla =
            new DefaultTableModel(columnas, 0);

        tablaPagos = new JTable(modeloTabla);

        // ── PANEL TABLA ────────────────────────────────

        JPanel panelTabla =
            createPanel("Pagos");

        panelTabla.setLayout(
            new BorderLayout(0, 6)
        );

        panelTabla.add(
            new JScrollPane(tablaPagos),
            BorderLayout.CENTER
        );

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            leftPanel,
            panelTabla
        );

        split.setDividerLocation(380);

        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        DateTimeFormatter formato =
            DateTimeFormatter.ofPattern(
                "dd/MM/yyyy HH:mm"
            );

        refrescarTabla = () -> {

            modeloTabla.setRowCount(0);

            for (
                int i = 0;
                i < pagosManager.obtenerPagos().size();
                i++
            ) {

                PagoVO p =
                    pagosManager.obtenerPagos().get(i);

                modeloTabla.addRow(
                    new Object[] {
                        i,
                        p.getIdPago(),
                        p.getFecha().format(formato),
                        p.getMonto(),
                        p.getMetodoPago()
                    }
                );
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }

    /**
     * Inicializa el listener de selección de la tabla
     * para cargar los datos del pago seleccionado
     * en el formulario.
     */
    private void initEventosSeleccion() {

        tablaPagos.getSelectionModel()
            .addListSelectionListener(e -> {

            int fila =
                tablaPagos.getSelectedRow();

            if (fila == -1) return;

            PagoVO p =
                pagosManager.obtenerPagos().get(fila);

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
    }

    /**
     * Obtiene los datos del formulario
     * y construye un objeto PagoVO.
     * 
     * @return PagoVO con los datos ingresados
     */
    private PagoVO obtenerPagoFormulario() {

        return new PagoVO(

            txtIdPago.getText().trim(),

            LocalDateTime.now(),

            Double.parseDouble(
                txtMonto.getText()
                    .trim()
                    .replace(".", "")
            ),

            cmbMetodoPago
                .getSelectedItem()
                .toString()
        );
    }

    /**
     * Agrega un nuevo pago al sistema
     * con los datos del formulario.
     */
    private void agregarPago() {

        try {

            if (
                txtIdPago.getText()
                    .trim()
                    .isEmpty()
            ) {

                showInfoMessage(
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

                showInfoMessage(
                    this,
                    "El monto debe ser mínimo 1.000."
                );

                return;
            }

            PagoVO p =
                obtenerPagoFormulario();

            pagosManager.agregarPago(p);

            refrescarTabla.run();

            showInfoMessage(
                this,
                "Pago agregado correctamente."
            );

        } catch (Exception ex) {

            showErrorMessage(
                this,
                ex.getMessage()
            );
        }
    }

    /**
     * Actualiza el pago seleccionado
     * con los datos del formulario.
     */
    private void actualizarPago() {

        int fila =
            tablaPagos.getSelectedRow();

        if (fila == -1) {

            showInfoMessage(
                this,
                "Seleccione un pago."
            );

            return;
        }

        try {

            double monto =
                Double.parseDouble(
                    txtMonto.getText()
                        .trim()
                        .replace(".", "")
                );

            if (monto < 1000) {

                showInfoMessage(
                    this,
                    "El monto debe ser mínimo 1.000."
                );

                return;
            }

            PagoVO pagoAnterior =
                pagosManager.obtenerPagos().get(fila);

            PagoVO p = new PagoVO(

                txtIdPago.getText().trim(),

                pagoAnterior.getFecha(),

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

            showInfoMessage(
                this,
                "Pago actualizado correctamente."
            );

        } catch (Exception ex) {

            showErrorMessage(
                this,
                ex.getMessage()
            );
        }
    }

    /**
     * Elimina el pago seleccionado en la tabla,
     * previa confirmación del usuario.
     */
    private void eliminarPago() {

        int fila =
            tablaPagos.getSelectedRow();

        if (fila == -1) {

            showInfoMessage(
                this,
                "Seleccione un pago."
            );

            return;
        }

        if (
            !confirmMessage(
                this,
                "¿Está seguro de eliminar el pago seleccionado?"
            )
        ) return;

        pagosManager.eliminarPago(fila);

        refrescarTabla.run();

        showInfoMessage(
            this,
            "Pago eliminado correctamente."
        );
    }

    /**
     * Limpia todos los campos del formulario
     * y deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {

        txtIdPago.setText("");

        txtMonto.setText("");

        cmbMetodoPago.setSelectedIndex(0);

        tablaPagos.clearSelection();
    }

    /**
     * Busca un pago por ID y lo resalta en la tabla.
     */
    private void buscarPago() {

        try {

            PagoVO p =
                pagosManager.buscarPagoPorId(
                    txtBuscar.getText().trim()
                );

            if (p != null) {

                highlightRow(
                    tablaPagos,
                    modeloTabla,
                    txtBuscar.getText().trim(),
                    1
                );

            } else {

                showInfoMessage(
                    this,
                    "No se encontró el pago \""
                    + txtBuscar.getText()
                    + "\"."
                );
            }

        } catch (Exception ex) {

            showErrorMessage(
                this,
                ex.getMessage()
            );
        }
    }
}