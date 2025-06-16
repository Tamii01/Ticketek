package packageTicketek;

public class MiniEstadio extends Sede{
	
	int asientosPorFila;
	int puestos;
	double consumisionLibre;
	String[] sectores;
	int[] capPorSector;
	int[] porcentajeAdicional;
	private double precioConsumicion;
	
	
	public MiniEstadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int puestos,
            double precioConsumicion, String[] sectores, int[] capPorSector, int[] porcentajeAdicional) {
super(nombre, direccion, capacidadMaxima);
this.asientosPorFila = asientosPorFila;
this.puestos = puestos;
this.precioConsumicion = precioConsumicion; 
this.sectores = sectores;
this.capPorSector = capPorSector;
this.porcentajeAdicional = porcentajeAdicional;
}

	
	//si tiene asientos o sectores
	public boolean esNumerada() {
		   return true;
		}
	
	@Override
	public boolean tieneConsumicionLibre() {
	    return true;
	}

	public double getPrecioConsumicion() {
	    return this.precioConsumicion;
	}


	
}
