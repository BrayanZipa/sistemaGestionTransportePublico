package co.edu.poligran.paradigmas.backend.vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RutaVO {
    private int codigo;
    private ParadaVO origen;
    private ParadaVO destino;
    private float distancia;
    private LocalDateTime fecha;
    private VehiculoVO vehiculo;
    private ConductorVO conductor;
    private List<ParadaVO> paradas;
    private List<BoletoVO> boletos;

	public RutaVO(int codigo, ParadaVO origen, ParadaVO destino, float distancia, LocalDateTime fecha, VehiculoVO vehiculo, ConductorVO conductor) {
		super();
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		this.conductor = conductor;
		this.paradas = new ArrayList<>();
		this.boletos = new ArrayList<>();
	}

	@Override
    public String toString() {
        return "Código: " + codigo +
               ", Origen: " + (origen != null ? origen.getNombre() : "N/A") +
               ", Destino: " + (destino != null ? destino.getNombre() : "N/A") +
               ", Distancia: " + distancia +
               ", Fecha: " + fecha +
               ", Vehículo: " + (vehiculo != null ? vehiculo.toString() : "N/A") +
               ", Conductor: " + (conductor != null ? conductor.toString() : "N/A");
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
	
    /**
	 * @return the vehiculo
	 */
	public VehiculoVO getVehiculo() {
		return vehiculo;
	}

	/**
	 * @param vehiculo the vehiculo to set
	 */
	public void setVehiculo(VehiculoVO vehiculo) {
		this.vehiculo = vehiculo;
	}

	/**
	 * @return the conductor
	 */
	public ConductorVO getConductor() {
		return conductor;
	}

	/**
	 * @param conductor the conductor to set
	 */
	public void setConductor(ConductorVO conductor) {
		this.conductor = conductor;
	}

	/**
	 * @return the paradas
	 */
	public List<ParadaVO> getParadas() {
		return paradas;
	}

	/**
	 * @param paradas the paradas to set
	 */
	public void setParadas(List<ParadaVO> paradas) {
		this.paradas = paradas;
	}
	
	/**
	 * Método que agrega una parada a la ruta
	 * @param parada parada que se desea agregar a la lista de paradas
	 */
	public void agregarParada(ParadaVO parada) {
	    paradas.add(parada);
	}
	
	/**
	 * @return the boletos
	 */
	public List<BoletoVO> getBoletos() {
		return boletos;
	}

	/**
	 * @param boletos the boletos to set
	 */
	public void setBoletos(List<BoletoVO> boletos) {
		this.boletos = boletos;
	}
	
	/**
	 * Método que agrega un boleto a la ruta
	 * @param boleto boleto que se desea agregar a la lista de boletos
	 */
	public void agregarBoleto(BoletoVO boleto) {
	    boletos.add(boleto);
	}
}
