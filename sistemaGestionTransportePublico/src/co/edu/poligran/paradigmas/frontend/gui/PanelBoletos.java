package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPagosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionPasajerosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionTarifasManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiUtils.*;

import javax.swing.JPanel;

public class PanelBoletos extends JPanel {
	
    private GestionBoletosManager boletoManager;
    private GestionPasajerosManager pasajeroManager;
    private GestionRutasManager rutaManager;
    private GestionPagosManager pagosManager;
    private GestionTarifasManager tarifasManager;
    
    /**
     * Constructor de la clase PanelBoletos.
     * 
     * @param boletoManager gestor encargado de las operaciones relacionadas con boletos
     * @param pasajeroManager gestor encargado de las operaciones relacionadas con pasajeros
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     */
    public PanelBoletos(GestionBoletosManager boletoManager, GestionPasajerosManager pasajeroManager, GestionRutasManager rutaManager,GestionPagosManager pagosManager, GestionTarifasManager tarifasManager ) {
		this.boletoManager = boletoManager;
		this.pasajeroManager = pasajeroManager;
		this.rutaManager = rutaManager;
		this.pagosManager = pagosManager;
		this.tarifasManager = tarifasManager;
		
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
