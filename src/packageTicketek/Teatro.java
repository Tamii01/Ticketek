package packageTicketek;

public class Teatro extends Sede{
	
	
	String[] sectores; //recién agregado
	
	public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima);
		this.sectores = sectores;
	}
}
