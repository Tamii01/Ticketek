package packageTicketek;

import java.util.List;

public class Funcion {

	String nombreEspectaculo; //lo añadí recién, supongo que una función tiene el nombre del espectaculo
	String sede;
	String fecha;
	List<Integer> entradasVendidas; //recién agregado
	double precioBase;
	

	// Constructor
	public Funcion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
		this.nombreEspectaculo = nombreEspectaculo;
		this.fecha = fecha;
		this.sede = sede;
		this.precioBase = precioBase;
	}


	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}


	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}


	public String getSede() {
		return sede;
	}


	public void setSede(String sede) {
		this.sede = sede;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public double getPrecioBase() {
		return precioBase;
	}


	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	
	
}
