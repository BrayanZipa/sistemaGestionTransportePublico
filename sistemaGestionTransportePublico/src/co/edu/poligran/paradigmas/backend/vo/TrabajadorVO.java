package co.edu.poligran.paradigmas.backend.vo;

/**
 * Clase abstracta que representa un trabajador dentro del sistema.
 * Contiene la información básica común para empleados y conductores.
 */
public abstract class TrabajadorVO {

    protected String nombre;
    protected String email;
    protected String rol;
    protected String identificacion;

    /**
     * Constructor de la clase TrabajadorVO
     * 
     * @param nombre nombre del trabajador
     * @param email correo electrónico del trabajador
     * @param rol rol que desempeña dentro del sistema
     * @param identificacion identificación única del trabajador
     */
    public TrabajadorVO(String nombre, String email, String rol, String identificacion) {
        super();
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.identificacion = identificacion;
    }

    /**
     * Método que retorna la información del trabajador en formato texto
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre +
               ", Email: " + email +
               ", Identificación: " + identificacion;
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
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
     * Método abstracto que retorna el tipo de trabajador
     * @return tipo de trabajador
     */
    public abstract String tipoTrabajador();
}