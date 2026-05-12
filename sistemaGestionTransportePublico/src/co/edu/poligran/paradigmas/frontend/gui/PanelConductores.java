package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import javax.swing.JPanel;

public class PanelConductores extends JPanel {
	
	private GestionConductoresManager conductorManager;

	/**
	 * Constructor de la clase PanelConductores.
	 * 
	 * @param conductorManager gestor encargado de las operaciones relacionadas con conductores
	 */
	public PanelConductores(GestionConductoresManager conductorManager) {
		this.conductorManager = conductorManager;
		
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
