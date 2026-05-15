package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.vo.PasajeroVO;
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

public class PanelPasajeros extends JPanel {

	private GestionPasajerosManager pasajeroManager;

	private JTextField txtIdentificacion;
	private JTextField txtNombre;
	private JTextField txtBuscar;

	private JTable tablaPasajeros;

	private DefaultTableModel modeloTabla;

	private Runnable refrescarTabla;

	/**
	 * Constructor de la clase PanelPasajeros.
	 * 
	 * @param pasajeroManager gestor encargado de las operaciones relacionadas con pasajeros
	 */
	public PanelPasajeros(GestionPasajerosManager pasajeroManager) {
		this.pasajeroManager = pasajeroManager;

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

		// ── FORMULARIO ─────────────────────────────────────

		JPanel panelFormulario = createPanel("Gestión de Pasajeros");
		panelFormulario.setLayout(new GridBagLayout());

		GridBagConstraints g = createGbc();

		txtIdentificacion = createTextField(15);
		txtNombre = createTextField(15);
		txtBuscar = createTextField(15);

		// ── FILAS DEL FORMULARIO ─────────────────────────────────────────

		createFormRow(panelFormulario, g, 0, "Identificación:", txtIdentificacion);
		createFormRow(panelFormulario, g, 1, "Nombre:", txtNombre);

		// ── BOTONES ────────────────────────────────────────

		JPanel panelBotones = createButtonRow(
			createBtn("Agregar", C_EXITO, e -> agregarPasajero()),
			createBtn("Actualizar", C_PRIMARIO, e -> actualizarPasajero()),
			createBtn("Eliminar", C_PELIGRO, e -> eliminarPasajero()),
			createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
		);

		g.gridx = 0;
		g.gridy = 2;
		g.gridwidth = 2;

		panelFormulario.add(panelBotones, g);

		// ── PANEL BUSCADOR ────────────────────────────────────────

		JPanel panelBuscador = createPanel("Buscar pasajero");
		panelBuscador.setLayout(new GridBagLayout());
		GridBagConstraints g4 = createGbc();

		createFormRow(panelBuscador, g4, 0, "Buscar identificación:", txtBuscar);
		JPanel rowBuscar = createButtonRow(createBtn("Buscar", C_PRIMARIO, e -> buscarPasajero()));

		g4.gridx = 0;
		g4.gridy = 1;
		g4.gridwidth = 2;

		panelBuscador.add(rowBuscar, g4);

		leftPanel.add(panelFormulario);
		leftPanel.add(Box.createVerticalStrut(10));
		leftPanel.add(panelBuscador);

		// ── TABLA ──────────────────────────────────────────

		String[] columnas = {
			"Indice",
			"Identificación",
			"Nombre"
		};

		modeloTabla = new DefaultTableModel(columnas, 0);
		tablaPasajeros = new JTable(modeloTabla);

		// ── PANEL TABLA ────────────────────────────────

		JPanel panelTabla = createPanel("Pasajeros");
		panelTabla.setLayout(new BorderLayout(0, 6));
		panelTabla.add(new JScrollPane(tablaPasajeros), BorderLayout.CENTER);

		// ── PANEL PRINCIPAL ────────────────────────────────

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, panelTabla);
		split.setDividerLocation(350);
		add(split, BorderLayout.CENTER);

		// ── MÉTODO REFRESCAR TABLA ─────────────────────────

		refrescarTabla = () -> {
			modeloTabla.setRowCount(0);

			for (int i = 0; i < pasajeroManager.obtenerPasajeros().size(); i++) {
				PasajeroVO p = pasajeroManager.obtenerPasajeros().get(i);

				modeloTabla.addRow(new Object[] {
					i,
					p.getIdentificacion(),
					p.getNombre()
				});
			}
		};

		refrescarTabla.run();

		// ── CARGAR SELECCIÓN ───────────────────────────────

