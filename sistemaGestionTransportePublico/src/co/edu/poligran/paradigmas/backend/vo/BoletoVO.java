package co.edu.poligran.paradigmas.backend.vo;

import java.time.LocalDateTime;

public class BoletoVO {
	private int codigo;
    private LocalDateTime fechaCompra;
    private String numeroAsiento;
    private PasajeroVO pasajero;
    private RutaVO ruta;
    
    public BoletoVO(int codigo, LocalDateTime fechaCompra, String numeroAsiento, PasajeroVO pasajero) {
		super();
		this.codigo = codigo;
		this.fechaCompra = fechaCompra;
		this.numeroAsiento = numeroAsiento;
		this.pasajero = pasajero;
	}
    
    @Override
    public String toString() {
        return "Código: " + codigo +
               ", Fecha Compra: " + fechaCompra +
               ", Asiento: " + numeroAsiento +
               ", Pasajero: " + (pasajero != null ? pasajero.toString() : "N/A") +
               ", Ruta: " + (ruta != null ? ruta.toString() : "N/A");
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
	 * @return the fechaCompra
	 */
	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}
	
	/**
	 * @param fechaCompra the fechaCompra to set
	 */
	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	/**
	 * @return the numeroAsiento
	 */
	public String getNumeroAsiento() {
		return numeroAsiento;
	}
	
	/**
	 * @param numeroAsiento the numeroAsiento to set
	 */
	public void setNumeroAsiento(String numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
	}
	
	/**
	 * @return the pasajero
	 */
	public PasajeroVO getPasajero() {
		return pasajero;
	}
	
	/**
	 * @param pasajero the pasajero to set
	 */
	public void setPasajero(PasajeroVO pasajero) {
		this.pasajero = pasajero;
	}

	/**
	 * @return the ruta
	 */
	public RutaVO getRuta() {
		return ruta;
	}

	/**
	 * @param ruta the ruta to set
	 */
	public void setRuta(RutaVO ruta) {
		this.ruta = ruta;
	}
}
