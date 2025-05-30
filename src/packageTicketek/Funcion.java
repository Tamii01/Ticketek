package packageTicketek;

public class Funcion {

	String nombreEspectaculo;
	String fecha;
	String sede;
	double precioBase;
	int entradaVendida;

	// Constructor
	public Funcion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
		this.nombreEspectaculo = nombreEspectaculo;
		this.fecha = fecha;
		this.sede = sede;
		this.precioBase = precioBase;
	}

	int entradaVendida(int cantidad) {
		return this.entradaVendida += cantidad;
	}

	public int getEntradsVendida() {
		return entradaVendida;
	}

	public void setEntradaVendida(int entradaVendida) {
		this.entradaVendida = entradaVendida;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.nombreEspectaculo.equals(obj)) {
			return true;
		}
		return true;
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

	/*
	 * public int getEntradasVendidas() { return entradasVendidas; }
	 * 
	 * public void setEntradasVendidas(int entradasVendidas) { this.entradasVendidas
	 * = entradasVendidas; }
	 */

}
