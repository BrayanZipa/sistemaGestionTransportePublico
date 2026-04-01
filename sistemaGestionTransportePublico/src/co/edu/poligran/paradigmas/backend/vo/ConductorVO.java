package co.edu.poligran.paradigmas.backend.vo;

public class ConductorVO extends TrabajadorVO {
    private String licencia;

	public ConductorVO(String nombre, String email, String rol, String identificacion, String licencia) {
		super(nombre, email, rol, identificacion);
		this.licencia = licencia;
	}

	@Override
	public String toString() {
	    return super.toString() +
	           ", Licencia: " + licencia;
	}

	/**
	 * @return the licencia
	 */
	public String getLicencia() {
		return licencia;
	}

	/**
	 * @param licencia the licencia to set
	 */
	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}
}