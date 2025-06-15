package packageTicketek;

public class MiniEstadio extends Sede{
	
	int asientosPorFila;
	int puestos;
	double consumisionLibre;
	String[] sectores;
	int[] capPorSector;
	int[] porcentajeAdicional;
	
	
	
	
	public MiniEstadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,int puestos, 
			double consumision, String[] sectores, int[] capPorSector, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima);
		this.asientosPorFila=asientosPorFila;
		this.puestos=puestos;
		this.consumisionLibre=consumision;
		this.sectores= sectores;
		this.porcentajeAdicional=porcentajeAdicional;
		
	}
	
	//si tiene asientos o sectores
	public boolean esNumerada() {
		   return true;
		}

}
