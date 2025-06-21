package packageTicketek;

import java.util.List;

public class Usuario {

	String email;
	String nombre;
	String apellido;
	String contrasenia;
	List<Entrada> entradas;

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	public Usuario(String email, String nombre, String apellido, String contrasenia) {
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasenia = contrasenia;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return email.equals(other.email);
	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}

}
