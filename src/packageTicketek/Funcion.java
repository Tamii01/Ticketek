package packageTicketek;

public class Funcion {


	String fecha;
	Sede sede;
	double precioBase;
	int entradaVendida;

	// Constructor
	public Funcion(String fecha, Sede sede, double precioBase) {

		this.fecha = fecha;
		this.sede = sede;
		this.precioBase = precioBase;
	}

	int entradasVendidas(int cantidad) {
		return this.entradaVendida += cantidad;
	}

	public int getEntradaVendida() {
		return entradaVendida;
	}

	public void setEntradaVendida(int entradaVendida) {
		this.entradaVendida = entradaVendida;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
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
