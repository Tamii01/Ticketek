package packageTicketek;

public abstract class Sede {

	String nombre;
	String direccion;
	int capacidadMaxima;
	String sector;
	
	
	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion; 
		this.capacidadMaxima = capacidadMaxima;
	}

	public boolean tieneConsumicionLibre() {
	    return false;
	}

	
	public String getSector() {
		return sector;
	}
	
	public void setSector(String sector) {
		this.sector = sector;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}
	public abstract boolean esNumerada();
}
