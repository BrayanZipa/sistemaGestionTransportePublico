package co.edu.poligran.paradigmas.backend.negocio;
 
import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.PasajeroVO;
 
/**
* Clase encargada de gestionar el CRUD de pasajeros
*/
public class GestionPasajerosManager {
 
    private List<PasajeroVO> listaPasajeros = new ArrayList<>();
 
    /**
     * Método que permite agregar pasajeros al catálogo
     * @param p objeto de tipo PasajeroVO que se va a agregar
     * @return la lista de pasajeros
     */
    public List<PasajeroVO> agregarPasajero(PasajeroVO p) {
        listaPasajeros.add(p);
        return listaPasajeros;
    }
 
    /**
     * Método que permite obtener todos los pasajeros
     * @return lista de pasajeros
     */
    public List<PasajeroVO> obtenerPasajeros() {
        return listaPasajeros;
    }
 
    /**
     * Método que permite buscar pasajero por identificación
     * @param identificacion identificador del pasajero
     * @return el pasajero encontrado o null
     */
    public PasajeroVO buscarPasajeroPorIdentificacion(String identificacion) {
        for (PasajeroVO p : listaPasajeros) {
            if (p.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return p;
            }
        }
        return null;
    }
 
    /**
     * Método que permite obtener la posición de un pasajero por identificación
     * @param identificacion del pasajero
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorIdentificacion(String identificacion) {
        for (int i = 0; i < listaPasajeros.size(); i++) {
            if (listaPasajeros.get(i).getIdentificacion().equalsIgnoreCase(identificacion)) {
                return i;
            }
        }
        return -1;
    }
 
    /**
     * Método que permite actualizar un pasajero por índice
     * @param indice posición
     * @param p nuevo pasajero
     * @return pasajero anterior o null si no existe
     */
    public PasajeroVO actualizarPasajero(int indice, PasajeroVO p) {
        if (indice >= 0 && indice < listaPasajeros.size()) {
            return listaPasajeros.set(indice, p);
        }
        return null;
    }
 
    /**
     * Método que permite actualizar un pasajero por identificación
     * @param identificacion identificador
     * @param p nuevo pasajero
     * @return true si se actualizó
     */
    public boolean actualizarPasajeroPorIdentificacion(String identificacion, PasajeroVO p) {
        int indice = obtenerIndicePorIdentificacion(identificacion);
 
        if (indice != -1) {
            p.setIdentificacion(identificacion);
            listaPasajeros.set(indice, p);
            return true;
        }
 
        return false;
    }
 
    /**
     * Método que permite eliminar un pasajero por índice
     * @param indice posición
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarPasajero(int indice) {
        if (indice >= 0 && indice < listaPasajeros.size()) {
            listaPasajeros.remove(indice);
            return true;
        }
        return false;
    }
 
    /**
     * Método que permite eliminar un pasajero por identificación
     * @param identificacion identificador
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarPasajeroPorIdentificacion(String identificacion) {
        int indice = obtenerIndicePorIdentificacion(identificacion);
 
        if (indice != -1) {
            listaPasajeros.remove(indice);
            return true;
        }
 
        return false;
    }
}
