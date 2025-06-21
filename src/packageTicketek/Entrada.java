package packageTicketek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
			if (sede instanceof MiniEstadio) {
				precioFinal += ((MiniEstadio) sede).precioConsumicion;
			}
		}

		return precioFinal;
	}

	@Override
	public String ubicacion() {
		if (asientos == null)
			return sector;
		return sector + " f:" + asientos[0] + " a:" + asientos[1];
	}

	@Override
	public String toString() {
		String fechaMostrada = fecha;

		// Agregamos " P" si la fecha ya pasó
		LocalDate fechaEntrada = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yy"));
		if (fechaEntrada.isBefore(LocalDate.now())) {
			fechaMostrada += " P";
		}

		return codigo + " - " + espectaculo + " - " + fechaMostrada + " - " + sede.nombre + " - " + ubicacion();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Entrada other = (Entrada) obj;
		return codigo.equals(other.codigo);
	}

	@Override
	public int hashCode() {
		return codigo.hashCode();
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
