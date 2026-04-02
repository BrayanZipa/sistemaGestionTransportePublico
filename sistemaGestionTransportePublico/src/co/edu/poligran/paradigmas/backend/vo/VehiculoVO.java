package co.edu.poligran.paradigmas.backend.vo;

import java.util.ArrayList;
import java.util.List;

public class VehiculoVO {
    private String placa;
    private String modelo;
    private int capacidadPasajeros;
    private boolean estadoDisponibilidad;
    private List<RutaVO> rutas;
    
    public VehiculoVO(String placa, String modelo, int capacidadPasajeros, boolean estadoDisponibilidad) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.capacidadPasajeros = capacidadPasajeros;
		this.estadoDisponibilidad = estadoDisponibilidad;
		this.rutas = new ArrayList<>();
	}

	@Override
    public String toString() {
        return "Placa: " + placa +
               ", Modelo: " + modelo +
               ", Capacidad: " + capacidadPasajeros +
               ", Disponible: " + estadoDisponibilidad;
    }
    
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * @return the capacidadPasajeros
	 */
	public int getCapacidadPasajeros() {
		return capacidadPasajeros;
	}
	
	/**
	 * @param capacidadPasajeros the capacidadPasajeros to set
	 */
	public void setCapacidadPasajeros(int capacidadPasajeros) {
		this.capacidadPasajeros = capacidadPasajeros;
	}
	
	/**
	 * @return the estadoDisponibilidad
	 */
	public boolean isEstadoDisponibilidad() {
		return estadoDisponibilidad;
	}
	
	/**
	 * @param estadoDisponibilidad the estadoDisponibilidad to set
	 */
	public void setEstadoDisponibilidad(boolean estadoDisponibilidad) {
		this.estadoDisponibilidad = estadoDisponibilidad;
	}

	/**
	 * @return the rutas
	 */
	public List<RutaVO> getRutas() {
		return rutas;
	}

	/**
	 * @param rutas the rutas to set
	 */
	public void setRutas(List<RutaVO> rutas) {
		this.rutas = rutas;
	}
	
	/**
	 * Método que agrega una ruta a el vehículo
	 * @param ruta ruta que se desea agregar a la lista de rutas
	 */
	public void agregarRuta(RutaVO ruta) {
	    rutas.add(ruta);
	}
}
