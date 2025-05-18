package packageTicketek;

public class Entrada {

	Sede sede;
	Espectaculo espectaculo;
	String codigo;
	String fecha;
	String ubicacion;
	int precio;
	
	void crearEntrada(Sede sede, Espectaculo espectaculo, String codigo, String fecha, String ubicacion, int precio) {
		this.sede = sede;
		this.espectaculo = espectaculo;
		this.codigo = codigo;
		this.fecha = fecha;
		this.ubicacion = ubicacion;
		this.precio = precio;
	}
	
}
