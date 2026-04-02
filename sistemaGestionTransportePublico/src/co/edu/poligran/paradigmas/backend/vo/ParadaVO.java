package co.edu.poligran.paradigmas.backend.vo;

import java.util.ArrayList;
import java.util.List;

public class ParadaVO {
    private String codigo;
    private String nombre;
    private String ubicacion;
    private List<RutaVO> rutas;
    
    public ParadaVO(String codigo, String nombre, String ubicacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.rutas = new ArrayList<>();
	}

	@Override
    public String toString() {
        return "Código: " + codigo +
               ", Nombre: " + nombre +
               ", Ubicación: " + ubicacion;
    }
    
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}
	
	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	/**
	 * @return the rutas
	 */
	public List<RutaVO> getRutas() {
		return rutas;
	}

	/**
	 * @param rutas the rutas to set
	 */
	public void setRutas(List<RutaVO> rutas) {
		this.rutas = rutas;
	}

	/**
	 * Método que agrega una ruta a la parada
	 * @param ruta ruta que se desea agregar a la lista de rutas
	 */
	public void agregarRuta(RutaVO ruta) {
	    rutas.add(ruta);
	}
}
