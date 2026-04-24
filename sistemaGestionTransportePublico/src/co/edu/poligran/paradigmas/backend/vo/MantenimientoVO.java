package co.edu.poligran.paradigmas.backend.vo;
import java.time.LocalDate;

public class MantenimientoVO {

	private String idMantenimiento;
	private LocalDate fecha;
    private String descripcion;
    private double costo;
    private VehiculoVO vehiculo;
    
    public MantenimientoVO(String idMantenimiento, LocalDate fecha, String descripcion, double costo, VehiculoVO vehiculo) {
        this.idMantenimiento = idMantenimiento;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.costo = costo;
        this.vehiculo = vehiculo;
    }

	@Override
    public String toString() {
        return "ID Mantenimiento: " + idMantenimiento +
               ", Fecha: " + fecha +
               ", Descripción: " + descripcion +
               ", Costo: " + costo +
               ", Vehículo: " + (vehiculo != null ? vehiculo.getPlaca() : "N/A");
    }
    
	/**
	 * @return the idMantenimiento
	 */
	public String getIdMantenimiento() {
		return idMantenimiento;
	}
	
	/**
	 * @param idMantenimiento the idMantenimiento to set
	 */
	public void setIdMantenimiento(String idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}
	
	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
	    return fecha;
	}
	
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
	    this.fecha = fecha;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @return the costo
	 */
	public double getCosto() {
		return costo;
	}
	
	/**
	 * @param costo the costo to set
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	/**
	 * @return the vehiculo
	 */
	public VehiculoVO getVehiculo() {
		return vehiculo;
	}
	
	/**
	 * @param vehiculo the vehiculo to set
	 */
	public void setVehiculo(VehiculoVO vehiculo) {
		this.vehiculo = vehiculo;
	}
}