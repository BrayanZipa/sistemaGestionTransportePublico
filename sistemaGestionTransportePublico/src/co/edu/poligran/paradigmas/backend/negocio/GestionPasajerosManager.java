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
     * @throws IllegalArgumentException si el objeto es nulo o sus datos son inválidos
     */
    public List<PasajeroVO> agregarPasajero(PasajeroVO p) {
        if (p == null) {
            throw new IllegalArgumentException("El pasajero no puede ser nulo.");
        }

        if (p.getIdentificacion() == null || p.getIdentificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
        }

        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

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
     * @throws IllegalArgumentException si la identificación es nula o vacía
     * @throws IllegalStateException si no hay pasajeros registrados
     */
    public PasajeroVO buscarPasajeroPorIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
        }

        if (listaPasajeros.isEmpty()) {
            throw new IllegalStateException("No hay pasajeros registrados.");
        }
        
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
     * @throws IllegalArgumentException si la identificación es nula o vacía
     * @throws IllegalStateException si no hay pasajeros registrados
     */
    public int obtenerIndicePorIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
        }

        if (listaPasajeros.isEmpty()) {
            throw new IllegalStateException("No hay pasajeros registrados.");
        }
        
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
     * @return pasajero anterior
     * @throws IllegalArgumentException si el objeto es inválido
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public PasajeroVO actualizarPasajero(int indice, PasajeroVO p) {
        if (p == null) {
            throw new IllegalArgumentException("El pasajero no puede ser nulo.");
        }

        if (p.getIdentificacion() == null || p.getIdentificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
        }

        if (indice < 0 || indice >= listaPasajeros.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaPasajeros.size()
            );
        }
        return listaPasajeros.set(indice, p);
    }
 
    /**
     * Método que permite actualizar un pasajero por identificación
     * @param identificacion identificador
     * @param p nuevo pasajero
     * @return true si se actualizó
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public boolean actualizarPasajeroPorIdentificacion(String identificacion, PasajeroVO p) {
        if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
        }

        if (p == null) {
            throw new IllegalArgumentException("El pasajero no puede ser nulo.");
        }

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
     * @throws IllegalStateException si no hay pasajeros registrados
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public void eliminarPasajero(int indice) {
        if (listaPasajeros.isEmpty()) {
            throw new IllegalStateException("No hay pasajeros para eliminar.");
        }

        if (indice < 0 || indice >= listaPasajeros.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaPasajeros.size()
            );
        }
        listaPasajeros.remove(indice);
    }
 
    /**
     * Método que permite eliminar un pasajero por identificación
     * @param identificacion identificador
     * @return true si se eliminó, false si no existe
     * @throws IllegalArgumentException si la identificación es inválida
     */
    public boolean eliminarPasajeroPorIdentificacion(String identificacion) {
    	if (identificacion == null || identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
        }
    	
        int indice = obtenerIndicePorIdentificacion(identificacion);
        if (indice != -1) {
            listaPasajeros.remove(indice);
            return true;
        }
        return false;
    }
}
