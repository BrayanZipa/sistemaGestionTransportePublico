package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionMantenimientoManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.MantenimientoVO;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class PanelMantenimientos extends JPanel {

    private GestionMantenimientoManager mantenimientoManager;
    private GestionVehiculosManager vehiculoManager;

    private JTextField txtId;
    private JSpinner spFecha;
    private JTextField txtDescripcion;
    private JTextField txtCosto;
    private JComboBox<String> cbVehiculo;
    private JTextField txtBuscar;

    private JTable tablaMantenimientos;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;

    /**
     * Constructor de la clase PanelMantenimientos.
     *
     * @param mantenimientoManager gestor encargado de las operaciones relacionadas con mantenimientos
     * @param vehiculoManager      gestor encargado de las operaciones relacionadas con vehículos
     */
    public PanelMantenimientos(GestionMantenimientoManager mantenimientoManager, GestionVehiculosManager vehiculoManager) {
        this.mantenimientoManager = mantenimientoManager;
        this.vehiculoManager = vehiculoManager;

        configurarPanel();
    }

    /**
     * Configura la interfaz gráfica del panel, incluyendo formulario, tabla y botones.
     */
    private void configurarPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(C_SECUNDARIO);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // ── PANEL ZONA IZQUIERDA ─────────────────────────────────────

        JPanel leftPanel = createPanel("panel");
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(4, 2, 4, 6));

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario = createPanel("Gestión de Mantenimientos");
        panelFormulario.setLayout(new GridBagLayout());

        // ── FORMULARIO ─────────────────────────────────────

        GridBagConstraints g = createGbc();

        txtId = createTextField(15);
        spFecha = createDateField(0);
        txtDescripcion = createTextField(15);
        txtCosto = createTextField(15);
        txtBuscar = createTextField(15);

        List<VehiculoVO> vehiculos = vehiculoManager.obtenerVehiculos();
        String[] placas = new String[vehiculos.size()];
        for (int i = 0; i < vehiculos.size(); i++) {
            placas[i] = vehiculos.get(i).getPlaca();
        }
        cbVehiculo = createComboBox(placas);

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────────

        createFormRow(panelFormulario, g, 0, "ID Mantenimiento:", txtId);
        createFormRow(panelFormulario, g, 1, "Fecha:", spFecha);
        createFormRow(panelFormulario, g, 2, "Descripción", txtDescripcion);
        createFormRow(panelFormulario, g, 3, "Costo:", txtCosto);
        createFormRow(panelFormulario, g, 4, "Vehículo:", cbVehiculo);

        // ── BOTONES ────────────────────────────────────────

        JPanel panelBotones = createButtonRow(
            createBtn("Agregar", C_EXITO, e -> agregarMantenimiento()),
            createBtn("Actualizar", C_PRIMARIO, e -> actualizarMantenimiento()),
            createBtn("Eliminar", C_PELIGRO, e -> eliminarMantenimiento()),
            createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
        );

        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 2;

        panelFormulario.add(panelBotones, g);

        // ── PANEL BUSCADOR ────────────────────────────────────────

        JPanel panelBuscador = createPanel("Buscar mantenimiento");
        panelBuscador.setLayout(new GridBagLayout());
        GridBagConstraints g4 = createGbc();

        createFormRow(panelBuscador, g4, 0, "Buscar ID:", txtBuscar);
        JPanel rowBuscar = createButtonRow(createBtn("Buscar", C_PRIMARIO, e -> buscarMantenimiento()));

        g4.gridx = 0;
        g4.gridy = 1;
        g4.gridwidth = 2;

        panelBuscador.add(rowBuscar, g4);

        leftPanel.add(panelFormulario);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(panelBuscador);

        // ── TABLA ──────────────────────────────────────────

        String[] columnas = {
            "\u00cdndice",
            "ID Mantenimiento",
            "Fecha",
            "Descripción",
            "Costo",
            "Vehículo"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaMantenimientos = new JTable(modeloTabla);

        // ── PANEL TABLA ────────────────────────────────

        JPanel panelTabla = createPanel("Mantenimientos");
        panelTabla.setLayout(new BorderLayout(0, 6));
        panelTabla.add(new JScrollPane(tablaMantenimientos), BorderLayout.CENTER);

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, panelTabla);
        split.setDividerLocation(350);
        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        refrescarTabla = () -> {
            modeloTabla.setRowCount(0);

            for (int i = 0; i < mantenimientoManager.obtenerMantenimientos().size(); i++) {
                MantenimientoVO m = mantenimientoManager.obtenerMantenimientos().get(i);

                modeloTabla.addRow(new Object[]{
                    i,
                    m.getIdMantenimiento(),
                    SDF.format(Date.from(m.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant())),
                    m.getDescripcion(),
                    m.getCosto(),
                    m.getVehiculo() != null ? m.getVehiculo().getPlaca() : "N/A"
                });
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }

    /**
     * Inicializa el listener de selección de la tabla para cargar los datos del mantenimiento
     * seleccionado en el formulario.
     */
    private void initEventosSeleccion() {
        tablaMantenimientos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaMantenimientos.getSelectedRow();
            if (fila == -1) return;

            MantenimientoVO m = mantenimientoManager.obtenerMantenimientos().get(fila);
            txtId.setText(m.getIdMantenimiento());
            spFecha.setValue(Date.from(m.getFecha().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            txtDescripcion.setText(m.getDescripcion());
            txtCosto.setText(String.valueOf(m.getCosto()));

            if (m.getVehiculo() != null) {
                cbVehiculo.setSelectedItem(m.getVehiculo().getPlaca());
            }
        });
    }

    /**
     * Obtiene los datos del formulario y construye un objeto MantenimientoVO.
     *
     * @return MantenimientoVO con los datos ingresados
     */
    private MantenimientoVO obtenerMantenimientoFormulario() {
        Date date = (Date) spFecha.getValue();
        LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String placaSeleccionada = (String) cbVehiculo.getSelectedItem();
        VehiculoVO vehiculo = vehiculoManager.buscarVehiculoPorPlaca(placaSeleccionada);

        return new MantenimientoVO(
            txtId.getText().trim(),
            fecha,
            txtDescripcion.getText().trim(),
            Double.parseDouble(txtCosto.getText().trim()),
            vehiculo
        );
    }

    /**
     * Valida los campos del formulario antes de enviarlos al gestor.
     *
     * @return true si todos los campos son validos, false en caso contrario
     */
    private boolean validarFormulario() {
        String id = txtId.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String costoStr = txtCosto.getText().trim();

        if (id.isEmpty()) {
            showErrorMessage(this, "El ID del mantenimiento no puede estar vacío.");
            return false;
        }

        if (descripcion.isEmpty()) {
            showErrorMessage(this, "La descripción no puede estar vacía.");
            return false;
        }

        if (costoStr.isEmpty()) {
            showErrorMessage(this, "El costo no puede estar vacío.");
            return false;
        }

        double costo;
        try {
            costo = Double.parseDouble(costoStr);
        } catch (NumberFormatException e) {
            showErrorMessage(this, "Debe ingresar un número válido en costo.");
            return false;
        }

        if (costo <= 0) {
            showErrorMessage(this, "El costo debe ser mayor a cero.");
            return false;
        }

        if (cbVehiculo.getSelectedItem() == null) {
            showErrorMessage(this, "Debe seleccionar un vehículo.");
            return false;
        }

        return true;
    }

    /**
     * Agrega un nuevo mantenimiento al sistema con los datos del formulario.
     * Muestra un mensaje de éxito o error según el resultado.
     */
    private void agregarMantenimiento() {
        if (!validarFormulario()) return;

        try {
            MantenimientoVO m = obtenerMantenimientoFormulario();
            mantenimientoManager.agregarMantenimiento(m);
            refrescarTabla.run();

            showInfoMessage(this, "Mantenimiento agregado correctamente.");

        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * Actualiza el mantenimiento seleccionado en la tabla con los datos del formulario.
     * Valida que haya una fila seleccionada antes de actualizar.
     */
    private void actualizarMantenimiento() {
        int fila = tablaMantenimientos.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione un mantenimiento.");
            return;
        }

        if (!validarFormulario()) return;

        try {
            MantenimientoVO m = obtenerMantenimientoFormulario();
            mantenimientoManager.actualizarMantenimiento(fila, m);
            refrescarTabla.run();

            showInfoMessage(this, "Mantenimiento actualizado correctamente.");

        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * Elimina el mantenimiento seleccionado en la tabla, previa confirmación del usuario.
     */
    private void eliminarMantenimiento() {
        int fila = tablaMantenimientos.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione un mantenimiento.");
            return;
        }

        if (!confirmMessage(this, "¿Está seguro de eliminar el mantenimiento seleccionado?")) return;

        mantenimientoManager.eliminarMantenimiento(fila);
        refrescarTabla.run();

        showInfoMessage(this, "Mantenimiento eliminado correctamente.");
    }

    /**
     * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {
        txtId.setText("");
        spFecha.setValue(new Date());
        txtDescripcion.setText("");
        txtCosto.setText("");
        cbVehiculo.setSelectedIndex(-1);
        tablaMantenimientos.clearSelection();
    }

    /**
     * Busca un mantenimiento por ID y lo resalta en la tabla, en caso contrario
     * muestra un mensaje informativo.
     */
    private void buscarMantenimiento() {
        try {
            MantenimientoVO m = mantenimientoManager.buscarMantenimientoPorId(txtBuscar.getText().trim());
            if (m != null) {
                highlightRow(tablaMantenimientos, modeloTabla, txtBuscar.getText().trim(), 1);
            } else {
                showInfoMessage(this, "No se encontró el mantenimiento \"" + txtBuscar.getText() + "\".");
            }
        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }
}
