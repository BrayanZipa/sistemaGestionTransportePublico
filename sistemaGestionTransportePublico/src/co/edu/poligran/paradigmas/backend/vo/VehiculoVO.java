package co.edu.poligran.paradigmas.backend.vo;

public class VehiculoVO {
    private String placa;
    private String modelo;
    private int capacidadPasajeros;
    private boolean estadoDisponibilidad;
    
    public VehiculoVO(String placa, String modelo, int capacidadPasajeros, boolean estadoDisponibilidad) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.capacidadPasajeros = capacidadPasajeros;
		this.estadoDisponibilidad = estadoDisponibilidad;
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
}
