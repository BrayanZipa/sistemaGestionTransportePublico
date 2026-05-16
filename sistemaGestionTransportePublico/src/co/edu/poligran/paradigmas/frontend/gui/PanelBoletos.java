package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionTarifasManager;
import co.edu.poligran.paradigmas.backend.vo.BoletoVO;
import co.edu.poligran.paradigmas.backend.vo.PagoVO;
import co.edu.poligran.paradigmas.backend.vo.PasajeroVO;
import co.edu.poligran.paradigmas.backend.vo.RutaVO;
import co.edu.poligran.paradigmas.backend.vo.TarifasVO;
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

public class PanelBoletos extends JPanel {

    private GestionBoletosManager boletoManager;
    private GestionPasajerosManager pasajeroManager;
    private GestionRutasManager rutaManager;
    private GestionPagosManager pagosManager;
    private GestionTarifasManager tarifasManager;

    private JTextField txtCodigo;
    private JSpinner spFechaCompra;
    private JTextField txtNumeroAsiento;
    private JComboBox<String> cbPasajero;
    private JComboBox<String> cbRuta;
    private JComboBox<String> cbPago;
    private JComboBox<String> cbTarifa;
    private JTextField txtBuscar;

    private JTable tablaBoletos;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;

    private PasajeroVO[] pasajeros;
    private RutaVO[] rutas;
    private PagoVO[] pagos;
    private TarifasVO[] tarifas;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructor de la clase PanelBoletos.
     *
     * @param boletoManager   gestor encargado de las operaciones relacionadas con boletos
     * @param pasajeroManager gestor encargado de las operaciones relacionadas con pasajeros
     * @param rutaManager     gestor encargado de las operaciones relacionadas con rutas
     * @param pagosManager    gestor encargado de las operaciones relacionadas con pagos
     * @param tarifasManager  gestor encargado de las operaciones relacionadas con tarifas
     */
    public PanelBoletos(GestionBoletosManager boletoManager, GestionPasajerosManager pasajeroManager,
                        GestionRutasManager rutaManager, GestionPagosManager pagosManager,
                        GestionTarifasManager tarifasManager) {
        this.boletoManager = boletoManager;
        this.pasajeroManager = pasajeroManager;
        this.rutaManager = rutaManager;
        this.pagosManager = pagosManager;
        this.tarifasManager = tarifasManager;

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

        JPanel panelFormulario = createPanel("Gestión de Boletos");
        panelFormulario.setLayout(new GridBagLayout());

        // ── FORMULARIO ─────────────────────────────────────

        GridBagConstraints g = createGbc();

        txtCodigo = createTextField(15);
        spFechaCompra = createDateField(0);
        txtNumeroAsiento = createTextField(15);
        txtBuscar = createTextField(15);

        // ── CARGAR COMBOBOXES ─────────────────────────────────

        cargarCombos();

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────────

        createFormRow(panelFormulario, g, 0, "Código:", txtCodigo);
        createFormRow(panelFormulario, g, 1, "Fecha Compra:", spFechaCompra);
        createFormRow(panelFormulario, g, 2, "Número Asiento:", txtNumeroAsiento);
        createFormRow(panelFormulario, g, 3, "Pasajero:", cbPasajero);
        createFormRow(panelFormulario, g, 4, "Ruta:", cbRuta);
        createFormRow(panelFormulario, g, 5, "Pago:", cbPago);
        createFormRow(panelFormulario, g, 6, "Tarifa:", cbTarifa);

        // ── BOTONES ────────────────────────────────────────

        JPanel panelBotones = createButtonRow(
            createBtn("Agregar", C_EXITO, e -> agregarBoleto()),
            createBtn("Actualizar", C_PRIMARIO, e -> actualizarBoleto()),
            createBtn("Eliminar", C_PELIGRO, e -> eliminarBoleto()),
            createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
        );

        g.gridx = 0;
        g.gridy = 7;
        g.gridwidth = 2;

        panelFormulario.add(panelBotones, g);

        // ── PANEL BUSCADOR ────────────────────────────────────────

        JPanel panelBuscador = createPanel("Buscar boleto");
        panelBuscador.setLayout(new GridBagLayout());
        GridBagConstraints g4 = createGbc();

        createFormRow(panelBuscador, g4, 0, "Buscar código:", txtBuscar);
        JPanel rowBuscar = createButtonRow(createBtn("Buscar", C_PRIMARIO, e -> buscarBoleto()));

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
            "Fecha Compra",
            "Asiento",
            "Pasajero",
            "Ruta",
            "Pago",
            "Tarifa"
        };

        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaBoletos = new JTable(modeloTabla);

        // ── PANEL TABLA ────────────────────────────────

