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
     * @param e objeto de tipo EmpleadoVO que se va a agregar
     * @return la lista de empleados
     */
    public List<EmpleadoVO> agregarEmpleado(EmpleadoVO e){
        listaEmpleados.add(e);
        return listaEmpleados;
    }

    /**
     * Método que permite obtener todos los empleados
     * @return lista de empleados
     */
    public List<EmpleadoVO> obtenerEmpleados(){
        return listaEmpleados;
    }

    /**
     * Método que permite buscar un empleado por identificación
     * @param identificacion identificador del empleado
     * @return el empleado encontrado o null si no existe
     */
    public EmpleadoVO buscarEmpleadoPorId(String identificacion) {

        for(EmpleadoVO e : listaEmpleados) {
            if(e.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de un empleado por identificación
     * @param identificacion del empleado
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorId(String identificacion) {

        for(int i = 0; i < listaEmpleados.size(); i++) {
            if(listaEmpleados.get(i).getIdentificacion()
                    .equalsIgnoreCase(identificacion)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar un empleado por identificación
     * @param identificacion identificador del empleado
     * @param nuevo nuevo objeto EmpleadoVO con la información actualizada
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarEmpleado(String identificacion, EmpleadoVO nuevo){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaEmpleados.set(indice, nuevo);
            return true;
        }

        return false;
    }

    /**
     * Método que permite eliminar un empleado por identificación
     * @param identificacion identificador del empleado
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminarEmpleado(String identificacion){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaEmpleados.remove(indice);
            return true;
        }

        return false;
    }
}