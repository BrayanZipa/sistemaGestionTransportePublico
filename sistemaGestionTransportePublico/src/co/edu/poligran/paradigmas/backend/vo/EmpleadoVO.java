package co.edu.poligran.paradigmas.backend.vo;

public class EmpleadoVO extends TrabajadorVO {
    private String password;

	public EmpleadoVO(String nombre, String email, String rol, String identificacion, String password) {
		super(nombre, email, rol, identificacion);
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
