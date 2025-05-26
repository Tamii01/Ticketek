package packageTicketek;

public class Entrada implements IEntrada{
	
	String espectaculo;
	String sede;
	String codigo;
	String fecha;
	String ubicacion;
	double precio;
	
	void crearEntrada(String sede, String espectaculo, String codigo, String fecha, String ubicacion, double precio) {
		this.sede = sede;
		this.espectaculo = espectaculo;
		this.codigo = codigo;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.precio = precio;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(String espectaculo) {
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public double precio() {
		// TODO Auto-generated method stub
		return precio;
	}

	@Override
	public String ubicacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
