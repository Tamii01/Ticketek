package packageTicketek;

public class Estadio extends Sede {

	int precioBase;
	
	public Estadio(String nombre, String direccion, int capacidadMaxima, int precioBase) {
		super(nombre, direccion, capacidadMaxima);
		this.precioBase = precioBase;
	}
	
}
