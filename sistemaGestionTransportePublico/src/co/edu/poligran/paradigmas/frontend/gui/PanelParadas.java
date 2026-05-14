package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.vo.ParadaVO;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class PanelParadas extends JPanel {
	
    private GestionParadasManager paradaManager;
    private GestionRutasManager rutaManager;
    
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtUbicacion;
    private JTextField txtBuscar;

    private JTable tablaParadas;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;
	
    /**
     * Constructor de la clase PanelParadas.
     * 
     * @param paradaManager gestor encargado de las operaciones relacionadas con paradas
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     */
    public PanelParadas(GestionParadasManager paradaManager, GestionRutasManager rutaManager) {
		this.paradaManager = paradaManager;
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
        
        // ── PANEL ZONA IZQUIERDA ─────────────────────────────────────
       
        JPanel leftPanel = createPanel("panel");
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(new EmptyBorder(4, 2, 4, 6));

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario = createPanel("Gestión de Paradas");
        panelFormulario.setLayout(new GridBagLayout());
        
        // ── FORMULARIO ─────────────────────────────────────
        
        GridBagConstraints g = createGbc();
        
        txtCodigo = createTextField(15);
        txtNombre = createTextField(15);
        txtUbicacion = createTextField(15);
        txtBuscar = createTextField(15);

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────────
        
        createFormRow(panelFormulario, g, 0, "Código:", txtCodigo);
        createFormRow(panelFormulario, g, 1, "Nombre:", txtNombre);
        createFormRow(panelFormulario, g, 2, "Ubicación:", txtUbicacion);

        // ── BOTONES ────────────────────────────────────────
        
        JPanel panelBotones = createButtonRow(
        	createBtn("Agregar", C_EXITO, e -> agregarParada()),
        	createBtn("Actualizar", C_PRIMARIO, e -> actualizarParada()),
        	createBtn("Eliminar", C_PELIGRO, e -> eliminarParada()),
        	createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
        );

        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;

        panelFormulario.add(panelBotones, g);
        
        // ── PANEL BUSCADOR ────────────────────────────────────────
        
        JPanel panelBuscador = createPanel("Buscar parada");
        panelBuscador.setLayout(new GridBagLayout());
        GridBagConstraints g4 = createGbc();
        
        createFormRow(panelBuscador, g4, 0, "Buscar código:", txtBuscar);
        JPanel rowBuscar = createButtonRow(createBtn("Buscar", C_PRIMARIO, e -> buscarParada()));
        
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
            "Nombre",
            "Ubicación",
            "Rutas"
        };
        
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaParadas = new JTable(modeloTabla);
        
        // ── PANEL TABLA ────────────────────────────────
        
        JPanel panelTabla = createPanel("Paradas");
        panelTabla.setLayout(new BorderLayout(0, 6));
        panelTabla.add(new JScrollPane(tablaParadas), BorderLayout.CENTER);

        // ── PANEL PRINCIPAL ────────────────────────────────

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, panelTabla);
        split.setDividerLocation(350);
        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        refrescarTabla = () -> {
            modeloTabla.setRowCount(0);

            for (int i = 0; i < paradaManager.obtenerParadas().size(); i++) {
                ParadaVO p = paradaManager.obtenerParadas().get(i);

                modeloTabla.addRow(new Object[] {
                    i,
                    p.getCodigo(),
                    p.getNombre(),
                    p.getUbicacion(),
                    p.getRutas().size()
                });
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }
    
    /**
     * Inicializa el listener de selección de la tabla para cargar los datos de la parada seleccionada en el formulario.
     */
    private void initEventosSeleccion() {
        tablaParadas.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaParadas.getSelectedRow();
            if (fila == -1) return;

            ParadaVO p = paradaManager.obtenerParadas().get(fila);
            txtCodigo.setText(p.getCodigo());
            txtNombre.setText(p.getNombre());
            txtUbicacion.setText(p.getUbicacion());
        });
    }

    /**
     * Obtiene los datos del formulario y construye un objeto ParadaVO.
     * 
     * @return ParadaVO con los datos ingresados
     */
    private ParadaVO obtenerParadaFormulario() {
        return new ParadaVO(
            txtCodigo.getText().trim(),
            txtNombre.getText().trim(),
            txtUbicacion.getText().trim()
        );
    }
    
    /**
     * Agrega una nueva parada al sistema con los datos del formulario.
     * Muestra un mensaje de éxito o error según el resultado.
     */
    private void agregarParada() {
        try {
        	ParadaVO p = obtenerParadaFormulario();
            paradaManager.agregarParada(p);
            refrescarTabla.run();
            
            showInfoMessage(this, "Parada agregada correctamente.");

        } catch (Exception ex) {
        	showErrorMessage(this, ex.getMessage());
        }
    }
    
    /**
     * Actualiza la parada seleccionada en la tabla con los datos del formulario.
     * Valida que haya una fila seleccionada antes de actualizar.
     */
    private void actualizarParada() {
        int fila = tablaParadas.getSelectedRow();

        if (fila == -1) {
            showInfoMessage(this, "Seleccione una parada.");
            return;
        }

        try {
        	ParadaVO p = obtenerParadaFormulario();
            paradaManager.actualizarParada(fila, p);
            refrescarTabla.run();
            
            showInfoMessage(this, "Parada actualizada correctamente.");

        } catch (Exception ex) {
        	showErrorMessage(this, ex.getMessage());
        }
    }
    
    /**
     * Elimina la parada seleccionada en la tabla, previa confirmación del usuario.
     */
    private void eliminarParada() {
        int fila = tablaParadas.getSelectedRow();

        if (fila == -1) {
        	showInfoMessage(this, "Seleccione una parada.");
            return;
        }
        
        if (!confirmMessage(this, "¿Está seguro de eliminar la parada seleccionada?")) return;
     
        paradaManager.eliminarParada(fila);
        refrescarTabla.run();
        
        showInfoMessage(this, "Parada eliminada correctamente.");
    }
    
    /**
     * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtUbicacion.setText("");
        tablaParadas.clearSelection();
    }
    
    /**
     * Busca una parada por código y la resalta en la tabla, en caso contrario muestra un mensaje informativo.
     */
    private void buscarParada() {
        try {
        	ParadaVO p = paradaManager.buscarParadaPorCodigo(txtBuscar.getText().trim());
            if (p != null) {
            	highlightRow(tablaParadas, modeloTabla, txtBuscar.getText().trim(), 1);
            	
            } else {
            	showInfoMessage(this, "No se encontró la parada \"" + txtBuscar.getText() + "\".");
            }
            
        } catch (Exception ex) { 
        	showErrorMessage(this, ex.getMessage());
        }
    }
}
