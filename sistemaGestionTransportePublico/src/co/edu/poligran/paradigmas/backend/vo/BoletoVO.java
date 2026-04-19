package co.edu.poligran.paradigmas.backend.vo;

import java.time.LocalDateTime;

public class BoletoVO {
	private int codigo;
    private LocalDateTime fechaCompra;
    private String numeroAsiento;
    private PasajeroVO pasajero;
    private RutaVO ruta;
    private PagoVO pago;
    private TarifasVO tarifa; 
    
    public BoletoVO(int codigo, LocalDateTime fechaCompra, String numeroAsiento, PasajeroVO pasajero, RutaVO ruta, PagoVO pago, TarifasVO tarifa) {
		super();
		this.codigo = codigo;
		this.fechaCompra = fechaCompra;
		this.numeroAsiento = numeroAsiento;
		this.pasajero = pasajero;
		this.ruta = ruta;
		this.pago = pago;
		this.tarifa = tarifa; 
	}
    
    @Override
    public String toString() {
        return "Código: " + codigo +
               ", Fecha Compra: " + fechaCompra +
               ", Asiento: " + numeroAsiento +
               ", Pasajero: " + (pasajero != null ? pasajero.toString() : "N/A") +
               ", Ruta: " + (ruta != null ? ruta.toString() : "N/A") + 
               ", Pago: " + (pago != null ? pago.getIdPago() : "N/A") +
               ", Tarifa: " + (tarifa != null ? tarifa.getTipoTarifa() + " ($" + tarifa.getValor() + ")" : "N/A");
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
	
	public PagoVO getPago() {
	    return pago;
	}

	public void setPago(PagoVO pago) {
	    this.pago = pago;
	}
	
	public TarifasVO getTarifa() {
	    return tarifa;
	}

	public void setTarifa(TarifasVO tarifa) {
	    this.tarifa = tarifa;
	}
}
