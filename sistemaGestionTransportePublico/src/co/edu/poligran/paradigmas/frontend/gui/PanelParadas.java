package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import javax.swing.JPanel;

public class PanelParadas extends JPanel {
	
    private GestionParadasManager paradaManager;
    private GestionRutasManager rutaManager;

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
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
