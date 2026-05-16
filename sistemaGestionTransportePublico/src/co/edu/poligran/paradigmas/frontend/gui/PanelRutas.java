package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;
import co.edu.poligran.paradigmas.backend.vo.ParadaVO;
import co.edu.poligran.paradigmas.backend.vo.RutaVO;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

public class PanelRutas extends JPanel {

    private GestionRutasManager rutaManager;
    private GestionParadasManager paradaManager;
    private GestionBoletosManager boletoManager;
    private GestionVehiculosManager vehiculoManager;
    private GestionConductoresManager conductorManager;

    private JTextField txtCodigo;
    private JTextField txtDistancia;
    private JSpinner spFecha;
    private JComboBox<String> cbOrigen;
    private JComboBox<String> cbDestino;
    private JComboBox<String> cbVehiculo;
    private JComboBox<String> cbConductor;
    private JTextField txtBuscar;

    private JTable tablaRutas;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;

    private ParadaVO[] paradas;
    private VehiculoVO[] vehiculos;
    private ConductorVO[] conductores;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructor de la clase PanelRutas.
     * 
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     * @param paradaManager gestor encargado de las operaciones relacionadas con paradas
     * @param boletoManager gestor encargado de las operaciones relacionadas con boletos
     * @param vehiculoManager gestor encargado de las operaciones relacionadas con vehículos
     * @param conductorManager gestor encargado de las operaciones relacionadas con conductores
     */
    public PanelRutas(GestionRutasManager rutaManager, GestionParadasManager paradaManager,
                      GestionBoletosManager boletoManager, GestionVehiculosManager vehiculoManager,
                      GestionConductoresManager conductorManager) {
        this.rutaManager = rutaManager;
        this.paradaManager = paradaManager;
        this.boletoManager = boletoManager;
        this.vehiculoManager = vehiculoManager;
        this.conductorManager = conductorManager;

        configurarPanel();
    }

    /**
     * Configura la interfaz gráfica del panel, incluyendo formulario,
     * buscador, tabla y botones.
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

        JPanel panelFormulario = createPanel("Gestión de Rutas");
        panelFormulario.setLayout(new GridBagLayout());

        // ── FORMULARIO ─────────────────────────────────────

        GridBagConstraints g = createGbc();

        txtCodigo = createTextField(15);
        txtDistancia = createTextField(15);
        spFecha = createDateField(0);
        txtBuscar = createTextField(15);

        cargarCombos();

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────────

        createFormRow(panelFormulario, g, 0, "Código:", txtCodigo);
        createFormRow(panelFormulario, g, 1, "Origen:", cbOrigen);
        createFormRow(panelFormulario, g, 2, "Destino:", cbDestino);
        createFormRow(panelFormulario, g, 3, "Distancia (km):", txtDistancia);
        createFormRow(panelFormulario, g, 4, "Fecha:", spFecha);
        createFormRow(panelFormulario, g, 5, "Vehículo:", cbVehiculo);
        createFormRow(panelFormulario, g, 6, "Conductor:", cbConductor);

        // ── BOTONES ────────────────────────────────────────

        JPanel panelBotones = createButtonRow(
            createBtn("Agregar", C_EXITO, e -> agregarRuta()),
            createBtn("Actualizar", C_PRIMARIO, e -> actualizarRuta()),
            createBtn("Eliminar", C_PELIGRO, e -> eliminarRuta()),
            createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
        );

        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 2;

        panelFormulario.add(panelBotones, g);

        // ── PANEL BUSCADOR ────────────────────────────────────────

        JPanel panelBuscador = createPanel("Buscar ruta");
        panelBuscador.setLayout(new GridBagLayout());
        GridBagConstraints g4 = createGbc();

        createFormRow(panelBuscador, g4, 0, "Buscar código:", txtBuscar);
        JPanel rowBuscar = createButtonRow(createBtn("Buscar", C_PRIMARIO, e -> buscarRuta()));

        g4.gridx = 0;
        g4.gridy = 1;
        g4.gridwidth = 2;

        panelBuscador.add(rowBuscar, g4);

        leftPanel.add(panelFormulario);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(panelBuscador);

        // ── TABLA ──────────────────────────────────────────

        String[] columnas = {
            "Índice",
            "Código",
            "Origen",
            "Destino",
            "Distancia",
            "Fecha",
            "Vehículo",
            "Conductor",
            "Paradas",
            "Boletos"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaRutas = new JTable(modeloTabla);

        // ── PANEL TABLA ────────────────────────────────

        JPanel panelTabla = createPanel("Rutas");
        panelTabla.setLayout(new BorderLayout(0, 6));
        panelTabla.add(new JScrollPane(tablaRutas), BorderLayout.CENTER);

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, panelTabla);
        split.setDividerLocation(400);
        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        refrescarTabla = () -> {
            modeloTabla.setRowCount(0);

            for (int i = 0; i < rutaManager.obtenerRutas().size(); i++) {
                RutaVO r = rutaManager.obtenerRutas().get(i);

                modeloTabla.addRow(new Object[]{
                    i,
                    r.getCodigo(),
                    r.getOrigen() != null ? r.getOrigen().getNombre() : "N/A",
                    r.getDestino() != null ? r.getDestino().getNombre() : "N/A",
                    r.getDistancia(),
                    r.getFecha() != null ? r.getFecha().format(FMT) : "N/A",
                    r.getVehiculo() != null
                        ? r.getVehiculo().getPlaca() + " - " + r.getVehiculo().getModelo()
                        : "N/A",
                    r.getConductor() != null
                        ? r.getConductor().getNombre() + " (" + r.getConductor().getLicencia() + ")"
                        : "N/A",
                    r.getParadas().size(),
                    r.getBoletos().size()
                });
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }

    /**
     * Carga los datos de los comboboxes de paradas, vehículos y conductores
     * desde los gestores correspondientes.
     */
    private void cargarCombos() {
        // ── PARADAS (ORIGEN) ─────────────────────────────────

        List<ParadaVO> listaParadas = paradaManager.obtenerParadas();
        paradas = listaParadas.toArray(new ParadaVO[0]);
        String[] itemsParada = new String[paradas.length];
        for (int i = 0; i < paradas.length; i++) {
            itemsParada[i] = paradas[i].getCodigo() + " - " + paradas[i].getNombre();
        }
        cbOrigen = createComboBox(itemsParada);

        // ── PARADAS (DESTINO) ───────────────────────────────

        cbDestino = createComboBox(itemsParada);

        // ── VEHÍCULOS (solo disponibles) ────────────────────

        List<VehiculoVO> listaVehiculos = vehiculoManager.obtenerVehiculos();
        vehiculos = listaVehiculos.stream()
            .filter(VehiculoVO::isEstadoDisponibilidad)
            .toArray(VehiculoVO[]::new);
        String[] itemsVehiculo = new String[vehiculos.length];
        for (int i = 0; i < vehiculos.length; i++) {
            itemsVehiculo[i] = vehiculos[i].getPlaca() + " - " + vehiculos[i].getModelo();
        }
        cbVehiculo = createComboBox(itemsVehiculo);

        // ── CONDUCTORES ─────────────────────────────────────

        List<ConductorVO> listaConductores = conductorManager.obtenerConductores();
        conductores = listaConductores.toArray(new ConductorVO[0]);
        String[] itemsConductor = new String[conductores.length];
        for (int i = 0; i < conductores.length; i++) {
            itemsConductor[i] = conductores[i].getIdentificacion() + " - " + conductores[i].getNombre();
        }
        cbConductor = createComboBox(itemsConductor);
    }
    
