package packageTicketek;

import java.util.List;

public class Usuario {
	
	String email;
	String nombre;
	String apellido;
	String contrasenia;
	List<Entrada> entradas;
	

	public Usuario(String email, String nombre, String apellido, String contrasenia) {
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
	}
	
	void comprarEntradas(Entrada entradas) {
		//this.entradas = entradas; (verificar esto)
	}
	
	
	//checkear
	List<Entrada> ListarEntradas() {
		return entradas;
	}
	
}
