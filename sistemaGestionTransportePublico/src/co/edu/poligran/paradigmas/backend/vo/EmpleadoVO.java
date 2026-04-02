package co.edu.poligran.paradigmas.backend.vo;

/**
 * Clase que representa un empleado del sistema.
 * Hereda los atributos básicos de TrabajadorVO y agrega
 * la contraseña para el acceso al sistema.
 */
public class EmpleadoVO extends TrabajadorVO {

    private String password;

    /**
     * Constructor de la clase EmpleadoVO
     * 
     * @param nombre nombre del empleado
     * @param email correo electrónico del empleado
     * @param rol rol asignado dentro del sistema
     * @param identificacion identificación del empleado
     * @param password contraseña de acceso
     */
    public EmpleadoVO(String nombre, String email, String rol, String identificacion, String password) {
        super(nombre, email, rol, identificacion);
        this.password = password;
    }

    /**
     * Método que retorna la información del empleado en formato texto
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Método que indica el tipo de trabajador
     * 
     * @return tipo de trabajador (Empleado)
     */
    @Override
    public String tipoTrabajador() {
        return "Empleado";
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