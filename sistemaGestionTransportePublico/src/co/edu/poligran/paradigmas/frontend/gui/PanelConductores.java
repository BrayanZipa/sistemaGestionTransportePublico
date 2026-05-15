package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.vo.ConductorVO;
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

public class PanelConductores extends JPanel {

    private GestionConductoresManager conductorManager;

    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtIdentificacion;
    private JTextField txtLicencia;
    private JTextField txtBuscar;

    private JTable tablaConductores;

    private DefaultTableModel modeloTabla;

    private Runnable refrescarTabla;

    /**
     * Constructor de la clase PanelConductores.
     * 
     * @param conductorManager gestor encargado de las operaciones relacionadas con conductores
     */
    public PanelConductores(GestionConductoresManager conductorManager) {

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

        leftPanel.setLayout(
            new BoxLayout(leftPanel, BoxLayout.Y_AXIS)
        );

        leftPanel.setBorder(
            new EmptyBorder(4, 2, 4, 6)
        );

        // ── PANEL FORMULARIO ─────────────────────────────────────

        JPanel panelFormulario =
            createPanel("Gestión de Conductores");

        panelFormulario.setLayout(
            new GridBagLayout()
        );

        // ── FORMULARIO ─────────────────────────────────────

        GridBagConstraints g = createGbc();

        txtNombre = createTextField(15);

        txtEmail = createTextField(15);

        txtIdentificacion = createTextField(15);

        txtLicencia = createTextField(15);

        txtBuscar = createTextField(15);

        // ── FILAS DEL FORMULARIO ─────────────────────────────────────

        createFormRow(
            panelFormulario,
            g,
            0,
            "Nombre:",
            txtNombre
        );

        createFormRow(
            panelFormulario,
            g,
            1,
            "Email:",
            txtEmail
        );

        createFormRow(
            panelFormulario,
            g,
            2,
            "Identificación:",
            txtIdentificacion
        );

        createFormRow(
            panelFormulario,
            g,
            3,
            "Licencia:",
            txtLicencia
        );

        // ── MENSAJE VALIDACIÓN ─────────────────────────────────────

        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 2;

        panelFormulario.add(
            createLabel(
                "* Licencia válida: A1, B2, C1..."
            ),
            g
        );

        // ── BOTONES ────────────────────────────────────────

        JPanel panelBotones = createButtonRow(

            createBtn(
                "Agregar",
                C_EXITO,
                e -> agregarConductor()
            ),

            createBtn(
                "Actualizar",
                C_PRIMARIO,
                e -> actualizarConductor()
            ),

            createBtn(
                "Eliminar",
                C_PELIGRO,
                e -> eliminarConductor()
            ),

            createBtn(
                "Limpiar",
                C_GRIS,
                e -> limpiarFormulario()
            )
        );

        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 2;

        panelFormulario.add(
            panelBotones,
            g
        );

        // ── PANEL BUSCADOR ────────────────────────────────────────

        JPanel panelBuscador =
            createPanel("Buscar conductor");

        panelBuscador.setLayout(
            new GridBagLayout()
        );

        GridBagConstraints g4 = createGbc();

        createFormRow(
            panelBuscador,
            g4,
            0,
            "Buscar ID:",
            txtBuscar
        );

        JPanel rowBuscar = createButtonRow(

            createBtn(
                "Buscar",
                C_PRIMARIO,
                e -> buscarConductor()
            )
        );

        g4.gridx = 0;
        g4.gridy = 1;
        g4.gridwidth = 2;

        panelBuscador.add(
            rowBuscar,
            g4
        );

        leftPanel.add(panelFormulario);

        leftPanel.add(
            Box.createVerticalStrut(10)
        );

        leftPanel.add(panelBuscador);

        // ── TABLA ──────────────────────────────────────────

        String[] columnas = {
            "Índice",
            "Nombre",
            "Email",
            "Identificación",
            "Licencia"
        };

        modeloTabla =
            new DefaultTableModel(columnas, 0);

        tablaConductores =
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
            leftPanel,
            panelTabla
        );

        split.setDividerLocation(350);

        add(split, BorderLayout.CENTER);

        // ── MÉTODO REFRESCAR TABLA ─────────────────────────

        refrescarTabla = () -> {

            modeloTabla.setRowCount(0);

            for (
                int i = 0;
                i < conductorManager.obtenerConductores().size();
                i++
            ) {

                ConductorVO c =
                    conductorManager
                        .obtenerConductores()
                        .get(i);

                modeloTabla.addRow(
                    new Object[] {
                        i,
                        c.getNombre(),
                        c.getEmail(),
                        c.getIdentificacion(),
                        c.getLicencia()
                    }
                );
            }
        };

        refrescarTabla.run();

        // ── CARGAR SELECCIÓN ───────────────────────────────