        JPanel panelTabla = createPanel("Boletos");
        panelTabla.setLayout(new BorderLayout(0, 6));
        panelTabla.add(new JScrollPane(tablaBoletos), BorderLayout.CENTER);

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, panelTabla);
        split.setDividerLocation(400);
        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        refrescarTabla = () -> {
            modeloTabla.setRowCount(0);

            for (int i = 0; i < boletoManager.obtenerBoletos().size(); i++) {
                BoletoVO b = boletoManager.obtenerBoletos().get(i);

                modeloTabla.addRow(new Object[]{
                    i,
                    b.getCodigo(),
                    b.getFechaCompra().format(FMT),
                    b.getNumeroAsiento(),
                    b.getPasajero() != null
                        ? b.getPasajero().getIdentificacion() + " - " + b.getPasajero().getNombre()
                        : "N/A",
                    b.getRuta() != null ? String.valueOf(b.getRuta().getCodigo()) : "N/A",
                    b.getPago() != null ? b.getPago().getIdPago() : "N/A",
                    b.getTarifa() != null
                        ? b.getTarifa().getTipoTarifa() + " ($" + b.getTarifa().getValor() + ")"
                        : "N/A"
                });
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }

    /**
     * Carga los datos de los comboboxes de pasajeros, rutas, pagos y tarifas desde los gestores correspondientes.
     */
    private void cargarCombos() {
        // ── PASAJEROS ─────────────────────────────────────

        List<PasajeroVO> listaPasajeros = pasajeroManager.obtenerPasajeros();
        pasajeros = listaPasajeros.toArray(new PasajeroVO[0]);
        String[] itemsPasajero = new String[pasajeros.length];
        for (int i = 0; i < pasajeros.length; i++) {
            itemsPasajero[i] = pasajeros[i].getIdentificacion() + " - " + pasajeros[i].getNombre();
        }
        cbPasajero = createComboBox(itemsPasajero);

        // ── RUTAS ─────────────────────────────────────────

        List<RutaVO> listaRutas = rutaManager.obtenerRutas();
        rutas = listaRutas.toArray(new RutaVO[0]);
        String[] itemsRuta = new String[rutas.length];
        for (int i = 0; i < rutas.length; i++) {
            itemsRuta[i] = rutas[i].getCodigo() + " - "
                         + (rutas[i].getOrigen() != null ? rutas[i].getOrigen().getNombre() : "?")
                         + " → "
                         + (rutas[i].getDestino() != null ? rutas[i].getDestino().getNombre() : "?");
        }
        cbRuta = createComboBox(itemsRuta);

        // ── PAGOS ─────────────────────────────────────────

        List<PagoVO> listaPagos = pagosManager.obtenerPagos();
        pagos = listaPagos.toArray(new PagoVO[0]);
        String[] itemsPago = new String[pagos.length];
        for (int i = 0; i < pagos.length; i++) {
            itemsPago[i] = pagos[i].getIdPago() + " - $" + pagos[i].getMonto();
        }
        cbPago = createComboBox(itemsPago);

        // ── TARIFAS ───────────────────────────────────────

        List<TarifasVO> listaTarifas = tarifasManager.obtenerTarifas();
        tarifas = listaTarifas.toArray(new TarifasVO[0]);
        String[] itemsTarifa = new String[tarifas.length];
        for (int i = 0; i < tarifas.length; i++) {
            itemsTarifa[i] = tarifas[i].getTipoTarifa() + " ($" + tarifas[i].getValor() + ")";
        }
        cbTarifa = createComboBox(itemsTarifa);
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
     * Inicializa el listener de selección de la tabla para cargar los datos del boleto
     * seleccionado en el formulario.
     */
    private void initEventosSeleccion() {
        tablaBoletos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaBoletos.getSelectedRow();
            if (fila == -1) return;

            BoletoVO b = boletoManager.obtenerBoletos().get(fila);
            txtCodigo.setText(String.valueOf(b.getCodigo()));
            spFechaCompra.setValue(
                Date.from(b.getFechaCompra().atZone(ZoneId.systemDefault()).toInstant())
            );
            txtNumeroAsiento.setText(b.getNumeroAsiento());

            if (b.getPasajero() != null) {
                seleccionarEnCombo(cbPasajero, b.getPasajero().getIdentificacion());
            }
            if (b.getRuta() != null) {
                seleccionarEnCombo(cbRuta, String.valueOf(b.getRuta().getCodigo()));
            }
            if (b.getPago() != null) {
                seleccionarEnCombo(cbPago, b.getPago().getIdPago());
            }
            if (b.getTarifa() != null) {
                seleccionarEnCombo(cbTarifa, b.getTarifa().getTipoTarifa());
            }
        });
    }

    /**
     * Obtiene los datos del formulario y construye un objeto BoletoVO.
     *
     * @return BoletoVO con los datos ingresados
     */
    private BoletoVO obtenerBoletoFormulario() {
        Date date = (Date) spFechaCompra.getValue();
        LocalDateTime fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        int idxPasajero = cbPasajero.getSelectedIndex();
        PasajeroVO pasajero = (idxPasajero >= 0) ? pasajeros[idxPasajero] : null;

        int idxRuta = cbRuta.getSelectedIndex();
        RutaVO ruta = (idxRuta >= 0) ? rutas[idxRuta] : null;

        int idxPago = cbPago.getSelectedIndex();
        PagoVO pago = (idxPago >= 0) ? pagos[idxPago] : null;

        int idxTarifa = cbTarifa.getSelectedIndex();
        TarifasVO tarifa = (idxTarifa >= 0) ? tarifas[idxTarifa] : null;

        return new BoletoVO(
            Integer.parseInt(txtCodigo.getText().trim()),
            fecha,
            txtNumeroAsiento.getText().trim(),
            pasajero,
            ruta,
            pago,
            tarifa
        );
    }

    /**
     * Valida los campos del formulario antes de enviarlos al gestor.
     *
     * @return true si todos los campos son válidos, false en caso contrario
     */
    private boolean validarFormulario() {
        String codigoStr = txtCodigo.getText().trim();
        String asiento = txtNumeroAsiento.getText().trim();

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

        if (asiento.isEmpty()) {
            showErrorMessage(this, "El número de asiento no puede estar vacío.");
            return false;
        }

        if (cbPasajero.getSelectedItem() == null) {
            showErrorMessage(this, "Debe seleccionar un pasajero.");
            return false;
        }

        if (cbRuta.getSelectedItem() == null) {
            showErrorMessage(this, "Debe seleccionar una ruta.");
            return false;
        }

        if (cbPago.getSelectedItem() == null) {
            showErrorMessage(this, "Debe seleccionar un pago.");
            return false;
        }

        if (cbTarifa.getSelectedItem() == null) {
            showErrorMessage(this, "Debe seleccionar una tarifa.");
            return false;
        }

        return true;
    }

    /**
     * Agrega un nuevo boleto al sistema con los datos del formulario.
     * Muestra un mensaje de éxito o error según el resultado.
     */
    private void agregarBoleto() {
        if (!validarFormulario()) return;

        try {
            BoletoVO b = obtenerBoletoFormulario();
            boletoManager.agregarBoleto(b);
            refrescarTabla.run();

            showInfoMessage(this, "Boleto agregado correctamente.");

        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * Actualiza el boleto seleccionado en la tabla con los datos del formulario.
     * Valida que haya una fila seleccionada antes de actualizar.
     */
    private void actualizarBoleto() {
        int fila = tablaBoletos.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione un boleto.");
            return;
        }

        if (!validarFormulario()) return;

        try {
            BoletoVO b = obtenerBoletoFormulario();
            boletoManager.actualizarBoleto(fila, b);
            refrescarTabla.run();

            showInfoMessage(this, "Boleto actualizado correctamente.");

        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }

    /**
     * Elimina el boleto seleccionado en la tabla, previa confirmación del usuario.
     */
    private void eliminarBoleto() {
        int fila = tablaBoletos.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione un boleto.");
            return;
        }

        if (!confirmMessage(this, "¿Está seguro de eliminar el boleto seleccionado?")) return;

        boletoManager.eliminarBoleto(fila);
        refrescarTabla.run();

        showInfoMessage(this, "Boleto eliminado correctamente.");
    }

    /**
     * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {
        txtCodigo.setText("");
        spFechaCompra.setValue(new Date());
        txtNumeroAsiento.setText("");
        cbPasajero.setSelectedIndex(-1);
        cbRuta.setSelectedIndex(-1);
        cbPago.setSelectedIndex(-1);
        cbTarifa.setSelectedIndex(-1);
        tablaBoletos.clearSelection();
    }

    /**
     * Busca un boleto por código y lo resalta en la tabla, en caso contrario
     * muestra un mensaje informativo.
     */
    private void buscarBoleto() {
        String texto = txtBuscar.getText().trim();

        if (texto.isEmpty()) {
            showErrorMessage(this, "Ingrese un código para buscar.");
            return;
        }

        try {
            int codigo = Integer.parseInt(texto);
            BoletoVO b = boletoManager.buscarBoletoPorCodigo(codigo);

            if (b != null) {
                highlightRow(tablaBoletos, modeloTabla, texto, 1);
            } else {
                showInfoMessage(this, "No se encontró el boleto \"" + texto + "\".");
            }

        } catch (NumberFormatException ex) {
            showErrorMessage(this, "Debe ingresar un número válido.");
        } catch (Exception ex) {
            showErrorMessage(this, ex.getMessage());
        }
    }
}
