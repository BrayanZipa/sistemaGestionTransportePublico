package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionEmpleadosManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiConfig.*;

import javax.swing.JPanel;

public class PanelEmpleados extends JPanel {
	
	private GestionEmpleadosManager empleadoManager;

	/**
	 * Constructor de la clase PanelEmpleados.
	 * 
	 * @param empleadoManager gestor encargado de las operaciones relacionadas con empleados
	 */
	public PanelEmpleados(GestionEmpleadosManager empleadoManager) {
		this.empleadoManager = empleadoManager;
		
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
