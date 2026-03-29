package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.ParadaVO;

/**
 * Clase encargada de gestionar el CRUD de paradas
 */
public class GestionParadasManager {

    List<ParadaVO> listaParadas = new ArrayList<>();

    /**
     * Método que permite agregar paradas al catálogo
     * @param p objeto de tipo ParadaVO que se va a agregar
     * @return la lista de paradas
     */
    public List<ParadaVO> agregarParada(ParadaVO p){
        listaParadas.add(p);
        return listaParadas;
    }

    /**
     * Método que permite obtener todas las paradas
     * @return lista de paradas
     */
    public List<ParadaVO> obtenerParadas(){
        return listaParadas;
    }

    /**
     * Método que permite buscar una parada por código
     * @param codigo identificador de la parada
     * @return la parada encontrada o null
     */
    public ParadaVO buscarParadaPorCodigo(String codigo) {
        ParadaVO respuesta = null;
        for(ParadaVO p : listaParadas) {
            if(p.getCodigo().equalsIgnoreCase(codigo)) {
                respuesta = p;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Método que permite obtener la posición de una parada por código
     * @param codigo identificador de la parada
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorCodigo(String codigo) {
        for(int i = 0; i < listaParadas.size(); i++) {
            if(listaParadas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar una parada por índice
     * @param indice posición en la lista
     * @param p nueva parada
     * @return parada anterior o null si no existe
     */
    public ParadaVO actualizarParada(int indice, ParadaVO p){
        if(indice >= 0 && indice < listaParadas.size()) {
            return listaParadas.set(indice, p);
        }
        return null;
    }

    /**
     * Método que permite actualizar una parada por código
     * @param codigo identificador de la parada
     * @param p nueva parada
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarParadaPorCodigo(String codigo, ParadaVO p){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaParadas.set(indice, p);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar una parada por índice
     * @param indice posición en la lista
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarParada(int indice){
        if(indice >= 0 && indice < listaParadas.size()) {
            listaParadas.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar una parada por código
     * @param codigo identificador de la parada
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarParadaPorCodigo(String codigo){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaParadas.remove(indice);
            return true;
        }
        return false;
    }
}