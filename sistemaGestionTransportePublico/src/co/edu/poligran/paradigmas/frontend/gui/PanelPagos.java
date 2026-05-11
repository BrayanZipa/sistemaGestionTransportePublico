package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiConfig.*;

import javax.swing.JPanel;

public class PanelPagos extends JPanel {
	
	private GestionPagosManager pagosManager;

	/**
	 * Constructor de la clase PanelPagos.
	 * 
	 * @param pagosManager gestor encargado de las operaciones relacionadas con pagos
	 */
	public PanelPagos(GestionPagosManager pagosManager) {
		this.pagosManager = pagosManager;
		
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
