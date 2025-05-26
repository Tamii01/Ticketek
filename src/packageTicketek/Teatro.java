package packageTicketek;

public class Teatro extends Sede{
	
	int asientosPorFila;
	String[] sectores;
	int[] capPorSector;
	int[] porcentajeAdicional;
	
	

	
	public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capPorSector, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima);
		this.asientosPorFila=asientosPorFila;
		this.sectores=sectores;
		this.capPorSector=capPorSector;
		this.porcentajeAdicional= porcentajeAdicional;
	}
	
	//Si tiene asientos y sectores
	public boolean esNumerada() {
		   return true;
		}
}
