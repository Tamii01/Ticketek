package packageTicketek;

import java.util.HashMap;

public class Espectaculo {

	String nombre;
	String codigo;
	HashMap<Sede, Funcion> funciones;
	
	public Espectaculo(String nombre) {
		this.nombre = nombre;
	}
	
	int calcularRecaudacion() {
		return 0;
	}
	
	public void agregarFuncion() {
		
	}

}
