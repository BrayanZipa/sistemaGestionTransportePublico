package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.RutaVO;

/**
 * Clase encargada de gestionar el CRUD de rutas
 */
public class GestionRutasManager {

    List<RutaVO> listaRutas = new ArrayList<>();

    /**
     * Método que permite agregar rutas al catálogo
     * @param r objeto de tipo RutaVO que se va a agregar
     * @return la lista de rutas
     */
    public List<RutaVO> agregarRuta(RutaVO r){
        listaRutas.add(r);
        return listaRutas;
    }

    /**
     * Método que permite obtener todas las rutas
     * @return lista de rutas
     */
    public List<RutaVO> obtenerRutas(){
        return listaRutas;
    }

    /**
     * Método que permite buscar una ruta por código
     * @param codigo identificador de la ruta
     * @return la ruta encontrada o null
     */
    public RutaVO buscarRutaPorCodigo(int codigo) {
        RutaVO respuesta = null;
        for(RutaVO r : listaRutas) {
            if(r.getCodigo() == codigo) {
                respuesta = r;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Método que permite obtener la posición de una ruta por código
     * @param codigo identificador de la ruta
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorCodigo(int codigo) {
        for(int i = 0; i < listaRutas.size(); i++) {
            if(listaRutas.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar una ruta por índice
     * @param indice posición en la lista
     * @param r nueva ruta
     * @return ruta anterior o null si no existe
     */
    public RutaVO actualizarRuta(int indice, RutaVO r){
        if(indice >= 0 && indice < listaRutas.size()) {
            return listaRutas.set(indice, r);
        }
        return null;
    }

    /**
     * Método que permite actualizar una ruta por código
     * @param codigo identificador de la ruta
     * @param r nueva ruta
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarRutaPorCodigo(int codigo, RutaVO r){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaRutas.set(indice, r);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar una ruta por índice
     * @param indice posición en la lista
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarRuta(int indice){
        if(indice >= 0 && indice < listaRutas.size()) {
            listaRutas.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar una ruta por código
     * @param codigo identificador de la ruta
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarRutaPorCodigo(int codigo){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaRutas.remove(indice);
            return true;
        }
        return false;
    }
}