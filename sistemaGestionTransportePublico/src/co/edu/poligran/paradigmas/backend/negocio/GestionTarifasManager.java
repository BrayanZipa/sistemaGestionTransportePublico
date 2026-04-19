package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.TarifasVO;

/**
 * Clase encargada de gestionar el CRUD de tarifas
 */
public class GestionTarifasManager {

    List<TarifasVO> listaTarifas = new ArrayList<>();

    /**
     * Método que permite agregar tarifas al catálogo
     * @param t objeto de tipo TarifasVO que se va a agregar
     * @return la lista de tarifas
     */
    public List<TarifasVO> agregarTarifa(TarifasVO t){
        listaTarifas.add(t);
        return listaTarifas;
    }

    /**
     * Método que permite obtener todas las tarifas
     * @return lista de tarifas
     */
    public List<TarifasVO> obtenerTarifas(){
        return listaTarifas;
    }

    /**
     * Método que permite buscar una tarifa por código
     * @param codigo identificador de la tarifa
     * @return la tarifa encontrada o null
     */
    public TarifasVO buscarTarifaPorCodigo(int codigo) {
        TarifasVO respuesta = null;
        for(TarifasVO t : listaTarifas) {
            if(t.getCodigo() == codigo) {
                respuesta = t;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Método que permite obtener el estado de una tarifa por código
     * @param codigo identificador de la tarifa
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorCodigo(int codigo) {
        for(int i = 0; i < listaTarifas.size(); i++) {
            if(listaTarifas.get(i).getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar una tarifa por índice
     * @param indice posición en la lista
     * @param t nueva tarifa
     * @return tarifa anterior o null si no existe
     */
    public TarifasVO actualizarTarifa(int indice, TarifasVO t){
        if(indice >= 0 && indice < listaTarifas.size()) {
            return listaTarifas.set(indice, t);
        }
        return null;
    }

    /**
     * Método que permite actualizar una tarifa por código
     * @param codigo identificador de la tarifa
     * @param t nueva tarifa
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarTarifaPorCodigo(int codigo, TarifasVO t){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaTarifas.set(indice, t);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar una tarifa por índice
     * @param indice posición en la lista
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarTarifa(int indice){
        if(indice >= 0 && indice < listaTarifas.size()) {
            listaTarifas.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar una tarifa por código
     * @param codigo identificador de la tarifa
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarTarifaPorCodigo(int codigo){
        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaTarifas.remove(indice);
            return true;
        }
        return false;
    }
}