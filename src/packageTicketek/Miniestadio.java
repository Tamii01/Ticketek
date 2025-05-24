package packageTicketek;

public class Miniestadio extends Sede{
	
	double consumisionLibre;
	int puestoMerchandasing;
	int puestoComidaRapida;
	
	public Miniestadio(String nombre, String direccion, int capacidadMaxima, double consumisionLibre, int puestoMerchandasing, int puestoComidaRapida) {
		super(nombre, direccion, capacidadMaxima);
		this.consumisionLibre = consumisionLibre;
		this.puestoMerchandasing = puestoMerchandasing;
		this.puestoComidaRapida = puestoComidaRapida;
	}

}
