package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import co.edu.poligran.paradigmas.backend.vo.EmpleadoVO;
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

public class PanelEmpleados extends JPanel {
	
	private GestionEmpleadosManager empleadoManager;

	private JTextField txtIdentificacion;
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtBuscar;

	private JTable tablaEmpleados;

	private DefaultTableModel modeloTabla;

	private Runnable refrescarTabla;

	/**
	 * Constructor de la clase PanelEmpleados.
	 * 
	 * @param empleadoManager gestor encargado de las operaciones relacionadas con empleados
	 */
	public PanelEmpleados(GestionEmpleadosManager empleadoManager) {
		this.empleadoManager = empleadoManager;

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

		JPanel panelFormulario = createPanel("Gestión de Empleados");
		panelFormulario.setLayout(new GridBagLayout());

		GridBagConstraints g = createGbc();

		txtIdentificacion = createTextField(15);
		txtNombre = createTextField(15);
		txtEmail = createTextField(15);
		txtPassword = createTextField(15);
		txtBuscar = createTextField(15);

		// ── FILAS DEL FORMULARIO ─────────────────────────────────────────

		createFormRow(panelFormulario, g, 0, "Identificación:", txtIdentificacion);
		createFormRow(panelFormulario, g, 1, "Nombre:", txtNombre);
		createFormRow(panelFormulario, g, 2, "Email:", txtEmail);
		createFormRow(panelFormulario, g, 3, "Contraseña:", txtPassword);

		// ── BOTONES ────────────────────────────────────────

		JPanel panelBotones = createButtonRow(
			createBtn("Agregar", C_EXITO, e -> agregarEmpleado()),
			createBtn("Actualizar", C_PRIMARIO, e -> actualizarEmpleado()),
			createBtn("Eliminar", C_PELIGRO, e -> eliminarEmpleado()),
			createBtn("Limpiar", C_GRIS, e -> limpiarFormulario())
		);

		g.gridx = 0;
		g.gridy = 4;
		g.gridwidth = 2;

		panelFormulario.add(panelBotones, g);

		// ── PANEL BUSCADOR ────────────────────────────────────────

		JPanel panelBuscador = createPanel("Buscar empleado");
		panelBuscador.setLayout(new GridBagLayout());
		GridBagConstraints g4 = createGbc();

		createFormRow(panelBuscador, g4, 0, "Buscar identificación:", txtBuscar);
		JPanel rowBuscar = createButtonRow(createBtn("Buscar", C_PRIMARIO, e -> buscarEmpleado()));

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
			"Identificación",
			"Nombre",
			"Email",
			"Rol"
		};

		modeloTabla = new DefaultTableModel(columnas, 0);
		tablaEmpleados = new JTable(modeloTabla);

		// ── PANEL TABLA ────────────────────────────────

		JPanel panelTabla = createPanel("Empleados");
		panelTabla.setLayout(new BorderLayout(0, 6));
		panelTabla.add(new JScrollPane(tablaEmpleados), BorderLayout.CENTER);

