package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;

import co.edu.poligran.paradigmas.backend.vo.ConductorVO;

public class GestionConductoresManager {

    List<ConductorVO> listaConductores = new ArrayList<>();

    // Método que permite crear un nuevo conductor
    public List<ConductorVO> agregarConductor(ConductorVO c){
        listaConductores.add(c);
        return listaConductores;
    }

    // Método que permite visualizar los conductores registrados
    public List<ConductorVO> obtenerConductores(){
        return listaConductores;
    }

    public ConductorVO buscarConductorPorId(String identificacion){

        for(ConductorVO c : listaConductores){
            if(c.getIdentificacion().equalsIgnoreCase(identificacion)){
                return c;
            }
        }
        return null;
    }

    public int obtenerIndicePorId(String identificacion){

        for(int i = 0; i < listaConductores.size(); i++){
            if(listaConductores.get(i).getIdentificacion()
                    .equalsIgnoreCase(identificacion)){
                return i;
            }
        }
        return -1;
    }

    // Método para actualizar informción del conductor
    public boolean actualizarConductor(String identificacion, ConductorVO nuevo){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaConductores.set(indice, nuevo);
            return true;
        }

        return false;
    }

    // Método para eliminar un conductor
    public boolean eliminarConductor(String identificacion){

        int indice = obtenerIndicePorId(identificacion);

        if(indice != -1){
            listaConductores.remove(indice);
            return true;
        }

        return false;
    }
}
