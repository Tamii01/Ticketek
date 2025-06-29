package packageTicketek;

import java.util.Collection;

public class Estadio extends Sede {
	
	public Estadio(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
	}

	public boolean esNumerada() {
		return false;
	}
	
	@Override
	public String resumenFuncion(String fecha, String espectaculo, Collection<Entrada> entradas) {
	    int cantidadVendida = 0;
	    for (Entrada entrada : entradas) {
	        if (entrada.espectaculo.equals(espectaculo) && entrada.fecha.equals(fecha)) {
	            cantidadVendida++;
	        }
	    }
	    return " - (" + fecha + ") " + nombre + " - " + cantidadVendida + "/" + capacidadMaxima + "\n";
	}

	@Override 
	public double calcularCostoEntrada(Funcion funcion, String sector) {
		return 0;
	}


}
