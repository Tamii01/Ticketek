package packageTicketek;

import java.util.Collection;

public abstract class Sede {

	String nombre;
	String direccion;
	int capacidadMaxima;

	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
	}

	public boolean tieneConsumicionLibre() {
		return false;//.
	}

	public double calcularCostoEntradaSinNumerar(Funcion funcion) {
	    double precio = funcion.precioBase;

	    if (tieneConsumicionLibre()) {
	        precio += 15000;
	    }

	    return precio;
	}
	
	public abstract boolean esNumerada();

	public abstract String resumenFuncion(String fecha, String espectaculo, Collection<Entrada> entradas);

	public abstract double calcularCostoEntrada(Funcion funcion, String sector);
	
}
