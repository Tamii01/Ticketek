package packageTicketek;

public class Entrada implements IEntrada {

	String codigo;
	String espectaculo;
	String fecha;
	Sede sede;
	String sector;
	double precio;
	String usuario;
	int[] asientos;

	// Usamos dos constructores, de acuerdo a los parametros
	public Entrada(String espectaculo, String fecha, Sede sede, double precio, String usuario) {
		this.espectaculo = espectaculo;
		this.fecha = fecha;
		this.sede = sede;
		this.precio = precio;
		this.usuario = usuario;
		this.sector = "CAMPO";
	}

	public Entrada(String espectaculo, String fecha, Sede sede, String sector, double precio, String usuario,
			int[] asientos) {
		this.espectaculo = espectaculo;
		this.fecha = fecha;
		this.sede = sede;
		this.sector = sector;
		this.precio = precio;
		this.usuario = usuario;
		this.asientos = asientos;
	}

	@Override
	public double precio() {
		double precioFinal = precio;

		// Recargo por sector
		if (sector != null) {
			switch (sector) {
			case "VIP":
				precioFinal *= 1.70;
				break;
			case "Comun":
				precioFinal *= 1.40;
				break;
			case "Baja":
				precioFinal *= 1.50;
				break;
			}
		}

		// Recargo por consumición (SOLO para estadios)
		if (sede != null && sede.tieneConsumicionLibre()) {
			precioFinal += 15000; // 10,000 por entrada
		}

		return precioFinal;
	}

	@Override
	public String ubicacion() {
		if (asientos == null)
			return sector;
		return sector + " f:" + asientos[0] + " a:" + asientos[1];
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public String getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(String espectaculo) {
		this.espectaculo = espectaculo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int[] getAsientos() {
		return asientos;
	}

	public void setAsientos(int[] asientos) {
		this.asientos = asientos;
	}
}
