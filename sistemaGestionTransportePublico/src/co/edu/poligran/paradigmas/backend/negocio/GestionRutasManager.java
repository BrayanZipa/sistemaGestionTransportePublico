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
     * @throws IllegalArgumentException si el objeto es inválido
     */
    public List<RutaVO> agregarRuta(RutaVO r){
        if (r == null) {
            throw new IllegalArgumentException("La ruta no puede ser nula.");
        }

        if (r.getCodigo() <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (r.getOrigen() == null || r.getDestino() == null) {
            throw new IllegalArgumentException("La ruta debe tener origen y destino.");
        }

        if (r.getDistancia() <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a cero.");
        }

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
     * @throws IllegalArgumentException si el código es inválido
     * @throws IllegalStateException si no hay rutas registradas
     */
    public RutaVO buscarRutaPorCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (listaRutas.isEmpty()) {
            throw new IllegalStateException("No hay rutas registradas.");
        }

        for(RutaVO r : listaRutas) {
            if(r.getCodigo() == codigo) {
                return r;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de una ruta por código
     * @param codigo identificador de la ruta
     * @return índice o -1 si no existe
     * @throws IllegalArgumentException si el código es inválido
     * @throws IllegalStateException si no hay rutas registradas
     */
    public int obtenerIndicePorCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (listaRutas.isEmpty()) {
            throw new IllegalStateException("No hay rutas registradas.");
        }

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
     * @return ruta anterior
     * @throws IllegalArgumentException si el objeto es inválido
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public RutaVO actualizarRuta(int indice, RutaVO r){
        if (r == null) {
            throw new IllegalArgumentException("La ruta no puede ser nula.");
        }

        if (r.getCodigo() <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (indice < 0 || indice >= listaRutas.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaRutas.size()
            );
        }
        return listaRutas.set(indice, r);
    }

    /**
     * Método que permite actualizar una ruta por código
     * @param codigo identificador de la ruta
     * @param r nueva ruta
     * @return true si se actualizó correctamente, false si no existe
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public boolean actualizarRutaPorCodigo(int codigo, RutaVO r){
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        if (r == null) {
            throw new IllegalArgumentException("La ruta no puede ser nula.");
        }

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
     * @throws IllegalStateException si no hay rutas registradas
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public void eliminarRuta(int indice){
        if (listaRutas.isEmpty()) {
            throw new IllegalStateException("No hay rutas para eliminar.");
        }

        if(indice < 0 || indice >= listaRutas.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaRutas.size()
            );
        }
        listaRutas.remove(indice);
    }

    /**
     * Método que permite eliminar una ruta por código
     * @param codigo identificador de la ruta
     * @return true si se eliminó, false si no existe
     * @throws IllegalArgumentException si el código es inválido
     */
    public boolean eliminarRutaPorCodigo(int codigo){
        if (codigo <= 0) {
            throw new IllegalArgumentException("El código debe ser mayor a cero.");
        }

        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaRutas.remove(indice);
            return true;
        }
        return false;
    }
}