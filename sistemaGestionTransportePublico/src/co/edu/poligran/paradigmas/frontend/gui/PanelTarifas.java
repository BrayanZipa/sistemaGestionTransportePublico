package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionTarifasManager;
import co.edu.poligran.paradigmas.backend.vo.TarifasVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDateTime;

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

public class PanelTarifas extends JPanel {
	
    private GestionTarifasManager tarifasManager;
    
    private JTextField txtCodigo;
    private JTextField txtValor;
    private JTextField txtBuscar;
    private JComboBox<String> cbxTipoTarifa;

    private JTable tablaTarifas;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;
	
    /**
     * Constructor de la clase PanelTarifas.
     * 
     * @param tarifasManager gestor encargado de las operaciones relacionadas con tarifas
     */
    public PanelTarifas(GestionTarifasManager tarifasManager) {
        this.tarifasManager = tarifasManager;

        configurarPanel();
    }
    
    /**
     * Configura la interfaz gráfica del panel, incluyendo formulario, tabla, botones.
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

        JPanel panelFormulario = createPanel("Gestión de Tarifas");
        panelFormulario.setLayout(new GridBagLayout());
        
        // ── FORMULARIO ─────────────────────────────────────
        
        GridBagConstraints g = createGbc();
        
        txtCodigo = createTextField(15);
        txtValor = createTextField(15);
        cbxTipoTarifa = createComboBox(new String[]{"General", "Especial", "Estudiante", "Adulto Mayor","Ejecutivo", "Nocturno","Turistico"});
        txtBuscar = createTextField(15);

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────────
        
        createFormRow(panelFormulario, g, 0, "Código:", txtCodigo);
        createFormRow(panelFormulario, g, 1, "Valor:", txtValor);
        createFormRow(panelFormulario, g, 2, "Tipo tarifa:", cbxTipoTarifa);

        // ── BOTONES ────────────────────────────────────────
        
        JPanel panelBotones = createButtonRow(
        	createBtn("Agregar", C_EXITO, e -> agregarTarifa()),
        	createBtn("Actualizar", C_PRIMARIO, e -> actualizarTarifa()),
        	createBtn("Eliminar", C_PELIGRO, e -> eliminarTarifa()),
        	createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
        );

        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;

        panelFormulario.add(panelBotones, g);
        
        // ── PANEL BUSCADOR ────────────────────────────────────────
        
        JPanel panelBuscador = createPanel("Buscar tarifa");
        panelBuscador.setLayout(new GridBagLayout());
        GridBagConstraints g4 = createGbc();
        
        createFormRow(panelBuscador, g4, 0, "Buscar código:", txtBuscar);
        JPanel rowBuscar = createButtonRow(createBtn("Buscar", C_PRIMARIO, e -> buscarTarifa()));
        
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
            "Fecha",
            "Valor",
            "Tipo Tarifa"
        };
        
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTarifas = new JTable(modeloTabla);
        
        // ── PANEL TABLA ────────────────────────────────
        
        JPanel panelTabla = createPanel("Tarifas");
        panelTabla.setLayout(new BorderLayout(0, 6));
        panelTabla.add(new JScrollPane(tablaTarifas), BorderLayout.CENTER);

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, panelTabla);
        split.setDividerLocation(350);
        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        refrescarTabla = () -> {
            modeloTabla.setRowCount(0);

            for (int i = 0; i < tarifasManager.obtenerTarifas().size(); i++) {
                TarifasVO t = tarifasManager.obtenerTarifas().get(i);

                modeloTabla.addRow(new Object[] {
                    i,
                    t.getCodigo(),
                    t.getFecha().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    t.getValor(),
                    t.getTipoTarifa()
                });
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }
    
    /**
     * Inicializa el listener de selección de la tabla para cargar los datos de la tarifa seleccionada en el formulario.
     */
    private void initEventosSeleccion() {
        tablaTarifas.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaTarifas.getSelectedRow();
            if (fila == -1) return;

            TarifasVO t = tarifasManager.obtenerTarifas().get(fila);
            txtCodigo.setText(String.valueOf(t.getCodigo()));
            txtValor.setText(String.valueOf(t.getValor()));
            cbxTipoTarifa.setSelectedItem(t.getTipoTarifa());
        });
    }

    /**
     * Obtiene los datos del formulario y construye un objeto TarifasVO.
     * 
     * @return TarifasVO con los datos ingresados
     */
    private TarifasVO obtenerTarifaFormulario() {
        return new TarifasVO(
            Integer.parseInt(txtCodigo.getText().trim()),
            LocalDateTime.now(),
            Double.parseDouble(txtValor.getText().trim()),
            cbxTipoTarifa.getSelectedItem().toString()
        );
    }

    /**
     * Valida los campos del formulario antes de enviarlos al gestor.
     * 
     * @return true si todos los campos son válidos, false en caso contrario
     */
    private boolean validarFormulario() {
        String codigoStr = txtCodigo.getText().trim();
        String valorStr = txtValor.getText().trim();
        String tipo = cbxTipoTarifa.getSelectedItem().toString();

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

        if (valorStr.isEmpty()) {
            showErrorMessage(this, "El valor no puede estar vacío.");
            return false;
        }

        double valor;
        try {
            String limpio = valorStr.replace(".", "");
            valor = Double.parseDouble(limpio);
        } catch (NumberFormatException e) {
            showErrorMessage(this, "Debe ingresar un número válido en valor.");
            return false;
        }

        if (valor < 1000) {
            showErrorMessage(this, "El valor debe ser mínimo 1.000.");
            return false;
        }

        if (tipo.isEmpty()) {
            showErrorMessage(this, "El tipo de tarifa no puede estar vacío.");
            return false;
        }

        return true;
    }
    
    /**
     * Agrega una nueva tarifa al sistema con los datos del formulario.
     * Muestra un mensaje de éxito o error según el resultado.
     */
    private void agregarTarifa() {
        if (!validarFormulario()) return;

        try {
        	TarifasVO t = obtenerTarifaFormulario();
            tarifasManager.agregarTarifa(t);
            refrescarTabla.run();
            
            showInfoMessage(this, "Tarifa agregada correctamente.");

        } catch (Exception ex) {
        	showErrorMessage(this, ex.getMessage());
        }
    }
    
    /**
     * Actualiza la tarifa seleccionada en la tabla con los datos del formulario.
     * Valida que haya una fila seleccionada antes de actualizar.
     */
    private void actualizarTarifa() {
        int fila = tablaTarifas.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione una tarifa.");
            return;
        }

        if (!validarFormulario()) return;

        try {
        	TarifasVO t = obtenerTarifaFormulario();
            tarifasManager.actualizarTarifa(fila, t);
            refrescarTabla.run();
            
            showInfoMessage(this, "Tarifa actualizada correctamente.");

        } catch (Exception ex) {
        	showErrorMessage(this, ex.getMessage());
        }
    }
    
    /**
     * Elimina la tarifa seleccionada en la tabla, previa confirmación del usuario.
     */
    private void eliminarTarifa() {
        int fila = tablaTarifas.getSelectedRow();

        if (fila == -1) {
        	showInfoMessage(this, "Seleccione una tarifa.");
            return;
        }
        
        if (!confirmMessage(this, "¿Está seguro de eliminar la tarifa seleccionada?")) return;
     
        tarifasManager.eliminarTarifa(fila);
        refrescarTabla.run();
        
        showInfoMessage(this, "Tarifa eliminada correctamente.");
    }
    
    /**
     * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtValor.setText("");
        cbxTipoTarifa.setSelectedIndex(0);
        tablaTarifas.clearSelection();
    }
    
    /**
     * Busca una tarifa por código y la resalta en la tabla, en caso contrario muestra un mensaje informativo.
     */
    private void buscarTarifa() {
        try {
            int codigo = Integer.parseInt(txtBuscar.getText().trim());
        	TarifasVO t = tarifasManager.buscarTarifaPorCodigo(codigo);
            if (t != null) {
            	highlightRow(tablaTarifas, modeloTabla, String.valueOf(codigo), 1);
            	
            } else {
            	showInfoMessage(this, "No se encontró la tarifa con código \"" + txtBuscar.getText() + "\".");
            }
            
        } catch (NumberFormatException ex) {
            showErrorMessage(this, "Debe ingresar un número válido para buscar.");
        } catch (Exception ex) { 
        	showErrorMessage(this, ex.getMessage());
        }
    }
}
