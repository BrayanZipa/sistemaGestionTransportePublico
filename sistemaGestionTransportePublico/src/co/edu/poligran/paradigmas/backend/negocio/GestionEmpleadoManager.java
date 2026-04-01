package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;

import co.edu.poligran.paradigmas.backend.vo.EmpleadoVO;

public class GestionEmpleadoManager {

    List<EmpleadoVO> listaEmpleados = new ArrayList<>();

    // Método que permite agregar empleados 
    public List<EmpleadoVO> agregarEmpleado(EmpleadoVO e){
        listaEmpleados.add(e);
        return listaEmpleados;
    }

    // Método que permite visualizar todos los empleados
    public List<EmpleadoVO> obtenerEmpleados(){
        return listaEmpleados;
    }

    public EmpleadoVO buscarEmpleadoPorId(String identificacion) {

        for(EmpleadoVO e : listaEmpleados) {
            if(e.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return e;
            }
        }
        return null;
    }

    public int obtenerIndicePorId(String identificacion) {

        for(int i = 0; i < listaEmpleados.size(); i++) {
            if(listaEmpleados.get(i).getIdentificacion()
                    .equalsIgnoreCase(identificacion)) {
                return i;
            }
        }
        return -1;
    }

    // Método que nos permite actualizar la información de los empleados
    public boolean actualizarEmpleado(String identificacion, EmpleadoVO nuevo){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaEmpleados.set(indice, nuevo);
            return true;
        }

        return false;
    }

    // Método que permite eliminar empleados
    public boolean eliminarEmpleado(String identificacion){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaEmpleados.remove(indice);
            return true;
        }

        return false;
    }
}
