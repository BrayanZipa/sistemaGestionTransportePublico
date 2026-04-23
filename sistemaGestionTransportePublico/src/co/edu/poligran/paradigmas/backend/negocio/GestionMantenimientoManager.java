package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.MantenimientoVO;

/**
 * Clase encargada de gestionar el CRUD de mantenimientos
 */
public class GestionMantenimientoManager {

	List<MantenimientoVO> listaMantenimientos = new ArrayList<>();

	/**
	 * Método que permite agregar mantenimientos al catálogo
	 * 
	 * @param m objeto de tipo MantenimientoVO que se va a agregar
	 * @return la lista de mantenimientos
	 * @throws IllegalArgumentException si el objeto es nulo o su ID está vacío
	 */
	public List<MantenimientoVO> agregarMantenimiento(MantenimientoVO m) {
		if (m == null) {
			throw new IllegalArgumentException("El mantenimiento no puede ser nulo.");
		}

		if (m.getIdMantenimiento() == null || m.getIdMantenimiento().trim().isEmpty()) {
			throw new IllegalArgumentException("El ID del mantenimiento no puede ser nulo o vacío.");
		}

		listaMantenimientos.add(m);
		return listaMantenimientos;
	}

	/**
	 * Método que permite obtener todos los mantenimientos
	 * 
	 * @return lista de mantenimientos
	 */
	public List<MantenimientoVO> obtenerMantenimientos() {
		return listaMantenimientos;
	}

	/**
	 * Método que permite buscar mantenimiento por ID
	 * 
	 * @param idMantenimiento identificador del mantenimiento
	 * @return el mantenimiento encontrado o null
	 * @throws IllegalArgumentException si el ID es nulo o vacío
	 * @throws IllegalStateException    si no hay mantenimientos registrados
	 */
	public MantenimientoVO buscarMantenimientoPorId(String idMantenimiento) {
		if (idMantenimiento == null || idMantenimiento.trim().isEmpty()) {
			throw new IllegalArgumentException("El ID de búsqueda no puede ser nulo o vacío.");
		}

		if (listaMantenimientos.isEmpty()) {
			throw new IllegalStateException("No hay mantenimientos registrados.");
		}

		for (MantenimientoVO m : listaMantenimientos) {
			if (m.getIdMantenimiento().equalsIgnoreCase(idMantenimiento)) {
				return m;
			}
		}
		return null;
	}

	/**
	 * Método que permite obtener el índice de un mantenimiento por ID
	 * 
	 * @param idMantenimiento identificador del mantenimiento
	 * @return índice o -1 si no existe
	 * @throws IllegalArgumentException si el ID es nulo o vacío
	 * @throws IllegalStateException    si no hay mantenimientos registrados
	 */
	public int obtenerIndicePorId(String idMantenimiento) {
		if (idMantenimiento == null || idMantenimiento.trim().isEmpty()) {
			throw new IllegalArgumentException("El ID no puede ser nulo o vacío.");
		}

		if (listaMantenimientos.isEmpty()) {
			throw new IllegalStateException("No hay mantenimientos registrados.");
		}

		for (int i = 0; i < listaMantenimientos.size(); i++) {
			if (listaMantenimientos.get(i).getIdMantenimiento().equalsIgnoreCase(idMantenimiento)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Método que permite actualizar un mantenimiento por índice
	 * 
	 * @param indice posición en la lista
	 * @param m      nuevo mantenimiento
	 * @return mantenimiento anterior
	 * @throws IllegalArgumentException  si el objeto es nulo o su ID está vacío
	 * @throws IndexOutOfBoundsException si el índice es inválido
	 */
	public MantenimientoVO actualizarMantenimiento(int indice, MantenimientoVO m) {
		if (m == null) {
			throw new IllegalArgumentException("El mantenimiento no puede ser nulo.");
		}

		if (m.getIdMantenimiento() == null || m.getIdMantenimiento().trim().isEmpty()) {
			throw new IllegalArgumentException("El ID del mantenimiento no puede ser nulo o vacío.");
		}

		if (indice < 0 || indice >= listaMantenimientos.size()) {
			throw new IndexOutOfBoundsException(
					"Índice fuera de rango: " + indice + ". Total: " + listaMantenimientos.size());
		}

		return listaMantenimientos.set(indice, m);
	}

	/**
	 * Método que permite actualizar un mantenimiento por ID
	 * 
	 * @param idMantenimiento identificador del mantenimiento
	 * @param m               nuevo mantenimiento
	 * @return true si se actualizó correctamente, false si no existe
	 * @throws IllegalArgumentException si el ID es nulo o vacío o el objeto es nulo
	 */
	public boolean actualizarMantenimientoPorId(String idMantenimiento, MantenimientoVO m) {
		if (idMantenimiento == null || idMantenimiento.trim().isEmpty()) {
			throw new IllegalArgumentException("El ID no puede ser nulo o vacío.");
		}

		if (m == null) {
			throw new IllegalArgumentException("El mantenimiento no puede ser nulo.");
		}

		int indice = obtenerIndicePorId(idMantenimiento);
		if (indice != -1) {
			listaMantenimientos.set(indice, m);
			return true;
		}
		return false;
	}

	/**
	 * Método que permite eliminar un mantenimiento por índice
	 * 
	 * @param indice posición en la lista
	 * @throws IllegalStateException     si no hay mantenimientos registrados
	 * @throws IndexOutOfBoundsException si el índice es inválido
	 */
	public void eliminarMantenimiento(int indice) {
		if (listaMantenimientos.isEmpty()) {
			throw new IllegalStateException("No hay mantenimientos para eliminar.");
		}

		if (indice < 0 || indice >= listaMantenimientos.size()) {
			throw new IndexOutOfBoundsException(
					"Índice fuera de rango: " + indice + ". Total: " + listaMantenimientos.size());
		}

		listaMantenimientos.remove(indice);
	}

	/**
	 * Método que permite eliminar un mantenimiento por ID
	 * 
	 * @param idMantenimiento identificador del mantenimiento
	 * @return true si se eliminó, false si no existe
	 * @throws IllegalArgumentException si el ID es nulo o vacío
	 */
	public boolean eliminarMantenimientoPorId(String idMantenimiento) {
		if (idMantenimiento == null || idMantenimiento.trim().isEmpty()) {
			throw new IllegalArgumentException("El ID no puede ser nulo o vacío.");
		}

		int indice = obtenerIndicePorId(idMantenimiento);
		if (indice != -1) {
			listaMantenimientos.remove(indice);
			return true;
		}
		return false;
	}
}