package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import javax.swing.JPanel;

public class PanelPasajeros extends JPanel {

    private GestionPasajerosManager pasajeroManager;
    
    /**
     * Constructor de la clase PanelPasajeros.
     * @param pasajeroManager gestor encargado de las operaciones relacionadas con pasajeros
     */
    public PanelPasajeros(GestionPasajerosManager pasajeroManager) {
        this.pasajeroManager = pasajeroManager;
        
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
