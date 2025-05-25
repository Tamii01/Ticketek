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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}
	
}