    /**
     * Busca y selecciona un item en un JComboBox cuyo texto comience con el prefijo dado.
     * 
     * @param combo   combobox donde buscar
     * @param prefijo texto inicial del item a seleccionar
     */
    private void seleccionarEnCombo(JComboBox<String> combo, String prefijo) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).startsWith(prefijo)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    /**
     * Inicializa el listener de selección de la tabla para cargar
     * los datos de la ruta seleccionada en el formulario.
     */
    private void initEventosSeleccion() {
        tablaRutas.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaRutas.getSelectedRow();
            if (fila == -1) return;

            RutaVO r = rutaManager.obtenerRutas().get(fila);
            txtCodigo.setText(String.valueOf(r.getCodigo()));
            txtDistancia.setText(String.valueOf(r.getDistancia()));
            spFecha.setValue(
                Date.from(r.getFecha().atZone(ZoneId.systemDefault()).toInstant())
            );

            if (r.getOrigen() != null) {
                seleccionarEnCombo(cbOrigen, r.getOrigen().getCodigo());
            }
            if (r.getDestino() != null) {
                seleccionarEnCombo(cbDestino, r.getDestino().getCodigo());
            }
            if (r.getVehiculo() != null) {
                seleccionarEnCombo(cbVehiculo, r.getVehiculo().getPlaca());
            }
            if (r.getConductor() != null) {
                seleccionarEnCombo(cbConductor, r.getConductor().getIdentificacion());
            }
        });
    }

    /**
     * Obtiene los datos del formulario y construye un objeto RutaVO.
     * 
     * @return RutaVO con los datos ingresados
     */
    private RutaVO obtenerRutaFormulario() {
        Date date = (Date) spFecha.getValue();
        LocalDateTime fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        int idxOrigen = cbOrigen.getSelectedIndex();
        ParadaVO origen = (idxOrigen >= 0) ? paradas[idxOrigen] : null;

        int idxDestino = cbDestino.getSelectedIndex();
        ParadaVO destino = (idxDestino >= 0) ? paradas[idxDestino] : null;

        int idxVehiculo = cbVehiculo.getSelectedIndex();
        VehiculoVO vehiculo = (idxVehiculo >= 0) ? vehiculos[idxVehiculo] : null;

        int idxConductor = cbConductor.getSelectedIndex();
        ConductorVO conductor = (idxConductor >= 0) ? conductores[idxConductor] : null;

        return new RutaVO(
            Integer.parseInt(txtCodigo.getText().trim()),
            origen,
            destino,
            Float.parseFloat(txtDistancia.getText().trim()),
            fecha,
            vehiculo,
            conductor
        );
    }

    /**
     * Valida los campos del formulario antes de enviarlos al gestor.
     * 
     * @return true si todos los campos son válidos, false en caso contrario
     */
    private boolean validarFormulario() {
        String codigoStr = txtCodigo.getText().trim();
        String distanciaStr = txtDistancia.getText().trim();

        if (codigoStr.isEmpty()) {
            showErrorMessage(this, "El código no puede estar vacío.");
            return false;
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            showErrorMessage(this, "Debe ingresar un número válido en código.");
            return false;
        }

        if (codigo <= 0) {
            showErrorMessage(this, "El código debe ser mayor a cero.");
            return false;
        }

        if (distanciaStr.isEmpty()) {
            showErrorMessage(this, "La distancia no puede estar vacía.");
            return false;
        }

        float distancia;
        try {
            distancia = Float.parseFloat(distanciaStr);
        } catch (NumberFormatException e) {
            showErrorMessage(this, "Debe ingresar un número válido en distancia.");
            return false;
        }

        if (distancia <= 0) {
            showErrorMessage(this, "La distancia debe ser mayor a cero.");
            return false;
        }

        if (cbOrigen.getSelectedIndex() < 0) {
            showErrorMessage(this, "Debe seleccionar un origen.");
            return false;
        }

        if (cbDestino.getSelectedIndex() < 0) {
            showErrorMessage(this, "Debe seleccionar un destino.");
            return false;
        }

        if (cbOrigen.getSelectedIndex() == cbDestino.getSelectedIndex()) {
            showErrorMessage(this, "El origen y destino no pueden ser iguales.");
            return false;
        }

        if (cbVehiculo.getSelectedIndex() < 0) {
            showErrorMessage(this, "Debe seleccionar un vehículo.");
            return false;
        }

        if (cbConductor.getSelectedIndex() < 0) {
            showErrorMessage(this, "Debe seleccionar un conductor.");
            return false;
        }

        return true;
    }

    /**
     * Agrega una nueva ruta al sistema con los datos del formulario.
     * Muestra un mensaje de éxito o error según el resultado.
     */
    private void agregarRuta() {
        if (!validarFormulario()) return;

        try {
            RutaVO r = obtenerRutaFormulario();
            rutaManager.agregarRuta(r);
            refrescarTabla.run();

            showInfoMessage(this, "Ruta agregada correctamente.");

        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * Actualiza la ruta seleccionada en la tabla con los datos del formulario.
     * Valida que haya una fila seleccionada antes de actualizar.
     */
    private void actualizarRuta() {
        int fila = tablaRutas.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione una ruta.");
            return;
        }

        if (!validarFormulario()) return;

        try {
            RutaVO r = obtenerRutaFormulario();
            rutaManager.actualizarRuta(fila, r);
            refrescarTabla.run();

            showInfoMessage(this, "Ruta actualizada correctamente.");

        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * Elimina la ruta seleccionada en la tabla, previa confirmación del usuario.
     */
    private void eliminarRuta() {
        int fila = tablaRutas.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione una ruta.");
            return;
        }

        if (!confirmMessage(this, "¿Está seguro de eliminar la ruta seleccionada?")) return;

        rutaManager.eliminarRuta(fila);
        refrescarTabla.run();

        showInfoMessage(this, "Ruta eliminada correctamente.");
    }

    /**
     * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtDistancia.setText("");
        spFecha.setValue(new Date());
        cbOrigen.setSelectedIndex(-1);
        cbDestino.setSelectedIndex(-1);
        cbVehiculo.setSelectedIndex(-1);
        cbConductor.setSelectedIndex(-1);
        tablaRutas.clearSelection();
    }

    /**
     * Busca una ruta por código y la resalta en la tabla, en caso contrario muestra un mensaje informativo.
     */
    private void buscarRuta() {
        String texto = txtBuscar.getText().trim();

        if (texto.isEmpty()) {
            showErrorMessage(this, "Ingrese un código para buscar.");
            return;
        }

        try {
            int codigo = Integer.parseInt(texto);
            RutaVO r = rutaManager.buscarRutaPorCodigo(codigo);

            if (r != null) {
                highlightRow(tablaRutas, modeloTabla, texto, 1);
            } else {
                showInfoMessage(this, "No se encontró la ruta \"" + texto + "\".");
            }

        } catch (NumberFormatException ex) {
            showErrorMessage(this, "Debe ingresar un número válido.");
        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }
}
