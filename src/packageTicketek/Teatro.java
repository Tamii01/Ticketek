package packageTicketek;

public class Teatro extends Sede{
	
	int precioBase;
	String[] sectores; //recién agregado
	int asientosPorFila; //recién agregado
	
	public Teatro(String nombre, String direccion, int capacidadMaxima, int precioBase, String[] sectores, int asientosPorFila) {
		super(nombre, direccion, capacidadMaxima);
		this.precioBase = precioBase;
		this.sectores = sectores;
	}
}
