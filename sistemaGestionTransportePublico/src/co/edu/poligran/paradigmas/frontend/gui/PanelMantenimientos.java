package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionMantenimientoManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import javax.swing.JPanel;

public class PanelMantenimientos extends JPanel {
	
	private GestionMantenimientoManager mantenimientoManager;
	private GestionVehiculosManager vehiculoManager;

	/**
	 * Constructor de la clase PanelMantenimientos.
	 * 
	 * @param mantenimientoManager gestor encargado de mantenimientos
	 * @param vehiculoManager gestor encargado de vehículos
	 */
	public PanelMantenimientos(GestionMantenimientoManager mantenimientoManager, GestionVehiculosManager vehiculoManager) {
		this.mantenimientoManager = mantenimientoManager;
		this.vehiculoManager = vehiculoManager;
		
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
