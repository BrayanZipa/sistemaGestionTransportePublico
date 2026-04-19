package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.MantenimientoVO;

/**
 * Clase encargada de gestionar el CRUD de mantenimientos
 */
public class GestionMantenimientoManager {

	List<MantenimientoVO> listaMantenimientos = new ArrayList<>();

    /**
     * Método que permite agregar mantenimientos al catálogo
     * @param m objeto de tipo MantenimientoVO que se va a agregar
     * @return la lista de mantenimientos
     */
    public List<MantenimientoVO> agregarMantenimiento(MantenimientoVO m){
        listaMantenimientos.add(m);
        return listaMantenimientos;
    }

    /**
     * Método que permite obtener todos los mantenimientos
     * @return lista de mantenimientos
     */
    public List<MantenimientoVO> obtenerMantenimientos(){
        return listaMantenimientos;
    }

    /**
     * Método que permite buscar mantenimiento por ID
     * @param idMantenimiento identificador del mantenimiento
     * @return el mantenimiento encontrado o null
     */
    public MantenimientoVO buscarMantenimientoPorId(String idMantenimiento) {
        MantenimientoVO respuesta = null;
        for(MantenimientoVO m : listaMantenimientos) {
            if(m.getIdMantenimiento().equalsIgnoreCase(idMantenimiento)) {
                respuesta = m;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Método que permite obtener el estado de un mantenimiento por ID
     * @param idMantenimiento identificador
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorId(String idMantenimiento) {
        for(int i = 0; i < listaMantenimientos.size(); i++) {
            if(listaMantenimientos.get(i).getIdMantenimiento().equalsIgnoreCase(idMantenimiento)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar un mantenimiento por índice
     * @param indice posición
     * @param m nuevo mantenimiento
     * @return mantenimiento anterior o null si no existe
     */
    public MantenimientoVO actualizarMantenimiento(int indice, MantenimientoVO m){
        if(indice >= 0 && indice < listaMantenimientos.size()) {
            return listaMantenimientos.set(indice, m);
        }
        return null;
    }

    /**
     * Método que permite actualizar un mantenimiento por ID
     * @param idMantenimiento identificador
     * @param m nuevo mantenimiento
     * @return true si se actualizó
     */
    public boolean actualizarMantenimientoPorId(String idMantenimiento, MantenimientoVO m){
        int indice = obtenerIndicePorId(idMantenimiento);
        if(indice != -1) {
            listaMantenimientos.set(indice, m);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un mantenimiento por índice
     * @param indice posición
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarMantenimiento(int indice){
        if(indice >= 0 && indice < listaMantenimientos.size()) {
            listaMantenimientos.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un mantenimiento por ID
     * @param idMantenimiento identificador
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarMantenimientoPorId(String idMantenimiento){
        int indice = obtenerIndicePorId(idMantenimiento);
        if(indice != -1) {
            listaMantenimientos.remove(indice);
            return true;
        }
        return false;
    }
}