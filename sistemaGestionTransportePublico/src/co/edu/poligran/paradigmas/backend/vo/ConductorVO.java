package co.edu.poligran.paradigmas.backend.vo;

/**
 * Clase que representa un conductor dentro del sistema.
 * Hereda los datos básicos de TrabajadorVO y agrega
 * la información de la licencia de conducción.
 */
public class ConductorVO extends TrabajadorVO {

    private String licencia;

    /**
     * Constructor de la clase ConductorVO
     * 
     * @param nombre nombre del conductor
     * @param email correo electrónico del conductor
     * @param rol rol asignado dentro del sistema
     * @param identificacion identificación del conductor
     * @param licencia número o tipo de licencia de conducción
     */
    public ConductorVO(String nombre, String email, String rol, String identificacion, String licencia) {
        super(nombre, email, rol, identificacion);
        this.licencia = licencia;
    }

    /**
     * Método que retorna la información del conductor en formato texto
     */
    @Override
    public String toString() {
        return super.toString() +
               ", Licencia: " + licencia;
    }

    /**
     * Método que indica el tipo de trabajador
     * @return tipo de trabajador (Conductor)
     */
    @Override
    public String tipoTrabajador() {
        return "Conductor";
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