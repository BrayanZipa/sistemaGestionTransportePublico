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
     * @throws IllegalArgumentException si el objeto es inválido
     */
    public List<TarifasVO> agregarTarifa(TarifasVO t){
        if (t == null) {
            throw new IllegalArgumentException("La tarifa no puede ser nula.");
        }

        if (t.getCodigo() <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (t.getValor() <= 0) {
            throw new IllegalArgumentException("El valor de la tarifa debe ser mayor a cero.");
        }

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
     * @throws IllegalArgumentException si el código es inválido
     * @throws IllegalStateException si no hay tarifas registradas
     */
    public TarifasVO buscarTarifaPorCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (listaTarifas.isEmpty()) {
            throw new IllegalStateException("No hay tarifas registradas.");
        }

        for(TarifasVO t : listaTarifas) {
            if(t.getCodigo() == codigo) {
                return t;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de una tarifa por código
     * @param codigo identificador de la tarifa
     * @return índice o -1 si no existe
     * @throws IllegalArgumentException si el código es inválido
     * @throws IllegalStateException si no hay tarifas registradas
     */
    public int obtenerIndicePorCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (listaTarifas.isEmpty()) {
            throw new IllegalStateException("No hay tarifas registradas.");
        }

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
     * @return tarifa anterior
     * @throws IllegalArgumentException si el objeto es inválido
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public TarifasVO actualizarTarifa(int indice, TarifasVO t){
        if (t == null) {
            throw new IllegalArgumentException("La tarifa no puede ser nula.");
        }

        if (t.getCodigo() <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (indice < 0 || indice >= listaTarifas.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaTarifas.size()
            );
        }

        return listaTarifas.set(indice, t);
    }

    /**
     * Método que permite actualizar una tarifa por código
     * @param codigo identificador de la tarifa
     * @param t nueva tarifa
     * @return true si se actualizó correctamente, false si no existe
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public boolean actualizarTarifaPorCodigo(int codigo, TarifasVO t){
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (t == null) {
            throw new IllegalArgumentException("La tarifa no puede ser nula.");
        }

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
     * @throws IllegalStateException si no hay tarifas registradas
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public void eliminarTarifa(int indice){
        if (listaTarifas.isEmpty()) {
            throw new IllegalStateException("No hay tarifas para eliminar.");
        }

        if(indice < 0 || indice >= listaTarifas.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaTarifas.size()
            );
        }

        listaTarifas.remove(indice);
    }

    /**
     * Método que permite eliminar una tarifa por código
     * @param codigo identificador de la tarifa
     * @return true si se eliminó, false si no existe
     * @throws IllegalArgumentException si el código es inválido
     */
    public boolean eliminarTarifaPorCodigo(int codigo){
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        int indice = obtenerIndicePorCodigo(codigo);

        if(indice != -1) {
            listaTarifas.remove(indice);
            return true;
        }
        return false;
    }
}