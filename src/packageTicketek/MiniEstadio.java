package packageTicketek;

import java.util.Collection;

public class MiniEstadio extends Sede {

	int asientosPorFila;
	int puestos;
	String[] sectores;
	int[] capPorSector;
	int[] porcentajeAdicional;
	double precioConsumicion; //hace falta poner private?

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

	// si tiene asientos o sectores
	public boolean esNumerada() {
		return true;
	}

	@Override
	public boolean tieneConsumicionLibre() {
		return true;
	}

	@Override
	public double calcularCostoEntradaSinNumerar(Funcion funcion) {
	    double precio = funcion.precioBase;

	    if (tieneConsumicionLibre()) {
	        precio += precioConsumicion;
	    }

	    return precio;
	}

	@Override
	public String resumenFuncion(String fecha, String espectaculo, Collection<Entrada> entradas) {
	    int[] vendidosPorSector = new int[sectores.length];
	    for (Entrada entrada : entradas) {//.
	        if (entrada.espectaculo.equals(espectaculo) && entrada.fecha.equals(fecha)) {
	            for (int i = 0; i < sectores.length; i++) {
	                if (sectores[i].equalsIgnoreCase(entrada.sector)) {
	                    vendidosPorSector[i]++;
	                }
	            }
	        }
	    }

	    StringBuilder sb = new StringBuilder(" - (" + fecha + ") " + nombre + " - ");
	    for (int i = 0; i < sectores.length; i++) {
	        sb.append(sectores[i] + ": " + vendidosPorSector[i] + "/" + capPorSector[i]);
	        if (i < sectores.length - 1) {
	            sb.append(" | ");
	        }
	    }
	    sb.append("\n");
	    return sb.toString();
	}

	@Override
	public double calcularCostoEntrada(Funcion funcion, String sector) {
	    for (int i = 0; i < sectores.length; i++) {
	        if (sectores[i].equalsIgnoreCase(sector)) {
	            double adicional = funcion.precioBase * porcentajeAdicional[i] / 100.0;
	            return funcion.precioBase + adicional + precioConsumicion;
	        }
	    }
	    throw new RuntimeException("Sector no encontrado");
	}
}
