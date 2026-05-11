package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionTarifasManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiConfig.*;

import javax.swing.JPanel;

public class PanelTarifas extends JPanel {
	
    private GestionTarifasManager tarifasManager;
    
    /**
     * Constructor de la clase PanelTarifas.
     * @param tarifasManager gestor encargado de las operaciones relacionadas con tarifas
     */
    public PanelTarifas(GestionTarifasManager tarifasManager) {
        this.tarifasManager = tarifasManager;
        
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
