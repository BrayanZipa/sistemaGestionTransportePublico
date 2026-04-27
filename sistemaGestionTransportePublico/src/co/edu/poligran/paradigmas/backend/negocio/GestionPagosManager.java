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
     * @throws IllegalArgumentException si el objeto es nulo o sus datos son inválidos
     */
    public List<PagoVO> agregarPago(PagoVO p){
        if (p == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo.");
        }

        if (p.getIdPago() == null || p.getIdPago().trim().isEmpty()) {
            throw new IllegalArgumentException("El Id del pago no puede ser nulo o vacío.");
        }

        if (p.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero.");
        }

        if (p.getMetodoPago() == null || p.getMetodoPago().trim().isEmpty()) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo o vacío.");
        }

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
     * @throws IllegalArgumentException si el ID es nulo o vacío
     * @throws IllegalStateException si no hay pagos registrados
     */
    public PagoVO buscarPagoPorId(String idPago) {
        if (idPago == null || idPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del pago no puede ser nulo o vacío.");
        }

        if (listaPagos.isEmpty()) {
            throw new IllegalStateException("No hay pagos registrados.");
        }

        for(PagoVO p : listaPagos) {
            if(p.getIdPago().equalsIgnoreCase(idPago)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de un pago por ID
     * @param idPago identificador del pago
     * @return índice o -1 si no existe
     * @throws IllegalArgumentException si el ID es inválido
     * @throws IllegalStateException si no hay pagos registrados
     */
    public int obtenerIndicePorId(String idPago) {
        if (idPago == null || idPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El Id del pago no puede ser nulo o vacío.");
        }

        if (listaPagos.isEmpty()) {
            throw new IllegalStateException("No hay pagos registrados.");
        }

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
     * @return pago anterior
     * @throws IllegalArgumentException si el objeto es inválido
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public PagoVO actualizarPago(int indice, PagoVO p){
        if (p == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo.");
        }

        if (p.getIdPago() == null || p.getIdPago().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del pago no puede ser nulo o vacío.");
        }

        if (indice < 0 || indice >= listaPagos.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaPagos.size()
            );
        }

        return listaPagos.set(indice, p);
    }

    /**
     * Método que permite actualizar un pago por ID
     * @param idPago identificador del pago
     * @param p nuevo pago
     * @return true si se actualizó correctamente, false si no existe
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public boolean actualizarPagoPorId(String idPago, PagoVO p){
        if (idPago == null || idPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El Id del pago no puede ser nulo o vacío.");
        }

        if (p == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo.");
        }

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
     * @throws IllegalStateException si no hay pagos registrados
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public void eliminarPago(int indice){
        if (listaPagos.isEmpty()) {
            throw new IllegalStateException("No hay pagos para eliminar.");
        }

        if(indice < 0 || indice >= listaPagos.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaPagos.size()
            );
        }

        listaPagos.remove(indice);
    }

    /**
     * Método que permite eliminar un pago por ID
     * @param idPago identificador del pago
     * @return true si se eliminó, false si no existe
     * @throws IllegalArgumentException si el ID es inválido
     */
    public boolean eliminarPagoPorId(String idPago){
        if (idPago == null || idPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del pago no puede ser nulo o vacío.");
        }

        int indice = obtenerIndicePorId(idPago);

        if(indice != -1) {
            listaPagos.remove(indice);
            return true;
        }
        return false;
    }
}