        initEventosSeleccion();
    }

    /**
     * Inicializa el listener de selección de la tabla para cargar
     * los datos del conductor seleccionado en el formulario.
     */
    private void initEventosSeleccion() {

        tablaConductores.getSelectionModel()
            .addListSelectionListener(e -> {

            int fila =
                tablaConductores.getSelectedRow();

            if (fila == -1) return;

            ConductorVO c =
                conductorManager
                    .obtenerConductores()
                    .get(fila);

            txtNombre.setText(
                c.getNombre()
            );

            txtEmail.setText(
                c.getEmail()
            );

            txtIdentificacion.setText(
                c.getIdentificacion()
            );

            txtLicencia.setText(
                c.getLicencia()
            );
        });
    }

    /**
     * Obtiene los datos del formulario y construye
     * un objeto ConductorVO.
     * 
     * @return ConductorVO con los datos ingresados
     */
    private ConductorVO obtenerConductorFormulario() {

        return new ConductorVO(

            txtNombre.getText().trim(),

            txtEmail.getText().trim(),

            "Conductor",

            txtIdentificacion.getText().trim(),

            txtLicencia.getText()
                .trim()
                .toUpperCase()
        );
    }

    /**
     * Agrega un nuevo conductor al sistema con los datos
     * ingresados en el formulario.
     */
    private void agregarConductor() {

        try {

            String nombre =
                txtNombre.getText().trim();

            String email =
                txtEmail.getText().trim();

            String identificacion =
                txtIdentificacion.getText().trim();

            String licencia =
                txtLicencia.getText()
                    .trim()
                    .toUpperCase();

            if (nombre.isEmpty()) {

                showInfoMessage(
                    this,
                    "El nombre no puede estar vacío."
                );

                return;
            }

            if (!nombre.matches(
                "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"
            )) {

                showInfoMessage(
                    this,
                    "El nombre solo debe contener letras."
                );

                return;
            }

            if (email.isEmpty()) {

                showInfoMessage(
                    this,
                    "El email no puede estar vacío."
                );

                return;
            }

            if (!email.contains("@")) {

                showInfoMessage(
                    this,
                    "Ingrese un email válido."
                );

                return;
            }

            if (!identificacion.matches("\\d+")) {

                showInfoMessage(
                    this,
                    "La identificación solo debe contener números."
                );

                return;
            }

            if (!licencia.matches("[A-Z]\\d")) {

                showInfoMessage(
                    this,
                    "Licencia inválida. Ejemplo válido: A1, B2, C1."
                );

                return;
            }

            ConductorVO c =
                obtenerConductorFormulario();

            conductorManager.agregarConductor(c);

            refrescarTabla.run();

            showInfoMessage(
                this,
                "Conductor agregado correctamente."
            );

        } catch (Exception ex) {

            showErrorMessage(
                this,
                ex.getMessage()
            );
        }
    }

    /**
     * Actualiza el conductor seleccionado en la tabla con
     * los nuevos datos del formulario.
     */
    private void actualizarConductor() {

        int fila =
            tablaConductores.getSelectedRow();

        if (fila == -1) {

            showInfoMessage(
                this,
                "Seleccione un conductor."
            );

            return;
        }

        try {

            ConductorVO c =
                conductorManager
                    .obtenerConductores()
                    .get(fila);

            String nuevoNombre =
                txtNombre.getText().trim();

            if (!nuevoNombre.isEmpty()) {

                if (nuevoNombre.matches(
                    "[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"
                )) {

                    c.setNombre(nuevoNombre);

                } else {

                    showInfoMessage(
                        this,
                        "Nombre inválido. Solo letras. Se conserva el anterior."
                    );
                }
            }

            String nuevoEmail =
                txtEmail.getText().trim();

            if (!nuevoEmail.isEmpty()) {

                if (nuevoEmail.contains("@")) {

                    c.setEmail(nuevoEmail);

                } else {

                    showInfoMessage(
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

                    showInfoMessage(
                        this,
                        "Licencia inválida. Ejemplo válido: A1, B2, C1."
                    );
                }
            }

            conductorManager.actualizarConductor(
                fila,
                c
            );

            refrescarTabla.run();

            showInfoMessage(
                this,
                "Conductor actualizado correctamente."
            );

        } catch (Exception ex) {

            showErrorMessage(
                this,
                ex.getMessage()
            );
        }
    }

    /**
     * Elimina el conductor seleccionado en la tabla,
     * previa confirmación del usuario.
     */
    private void eliminarConductor() {

        int fila =
            tablaConductores.getSelectedRow();

        if (fila == -1) {

            showInfoMessage(
                this,
                "Seleccione un conductor."
            );

            return;
        }

        if (!confirmMessage(
            this,
            "¿Está seguro de eliminar el conductor seleccionado?"
        )) return;

        conductorManager.eliminarConductor(fila);

        refrescarTabla.run();

        showInfoMessage(
            this,
            "Conductor eliminado correctamente."
        );
    }

    /**
     * Limpia todos los campos del formulario y
     * deselecciona la fila activa de la tabla.
     */
    private void limpiarFormulario() {

        txtNombre.setText("");

        txtEmail.setText("");

        txtIdentificacion.setText("");

        txtLicencia.setText("");

        tablaConductores.clearSelection();
    }

    /**
     * Busca un conductor por identificación y lo resalta en la tabla.
     */
    private void buscarConductor() {

        try {

            String id =
                txtBuscar.getText().trim();

            if (!id.matches("\\d+")) {

                showInfoMessage(
                    this,
                    "La identificación debe contener solo números."
                );

                return;
            }

            ConductorVO c =
                conductorManager
                    .buscarConductorPorId(id);

            if (c != null) {

                highlightRow(
                    tablaConductores,
                    modeloTabla,
                    id,
                    3
                );

            } else {

                showInfoMessage(
                    this,
                    "No se encontró el conductor \"" + id + "\"."
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