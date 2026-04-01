package co.edu.poligran.paradigmas.backend.vo;

public class PasajeroVO {
    private String identificacion;
    private String nombre;
    
	public PasajeroVO(String identificacion, String nombre) {
		super();
		this.identificacion = identificacion;
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
	    return "Nombre: " + nombre +
	           ", Identificación: " + identificacion;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}
	
	/**
	 * @param identificacion the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
