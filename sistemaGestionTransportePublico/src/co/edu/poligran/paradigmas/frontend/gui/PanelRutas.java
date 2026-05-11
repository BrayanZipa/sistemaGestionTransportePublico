package co.edu.poligran.paradigmas.frontend.gui;

import co.edu.poligran.paradigmas.backend.negocio.GestionBoletosManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionConductoresManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionParadasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionRutasManager;
import co.edu.poligran.paradigmas.backend.negocio.GestionVehiculosManager;
import static co.edu.poligran.paradigmas.frontend.gui.GuiConfig.*;

import javax.swing.JPanel;

public class PanelRutas extends JPanel {
	
    private GestionRutasManager rutaManager;
    private GestionParadasManager paradaManager;
    private GestionBoletosManager boletoManager;
    private GestionVehiculosManager vehiculoManager;
    private GestionConductoresManager conductorManager;
    
    /**
     * Constructor de la clase PanelRutas.
     * 
     * @param rutaManager gestor encargado de las operaciones relacionadas con rutas
     * @param paradaManager gestor encargado de las operaciones relacionadas con paradas
     * @param boletoManager gestor encargado de las operaciones relacionadas con boletos
     * @param vehiculoManager gestor encargado de las operaciones relacionadas con vehiculos
     * @param conductorManager gestor encargado de las operaciones relacionadas con conductores
     */
    public PanelRutas(GestionRutasManager rutaManager, GestionParadasManager paradaManager, GestionBoletosManager boletoManager, GestionVehiculosManager vehiculoManager, GestionConductoresManager conductorManager) {
        this.rutaManager = rutaManager;
        this.paradaManager = paradaManager;
        this.boletoManager = boletoManager;
        this.vehiculoManager = vehiculoManager;
        this.conductorManager = conductorManager;
        
		configurarPanel();
	}
    
    private void configurarPanel() {
        setBackground(C_SECUNDARIO);
    }
}
