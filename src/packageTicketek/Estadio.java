package packageTicketek;

public class Estadio extends Sede {

	int precio;
	
	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		
	}
	
	public boolean esNumerada() {
	   return false;
	}
}
