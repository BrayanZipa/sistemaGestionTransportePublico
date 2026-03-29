package co.edu.poligran.paradigmas.backend.negocio;

import java.util.ArrayList;
import java.util.List;
import co.edu.poligran.paradigmas.backend.vo.VehiculoVO;

/**
 * Clase encargada de gestionar el CRUD de vehículos
 */
public class GestionVehiculosManager {
	
    List<VehiculoVO> listaVehiculos = new ArrayList<>();

    /**
     * Método que permite agregar vehículos al catálogo
     * @param v objeto de tipo VehiculoVO que se va a agregar
     * @return la lista de vehículos
     */
    public List<VehiculoVO> agregarVehiculo(VehiculoVO v){
        listaVehiculos.add(v);
        return listaVehiculos;
    }

    /**
     * Método que permite obtener todos los vehículos
     * @return lista de vehículos
     */
    public List<VehiculoVO> obtenerVehiculos(){
        return listaVehiculos;
    }

    /**
     * Método que permite buscar vehículo por placa
     * @param placa identificador del vehículo
     * @return el vehículo encontrado o null
     */
    public VehiculoVO buscarVehiculoPorPlaca(String placa) {
        VehiculoVO respuesta = null;
        for(VehiculoVO v : listaVehiculos) {
            if(v.getPlaca().equalsIgnoreCase(placa)) {
                respuesta = v;
                break;
            }
        }
        return respuesta;
    }

    /**
     * Método que permite obtener la posición de un vehículo por placa
     * @param placa del vehículo
     * @return índice o -1 si no existe
     */
    public int obtenerIndicePorPlaca(String placa) {
        for(int i = 0; i < listaVehiculos.size(); i++) {
            if(listaVehiculos.get(i).getPlaca().equalsIgnoreCase(placa)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Método que permite actualizar un vehículo por índice
     * @param indice posición
     * @param v nuevo vehículo
     * @return vehículo anterior o null si no existe
     */
    public VehiculoVO actualizarVehiculo(int indice, VehiculoVO v){
        if(indice >= 0 && indice < listaVehiculos.size()) {
            return listaVehiculos.set(indice, v);
        }
        return null;
    }

    /**
     * Método que permite actualizar un vehículo por placa
     * @param placa identificador
     * @param v nuevo vehículo
     * @return true si se actualizó
     */
    public boolean actualizarVehiculoPorPlaca(String placa, VehiculoVO v){
        int indice = obtenerIndicePorPlaca(placa);
        if(indice != -1) {
            listaVehiculos.set(indice, v);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un vehículo por índice
     * @param indice posición
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarVehiculo(int indice){
        if(indice >= 0 && indice < listaVehiculos.size()) {
            listaVehiculos.remove(indice);
            return true;
        }
        return false;
    }

    /**
     * Método que permite eliminar un vehículo por placa
     * @param placa identificador
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarVehiculoPorPlaca(String placa){
        int indice = obtenerIndicePorPlaca(placa);
        if(indice != -1) {
            listaVehiculos.remove(indice);
            return true;
        }
        return false;
    }
    
    /**
     * Método que permite cambiar el estado de disponibilidad de un vehículo
     * @param placa identificador del vehículo
     * @param estado nuevo estado (true = disponible, false = no disponible)
     * @return true si se actualizó correctamente, false si no se encontró
     */
    public boolean cambiarDisponibilidad(String placa, boolean estado) {
        VehiculoVO vehiculo = buscarVehiculoPorPlaca(placa);
        
        if (vehiculo != null) {
            vehiculo.setEstadoDisponibilidad(estado);
            return true;
        }
        
        return false;
    }
}