package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;

import co.edu.poligran.paradigmas.backend.vo.EmpleadoVO;

/**
 * Clase encargada de gestionar el CRUD de empleados
 */
public class GestionEmpleadosManager {

	private List<EmpleadoVO> listaEmpleados = new ArrayList<>();

	/**
	 * Método que permite agregar empleados al catálogo
	 * 
	 * @param e objeto de tipo EmpleadoVO que se va a agregar
	 * @return la lista de empleados
	 * @throws IllegalArgumentException si el objeto es nulo o sus datos son
	 *                                  inválidos
	 */
	public List<EmpleadoVO> agregarEmpleado(EmpleadoVO e) {
		if (e == null) {
			throw new IllegalArgumentException("El empleado no puede ser nulo.");
		}

		if (e.getIdentificacion() == null || e.getIdentificacion().trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (e.getNombre() == null || e.getNombre().trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
		}

		listaEmpleados.add(e);
		return listaEmpleados;
	}

	/**
	 * Método que permite obtener todos los empleados
	 * 
	 * @return lista de empleados
	 */
	public List<EmpleadoVO> obtenerEmpleados() {
		return listaEmpleados;
	}

	/**
	 * Método que permite buscar un empleado por identificación
	 * 
	 * @param identificacion identificador del empleado
	 * @return el empleado encontrado o null si no existe
	 * @throws IllegalArgumentException si la identificación es nula o vacía
	 * @throws IllegalStateException    si no hay empleados registrados
	 */
	public EmpleadoVO buscarEmpleadoPorId(String identificacion) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (listaEmpleados.isEmpty()) {
			throw new IllegalStateException("No hay empleados registrados.");
		}

		for (EmpleadoVO e : listaEmpleados) {
			if (e.getIdentificacion().equalsIgnoreCase(identificacion)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Método que permite obtener la posición de un empleado por identificación
	 * 
	 * @param identificacion del empleado
	 * @return índice o -1 si no existe
	 * @throws IllegalArgumentException si la identificación es nula o vacía
	 * @throws IllegalStateException    si no hay empleados registrados
	 */
	public int obtenerIndicePorId(String identificacion) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (listaEmpleados.isEmpty()) {
			throw new IllegalStateException("No hay empleados registrados.");
		}

		for (int i = 0; i < listaEmpleados.size(); i++) {
			if (listaEmpleados.get(i).getIdentificacion().equalsIgnoreCase(identificacion)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Método que permite actualizar un empleado por índice
	 * 
	 * @param indice posición en la lista
	 * @param e      nuevo empleado
	 * @return empleado anterior
	 * @throws IllegalArgumentException  si el objeto es inválido
	 * @throws IndexOutOfBoundsException si el índice es inválido
	 */
	public EmpleadoVO actualizarEmpleado(int indice, EmpleadoVO e) {
		if (e == null) {
			throw new IllegalArgumentException("El empleado no puede ser nulo.");
		}

		if (e.getIdentificacion() == null || e.getIdentificacion().trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (indice < 0 || indice >= listaEmpleados.size()) {
			throw new IndexOutOfBoundsException(
					"Índice fuera de rango: " + indice + ". Total: " + listaEmpleados.size());
		}

		return listaEmpleados.set(indice, e);
	}

	/**
	 * Método que permite actualizar un empleado por identificación
	 * 
	 * @param identificacion identificador del empleado
	 * @param nuevo          nuevo objeto EmpleadoVO con la información actualizada
	 * @return true si se actualizó correctamente, false si no existe
	 * @throws IllegalArgumentException si los datos son inválidos
	 */
	public boolean actualizarEmpleado(String identificacion, EmpleadoVO nuevo) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		if (nuevo == null) {
			throw new IllegalArgumentException("El empleado no puede ser nulo.");
		}

		int indice = obtenerIndicePorId(identificacion);

		if (indice != -1) {
			nuevo.setIdentificacion(identificacion);
			listaEmpleados.set(indice, nuevo);
			return true;
		}

		return false;
	}

	/**
	 * Método que permite eliminar un empleado por índice
	 * 
	 * @param indice posición en la lista
	 * @throws IllegalStateException     si no hay empleados registrados
	 * @throws IndexOutOfBoundsException si el índice es inválido
	 */
	public void eliminarEmpleado(int indice) {
		if (listaEmpleados.isEmpty()) {
			throw new IllegalStateException("No hay empleados para eliminar.");
		}

		if (indice < 0 || indice >= listaEmpleados.size()) {
			throw new IndexOutOfBoundsException(
					"Índice fuera de rango: " + indice + ". Total: " + listaEmpleados.size());
		}

		listaEmpleados.remove(indice);
	}

	/**
	 * Método que permite eliminar un empleado por identificación
	 * 
	 * @param identificacion identificador del empleado
	 * @return true si se eliminó correctamente, false si no existe
	 * @throws IllegalArgumentException si la identificación es inválida
	 */
	public boolean eliminarEmpleado(String identificacion) {

		if (identificacion == null || identificacion.trim().isEmpty()) {
			throw new IllegalArgumentException("La identificación no puede ser nula o vacía.");
		}

		int indice = obtenerIndicePorId(identificacion);

		if (indice != -1) {
			listaEmpleados.remove(indice);
			return true;
		}

		return false;
	}

}