		initEventosSeleccion();
	}

	/**
	 * Inicializa el listener de selección de la tabla para cargar los datos del pasajero seleccionado en el formulario.
	 */
	private void initEventosSeleccion() {
		tablaPasajeros.getSelectionModel().addListSelectionListener(e -> {
			int fila = tablaPasajeros.getSelectedRow();
			if (fila == -1) return;

			PasajeroVO pasajero = pasajeroManager.obtenerPasajeros().get(fila);
			txtIdentificacion.setText(pasajero.getIdentificacion());
			txtNombre.setText(pasajero.getNombre());
		});
	}

	/**
	 * Obtiene los datos del formulario y construye un objeto PasajeroVO.
	 * 
	 * @return PasajeroVO con los datos ingresados
	 */
	private PasajeroVO obtenerPasajeroFormulario() {
		return new PasajeroVO(
			txtIdentificacion.getText().trim(),
			txtNombre.getText().trim()
		);
	}

	/**
	 * Valida los campos del formulario antes de enviarlos al gestor.
	 * 
	 * @return true si todos los campos son válidos, false en caso contrario
	 */
	private boolean validarFormulario() {
		String identificacion = txtIdentificacion.getText().trim();
		String nombre = txtNombre.getText().trim();

		if (identificacion.isEmpty()) {
			showErrorMessage(this, "La identificación no puede estar vacia.");
			return false;
		}

		if (!identificacion.matches("\\d+")) {
			showErrorMessage(this, "La identificación solo debe contener números.");
			return false;
		}

		if (nombre.isEmpty()) {
			showErrorMessage(this, "El nombre no puede estar vacio.");
			return false;
		}

		if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
			showErrorMessage(this, "El nombre solo debe contener letras.");
			return false;
		}

		return true;
	}

	/**
	 * Agrega un nuevo pasajero al sistema con los datos del formulario.
	 * Muestra un mensaje de éxito o error según el resultado.
	 */
	private void agregarPasajero() {
		if (!validarFormulario()) return;

		try {
			PasajeroVO p = obtenerPasajeroFormulario();
			pasajeroManager.agregarPasajero(p);
			refrescarTabla.run();

			showInfoMessage(this, "Pasajero agregado correctamente.");

		} catch (Exception ex) {
			showErrorMessage(this, ex.getMessage());
		}
	}

	/**
	 * Actualiza el pasajero seleccionado en la tabla con los datos del formulario.
	 * Valida que haya una fila seleccionada antes de actualizar.
	 */
	private void actualizarPasajero() {
		int fila = tablaPasajeros.getSelectedRow();

		if (fila == -1) {
			showInfoMessage(this, "Seleccione un pasajero.");
			return;
		}

		if (!validarFormulario()) return;

		try {
			PasajeroVO p = obtenerPasajeroFormulario();
			pasajeroManager.actualizarPasajero(fila, p);
			refrescarTabla.run();

			showInfoMessage(this, "Pasajero actualizado correctamente.");

		} catch (Exception ex) {
			showErrorMessage(this, ex.getMessage());
		}
	}

	/**
	 * Elimina el pasajero seleccionado en la tabla, previa confirmación del usuario.
	 */
	private void eliminarPasajero() {
		int fila = tablaPasajeros.getSelectedRow();

		if (fila == -1) {
			showInfoMessage(this, "Seleccione un pasajero.");
			return;
		}

		if (!confirmMessage(this, "¿Está seguro de eliminar el vehículo seleccionado?")) return;

		pasajeroManager.eliminarPasajero(fila);
		refrescarTabla.run();

		showInfoMessage(this, "Pasajero eliminado correctamente.");
	}

	/**
	 * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
	 */
	private void limpiarFormulario() {
		txtIdentificacion.setText("");
		txtNombre.setText("");
		tablaPasajeros.clearSelection();
	}

	/**
	 * Busca un pasajero por identificación y lo resalta en la tabla, en caso contrario muestra un mensaje informativo.
	 */
	private void buscarPasajero() {
		try {
			PasajeroVO p = pasajeroManager.buscarPasajeroPorIdentificacion(txtBuscar.getText().trim());
			if (p != null) {
				highlightRow(tablaPasajeros, modeloTabla, txtBuscar.getText().trim(), 1);

			} else {
				showInfoMessage(this, "No se encontró el pasajero \"" + txtBuscar.getText() + "\".");
			}

		} catch (Exception ex) {
			showErrorMessage(this, ex.getMessage());
		}
	}
}
