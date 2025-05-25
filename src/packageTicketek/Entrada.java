package packageTicketek;

public class Entrada {
	Sede sede;
	Espectaculo espectaculo;
	String codigo;
	String fecha;
	String ubicacion;
	int precio;
	
	void crearEntrada(Sede sede, Espectaculo espectaculo, String codigo, String fecha, String ubicacion, int precio) {
		this.sede = sede;
		this.espectaculo = espectaculo;
		this.codigo = codigo;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.precio = precio;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Espectaculo getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(Espectaculo espectaculo) {
		this.espectaculo = espectaculo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
}
