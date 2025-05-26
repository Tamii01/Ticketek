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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public HashMap<String, Funcion> getFunciones() {
		return funciones;
	}

	public void setFunciones(HashMap<String, Funcion> funciones) {
		this.funciones = funciones;
	}

}
