package packageTicketek;

import java.util.HashMap;

public class Espectaculo {

	String nombre;
	String codigo;
	HashMap<String, Funcion> funciones; // Fecha, Obj


	public Espectaculo(String nombre, String codigo, HashMap<String, Funcion> funciones) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.funciones = funciones;
	}

	public Espectaculo(String nombre) {
		this.nombre = nombre;
		this.codigo = "";
		this.funciones = null;
	}

}
