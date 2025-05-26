package packageTicketek;

public class Estadio extends Sede {

	int precio;
	
	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		
	}

	@Override
	public boolean esNumerada() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean esNumerada() {
	   return false;
	}
}
