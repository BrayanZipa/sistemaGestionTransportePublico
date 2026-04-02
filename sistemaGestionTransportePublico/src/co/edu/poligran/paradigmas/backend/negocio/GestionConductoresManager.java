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
     * @param c objeto de tipo ConductorVO que se va a agregar
     * @return la lista de conductores
     */
    public List<ConductorVO> agregarConductor(ConductorVO c){
        listaConductores.add(c);
        return listaConductores;
    }

    /**
     * Método que permite obtener todos los conductores
     * @return lista de conductores
     */
    public List<ConductorVO> obtenerConductores(){
        return listaConductores;
    }

    /**
     * Método que permite buscar un conductor por identificación
     * @param identificacion identificador del conductor
     * @return el conductor encontrado o null si no existe
     */
    public ConductorVO buscarConductorPorId(String identificacion){

        for(ConductorVO c : listaConductores){
            if(c.getIdentificacion().equalsIgnoreCase(identificacion)){
                return c;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de un conductor por identificación
     * @param identificacion del conductor
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorId(String identificacion){

        for(int i = 0; i < listaConductores.size(); i++){
            if(listaConductores.get(i).getIdentificacion()
                    .equalsIgnoreCase(identificacion)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar un conductor por identificación
     * @param identificacion identificador del conductor
     * @param nuevo nuevo objeto ConductorVO con la información actualizada
     * @return true si se actualizó correctamente, false si no existe
     */
    public boolean actualizarConductor(String identificacion, ConductorVO nuevo){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaConductores.set(indice, nuevo);
            return true;
        }

        return false;
    }

    /**
     * Método que permite eliminar un conductor por identificación
     * @param identificacion identificador del conductor
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean eliminarConductor(String identificacion){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaConductores.remove(indice);
            return true;
        }

        return false;
    }



}
