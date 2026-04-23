package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;

import co.edu.poligran.paradigmas.backend.vo.ConductorVO;

/**
 * Clase encargada de gestionar el CRUD de conductores
 */
public class GestionConductoresManager {

	private List<ConductorVO> listaConductores = new ArrayList<>();

	/**
	 * Método que permite agregar conductores al catálogo
	 * 
	 * @param c objeto de tipo ConductorVO que se va a agregar
	 * @return la lista de conductores
	 * @throws IllegalArgumentException si el objeto es nulo o sus datos son
	 *                                  inválidos
	 */
	public List<ConductorVO> agregarConductor(ConductorVO c) {
		if (c == null) {
			throw new IllegalArgumentException("El conductor no puede ser nulo.");
		}

		if (c.getIdentificacion() == null || c.getIdentificacion().trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
		}

		listaConductores.add(c);
		return listaConductores;
	}

	/**
	 * Método que permite obtener todos los conductores
	 * 
	 * @return lista de conductores
	 */
	public List<ConductorVO> obtenerConductores() {
		return listaConductores;
	}

	/**
	 * Método que permite buscar un conductor por identificación
	 * 
	 * @param identificacion identificador del conductor
	 * @return el conductor encontrado o null si no existe
	 * @throws IllegalArgumentException si la identificación es nula o vacía
	 * @throws IllegalStateException    si no hay conductores registrados
	 */
	public ConductorVO buscarConductorPorId(String identificacion) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (listaConductores.isEmpty()) {
			throw new IllegalStateException("No hay conductores registrados.");
		}

		for (ConductorVO c : listaConductores) {
			if (c.getIdentificacion().equalsIgnoreCase(identificacion)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Método que permite obtener la posición de un conductor por identificación
	 * 
	 * @param identificacion del conductor
	 * @return índice o -1 si no existe
	 * @throws IllegalArgumentException si la identificación es nula o vacía
	 * @throws IllegalStateException    si no hay conductores registrados
	 */
	public int obtenerIndicePorId(String identificacion) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (listaConductores.isEmpty()) {
			throw new IllegalStateException("No hay conductores registrados.");
		}

		for (int i = 0; i < listaConductores.size(); i++) {
			if (listaConductores.get(i).getIdentificacion().equalsIgnoreCase(identificacion)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Método que permite actualizar un conductor por índice
	 * 
	 * @param indice posición en la lista
	 * @param c      nuevo conductor
	 * @return conductor anterior
	 * @throws IllegalArgumentException  si el objeto es inválido
	 * @throws IndexOutOfBoundsException si el índice es inválido
	 */
	public ConductorVO actualizarConductor(int indice, ConductorVO c) {
		if (c == null) {
			throw new IllegalArgumentException("El conductor no puede ser nulo.");
		}

		if (c.getIdentificacion() == null || c.getIdentificacion().trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (indice < 0 || indice >= listaConductores.size()) {
			throw new IndexOutOfBoundsException(
					"Índice fuera de rango: " + indice + ". Total: " + listaConductores.size());
		}

		return listaConductores.set(indice, c);
	}

	/**
	 * Método que permite actualizar un conductor por identificación
	 * 
	 * @param identificacion identificador del conductor
	 * @param nuevo          nuevo objeto ConductorVO con la información actualizada
	 * @return true si se actualizó correctamente, false si no existe
	 * @throws IllegalArgumentException si los datos son inválidos
	 */
	public boolean actualizarConductor(String identificacion, ConductorVO nuevo) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (nuevo == null) {
			throw new IllegalArgumentException("El conductor no puede ser nulo.");
		}

		int indice = obtenerIndicePorId(identificacion);

		if (indice != -1) {
			nuevo.setIdentificacion(identificacion);
			listaConductores.set(indice, nuevo);
			return true;
		}

		return false;
	}

	/**
	 * Método que permite eliminar un conductor por índice
	 * 
	 * @param indice posición en la lista
	 * @throws IllegalStateException     si no hay conductores registrados
	 * @throws IndexOutOfBoundsException si el índice es inválido
	 */
	public void eliminarConductor(int indice) {
		if (listaConductores.isEmpty()) {
			throw new IllegalStateException("No hay conductores para eliminar.");
		}

		if (indice < 0 || indice >= listaConductores.size()) {
			throw new IndexOutOfBoundsException(
					"Índice fuera de rango: " + indice + ". Total: " + listaConductores.size());
		}

		listaConductores.remove(indice);
	}

	/**
	 * Método que permite eliminar un conductor por identificación
	 * 
	 * @param identificacion identificador del conductor
	 * @return true si se eliminó correctamente, false si no existe
	 * @throws IllegalArgumentException si la identificación es inválida
	 */
	public boolean eliminarConductor(String identificacion) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		int indice = obtenerIndicePorId(identificacion);

		if (indice != -1) {
			listaConductores.remove(indice);
			return true;
		}

		return false;
	}
}