		// ── PANEL PRINCIPAL ────────────────────────────────

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, panelTabla);
		split.setDividerLocation(350);
		add(split, BorderLayout.CENTER);

		// ── MÉTODO REFRESCAR TABLA ─────────────────────────

		refrescarTabla = () -> {
			modeloTabla.setRowCount(0);

			for (int i = 0; i < empleadoManager.obtenerEmpleados().size(); i++) {
				EmpleadoVO e = empleadoManager.obtenerEmpleados().get(i);

				modeloTabla.addRow(new Object[] {
					i,
					e.getIdentificacion(),
					e.getNombre(),
					e.getEmail(),
					e.getRol()
				});
			}
		};

		refrescarTabla.run();

		// ── CARGAR SELECCIÓN ───────────────────────────────

		initEventosSeleccion();
	}

	/**
	 * Inicializa el listener de selección de la tabla para cargar los datos del empleado seleccionado en el formulario.
	 */
	private void initEventosSeleccion() {
		tablaEmpleados.getSelectionModel().addListSelectionListener(e -> {
			int fila = tablaEmpleados.getSelectedRow();
			if (fila == -1) return;

			EmpleadoVO emp = empleadoManager.obtenerEmpleados().get(fila);
			txtIdentificacion.setText(emp.getIdentificacion());
			txtNombre.setText(emp.getNombre());
			txtEmail.setText(emp.getEmail());
			txtPassword.setText(emp.getPassword());
		});
	}

	/**
	 * Obtiene los datos del formulario y construye un objeto EmpleadoVO.
	 * 
	 * @return EmpleadoVO con los datos ingresados
	 */
	private EmpleadoVO obtenerEmpleadoFormulario() {
		return new EmpleadoVO(
			txtNombre.getText().trim(),
			txtEmail.getText().trim(),
			"Empleado",
			txtIdentificacion.getText().trim(),
			txtPassword.getText().trim()
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
		String email = txtEmail.getText().trim();
		String password = txtPassword.getText().trim();

		if (identificacion.isEmpty()) {
			showErrorMessage(this, "La identificación no puede estar vacía.");
			return false;
		}

		if (!identificacion.matches("\\d+")) {
			showErrorMessage(this, "La identificación solo debe contener números.");
			return false;
		}

		if (nombre.isEmpty()) {
			showErrorMessage(this, "El nombre no puede estar vacío.");
			return false;
		}

		if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
			showErrorMessage(this, "El nombre solo debe contener letras.");
			return false;
		}

		if (email.isEmpty()) {
			showErrorMessage(this, "El email no puede estar vacío.");
			return false;
		}

		if (!email.contains("@")) {
			showErrorMessage(this, "Ingrese un email válido.");
			return false;
		}

		if (password.isEmpty()) {
			showErrorMessage(this, "La contraseña no puede estar vacía.");
			return false;
		}

		if (password.length() < 4) {
			showErrorMessage(this, "La contraseña debe tener al menos 4 caracteres.");
			return false;
		}

		return true;
	}

	/**
	 * Agrega un nuevo empleado al sistema con los datos del formulario.
	 * Muestra un mensaje de éxito o error según el resultado.
	 */
	private void agregarEmpleado() {
		if (!validarFormulario()) return;

		try {
			EmpleadoVO e = obtenerEmpleadoFormulario();
			empleadoManager.agregarEmpleado(e);
			refrescarTabla.run();

			showInfoMessage(this, "Empleado agregado correctamente.");

		} catch (Exception ex) {
			showErrorMessage(this, ex.getMessage());
		}
	}

	/**
	 * Actualiza el empleado seleccionado en la tabla con los datos del formulario.
	 * Valida que haya una fila seleccionada antes de actualizar.
	 */
	private void actualizarEmpleado() {
		int fila = tablaEmpleados.getSelectedRow();

		if (fila == -1) {
			showInfoMessage(this, "Seleccione un empleado.");
			return;
		}

		if (!validarFormulario()) return;

		try {
			EmpleadoVO e = obtenerEmpleadoFormulario();
			empleadoManager.actualizarEmpleado(fila, e);
			refrescarTabla.run();

			showInfoMessage(this, "Empleado actualizado correctamente.");

		} catch (Exception ex) {
			showErrorMessage(this, ex.getMessage());
		}
	}

	/**
	 * Elimina el empleado seleccionado en la tabla, previa confirmación del usuario.
	 */
	private void eliminarEmpleado() {
		int fila = tablaEmpleados.getSelectedRow();

		if (fila == -1) {
			showInfoMessage(this, "Seleccione un empleado.");
			return;
		}

		if (!confirmMessage(this, "¿Está seguro de eliminar el empleado seleccionado?")) return;

		empleadoManager.eliminarEmpleado(fila);
		refrescarTabla.run();

		showInfoMessage(this, "Empleado eliminado correctamente.");
	}

	/**
	 * Limpia todos los campos del formulario y deselecciona la fila activa de la tabla.
	 */
	private void limpiarFormulario() {
		txtIdentificacion.setText("");
		txtNombre.setText("");
		txtEmail.setText("");
		txtPassword.setText("");
		tablaEmpleados.clearSelection();
	}

	/**
	 * Busca un empleado por identificación y lo resalta en la tabla, en caso contrario muestra un mensaje informativo.
	 */
	private void buscarEmpleado() {
		try {
			EmpleadoVO e = empleadoManager.buscarEmpleadoPorId(txtBuscar.getText().trim());
			if (e != null) {
				highlightRow(tablaEmpleados, modeloTabla, txtBuscar.getText().trim(), 1);

			} else {
				showInfoMessage(this, "No se encontró el empleado \"" + txtBuscar.getText() + "\".");
			}

		} catch (Exception ex) {
			showErrorMessage(this, ex.getMessage());
		}
	}
}
