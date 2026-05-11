package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiConfig.*;

import javax.swing.JPanel;

public class PanelVehiculos extends JPanel {
	
    private GestionVehiculosManager vehiculoManager;
    private GestionRutasManager rutaManager;
	
    /**
     * Constructor de la clase PanelVehiculos.
     * 
     * @param vehiculoManager gestor encargado de las operaciones relacionadas con vehículos
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     */
    public PanelVehiculos(GestionVehiculosManager vehiculoManager, GestionRutasManager rutaManager) {
		this.vehiculoManager = vehiculoManager;
		this.rutaManager = rutaManager;
		
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
