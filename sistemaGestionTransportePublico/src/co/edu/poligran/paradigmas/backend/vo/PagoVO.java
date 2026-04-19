package co.edu.poligran.paradigmas.backend.vo;

import java.time.LocalDateTime;

public class PagoVO {

	private String idPago;
    private LocalDateTime fecha;
    private double monto;
    private String metodoPago;

    public PagoVO(String idPago, LocalDateTime fecha, double monto, String metodoPago) {
		super();
		this.idPago = idPago;
		this.fecha = fecha;
		this.monto = monto;
		this.metodoPago = metodoPago;
	}

    @Override
    public String toString() {
        return "ID Pago: " + idPago +
               ", Fecha: " + fecha +
               ", Monto: " + monto +
               ", Método de Pago: " + metodoPago;
    }

	/**
	 * @return the idPago
	 */
	public String getIdPago() {
		return idPago;
	}

	/**
	 * @param idPago the idPago to set
	 */
	public void setIdPago(String idPago) {
		this.idPago = idPago;
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

	/**
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * @return the metodoPago
	 */
	public String getMetodoPago() {
		return metodoPago;
	}

	/**
	 * @param metodoPago the metodoPago to set
	 */
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
}