package co.edu.poligran.paradigmas.backend.vo;

import java.time.LocalDateTime;

public class RutaVO {
    private int codigo;
    private ParadaVO origen;
    private ParadaVO destino;
    private float distancia;
    private LocalDateTime fecha;
    
    @Override
    public String toString() {
        return "Código: " + codigo +
               ", Origen: " + (origen != null ? origen.getNombre() : "N/A") +
               ", Destino: " + (destino != null ? destino.getNombre() : "N/A") +
               ", Distancia: " + distancia +
               ", Fecha: " + fecha;
    }
    
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * @return the origen
	 */
	public ParadaVO getOrigen() {
		return origen;
	}
	
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(ParadaVO origen) {
		this.origen = origen;
	}
	
	/**
	 * @return the destino
	 */
	public ParadaVO getDestino() {
		return destino;
	}
	
	/**
	 * @param destino the destino to set
	 */
	public void setDestino(ParadaVO destino) {
		this.destino = destino;
	}
	
	/**
	 * @return the distancia
	 */
	public float getDistancia() {
		return distancia;
	}
	
	/**
	 * @param distancia the distancia to set
	 */
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	
	/**
	 * @return the fecha
	 */
	public LocalDateTime getFecha() {
		return fecha;
	}
	
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
}
