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
     * @throws IllegalArgumentException si el objeto es nulo o su código está vacío
     */
    public List<ParadaVO> agregarParada(ParadaVO p){
        if (p == null) {
            throw new IllegalArgumentException("La parada no puede ser nula.");
        }
        
        if (p.getCodigo() == null || p.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la parada no puede ser nulo o vacío.");
        }
        
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
     * @throws IllegalArgumentException si el código es nulo o vacío
     * @throws IllegalStateException si la lista de objetos está vacía
     */
    public ParadaVO buscarParadaPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de búsqueda no puede ser nulo o vacío.");
        }
        
        if (listaParadas.isEmpty()) {
            throw new IllegalStateException("No hay paradas registradas.");
        }

        for(ParadaVO p : listaParadas) {
            if(p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de una parada por código
     * @param codigo identificador de la parada
     * @return índice o -1 si no existe
     * @throws IllegalArgumentException si el código es nulo o vacío
     * @throws IllegalStateException si la lista de objetos está vacía
     */
    public int obtenerIndicePorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo o vacío.");
        }
        
        if (listaParadas.isEmpty()) {
            throw new IllegalStateException("No hay paradas registradas.");
        }

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
     * @throws IllegalArgumentException si el objeto es nulo o su código está vacío
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la lista
     */
    public ParadaVO actualizarParada(int indice, ParadaVO p){
        if (p == null) {
            throw new IllegalArgumentException("La parada no puede ser nula.");
        }
        
        if (p.getCodigo() == null || p.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la parada no puede ser nulo o vacío.");
        }
        
        if (indice < 0 || indice >= listaParadas.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaParadas.size()
            );
        }

        return listaParadas.set(indice, p);
    }

    /**
     * Método que permite actualizar una parada por código
     * @param codigo identificador de la parada
     * @param p nueva parada
     * @return true si se actualizó correctamente, false si no existe
     * @throws IllegalArgumentException si el objeto es nulo o su código está vacío
     */
    public boolean actualizarParadaPorCodigo(String codigo, ParadaVO p){    	
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo o vacío.");
        }
        
        if (p == null) {
            throw new IllegalArgumentException("La parada no puede ser nula.");
        }

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
     * @throws IllegalStateException si la lista de objetos está vacía
     * @throws IndexOutOfBoundsException si el índice está fuera del rango de la lista
     */
    public void eliminarParada(int indice){
        if (listaParadas.isEmpty()) {
            throw new IllegalStateException("No hay paradas para eliminar.");
        }
        
        if (indice < 0 || indice >= listaParadas.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaParadas.size()
            );
        }
        listaParadas.remove(indice);
    }

    /**
     * Método que permite eliminar una parada por código
     * @param codigo identificador de la parada
     * @return true si se eliminó, false si no existe
     * @throws IllegalArgumentException si el código está vacío
     */
    public boolean eliminarParadaPorCodigo(String codigo){
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo o vacío.");
        }

        int indice = obtenerIndicePorCodigo(codigo);
        if(indice != -1) {
            listaParadas.remove(indice);
            return true;
        }
        return false;
    }
}