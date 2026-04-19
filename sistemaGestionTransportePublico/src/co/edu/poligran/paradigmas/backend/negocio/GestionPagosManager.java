package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.PagoVO;

/**
 * Clase encargada de gestionar el CRUD de pagos
 */
public class GestionPagosManager {

    List<PagoVO> listaPagos = new ArrayList<>();

    /**
     * Método que permite agregar pagos al catálogo
     * @param p objeto de tipo PagoVO que se va a agregar
     * @return la lista de pagos
     */
    public List<PagoVO> agregarPago(PagoVO p){
        listaPagos.add(p);
        return listaPagos;
    }

    /**
     * Método que permite obtener todos los pagos
     * @return lista de pagos
     */
    public List<PagoVO> obtenerPagos(){
        return listaPagos;
    }

    /**
     * Método que permite buscar un pago por ID
     * @param idPago identificador del pago
     * @return el pago encontrado o null
     */
    public PagoVO buscarPagoPorId(String idPago) {
        PagoVO respuesta = null;
        for(PagoVO p : listaPagos) {
            if(p.getIdPago().equalsIgnoreCase(idPago)) {
                respuesta = p;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Método que permite obtener el estado de un pago por ID
     * @param idPago identificador del pago
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorId(String idPago) {
        for(int i = 0; i < listaPagos.size(); i++) {
            if(listaPagos.get(i).getIdPago().equalsIgnoreCase(idPago)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar un pago por índice
     * @param indice posición en la lista
     * @param p nuevo pago
     * @return pago anterior o null si no existe
     */
    public PagoVO actualizarPago(int indice, PagoVO p){
        if(indice >= 0 && indice < listaPagos.size()) {
            return listaPagos.set(indice, p);
        }
        return null;
    }

    /**
     * Método que permite actualizar un pago por ID
     * @param idPago identificador del pago
     * @param p nuevo pago
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarPagoPorId(String idPago, PagoVO p){
        int indice = obtenerIndicePorId(idPago);
        if(indice != -1) {
            listaPagos.set(indice, p);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un pago por índice
     * @param indice posición en la lista
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarPago(int indice){
        if(indice >= 0 && indice < listaPagos.size()) {
            listaPagos.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un pago por ID
     * @param idPago identificador del pago
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarPagoPorId(String idPago){
        int indice = obtenerIndicePorId(idPago);
        if(indice != -1) {
            listaPagos.remove(indice);
            return true;
        }
        return false;
    }
}