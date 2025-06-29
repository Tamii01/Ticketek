package packageTicketek;

import java.util.Collection;

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
	
	public boolean esNumerada() {
		   return true;
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
	            return funcion.precioBase + adicional;
	        }
	    }
	    throw new RuntimeException("Sector no encontrado");
	}

	
}
