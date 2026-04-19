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
     * @throws IllegalArgumentException si el objeto es nulo o sus datos son inválidos
     */
    public List<VehiculoVO> agregarVehiculo(VehiculoVO v){
        if (v == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }

        if (v.getPlaca() == null || v.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }

        if (v.getModelo() == null || v.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede ser nulo o vacío.");
        }

        if (v.getCapacidadPasajeros() <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a cero.");
        }

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
     * @throws IllegalArgumentException si la placa es inválida
     * @throws IllegalStateException si no hay vehículos registrados
     */
    public VehiculoVO buscarVehiculoPorPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }

        if (listaVehiculos.isEmpty()) {
            throw new IllegalStateException("No hay vehículos registrados.");
        }

        for(VehiculoVO v : listaVehiculos) {
            if(v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Método que permite obtener la posición de un vehículo por placa
     * @param placa del vehículo
     * @return índice o -1 si no existe
     * @throws IllegalArgumentException si la placa es inválida
     * @throws IllegalStateException si no hay vehículos registrados
     */
    public int obtenerIndicePorPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }

        if (listaVehiculos.isEmpty()) {
            throw new IllegalStateException("No hay vehículos registrados.");
        }

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
     * @return vehículo anterior
     * @throws IllegalArgumentException si el objeto es inválido
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public VehiculoVO actualizarVehiculo(int indice, VehiculoVO v){
        if (v == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }

        if (v.getPlaca() == null || v.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }

        if (indice < 0 || indice >= listaVehiculos.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaVehiculos.size()
            );
        }
        return listaVehiculos.set(indice, v);
    }

    /**
     * Método que permite actualizar un vehículo por placa
     * @param placa identificador
     * @param v nuevo vehículo
     * @return true si se actualizó
     * @throws IllegalArgumentException si los datos son inválidos
     */
    public boolean actualizarVehiculoPorPlaca(String placa, VehiculoVO v){
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }

        if (v == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo.");
        }

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
     * @throws IllegalStateException si no hay vehículos registrados
     * @throws IndexOutOfBoundsException si el índice es inválido
     */
    public void eliminarVehiculo(int indice){
        if (listaVehiculos.isEmpty()) {
            throw new IllegalStateException("No hay vehículos para eliminar.");
        }

        if(indice < 0 || indice >= listaVehiculos.size()) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice + ". Total: " + listaVehiculos.size()
            );
        }
        listaVehiculos.remove(indice);
    }

    /**
     * Método que permite eliminar un vehículo por placa
     * @param placa identificador
     * @return true si se eliminó, false si no existe
     * @throws IllegalArgumentException si la placa es inválida
     */
    public boolean eliminarVehiculoPorPlaca(String placa){
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }

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
     * @throws IllegalArgumentException si la placa es inválida
     */
    public boolean cambiarDisponibilidad(String placa, boolean estado) {
    	if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía.");
        }
    	
        VehiculoVO vehiculo = buscarVehiculoPorPlaca(placa);
        if (vehiculo != null) {
            vehiculo.setEstadoDisponibilidad(estado);
            return true;
        }
        return false;
    }
}