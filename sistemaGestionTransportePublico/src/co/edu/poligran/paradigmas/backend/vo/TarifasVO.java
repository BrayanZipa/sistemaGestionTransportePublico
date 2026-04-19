package co.edu.poligran.paradigmas.backend.vo;

import java.time.LocalDateTime;

public class TarifasVO {

    private int codigo;
    private LocalDateTime fecha;
    private double valor;
    private String tipoTarifa; // Ej: Urbano, Intermunicipal, Especial

    public TarifasVO(int codigo, LocalDateTime fecha, double valor, String tipoTarifa) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.valor = valor;
        this.tipoTarifa = tipoTarifa;
    
    }
    
    @Override
    public String toString() {
        return "Código: " + codigo +
               ", Fecha: " + fecha +
               ", Valor: " + valor +
               ", Tipo Tarifa: " + tipoTarifa ;
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
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}
	
	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/**
	 * @return the tipoTarifa
	 */
	public String getTipoTarifa() {
		return tipoTarifa;
	}
	
	/**
	 * @param tipoTarifa the tipoTarifa to set
	 */
	public void setTipoTarifa(String tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

}
