package packageTicketek;

import java.util.HashMap;
import java.util.List;

public class Ticketek implements ITicketek{

	HashMap<String, Usuario> usuarios;
	HashMap<String, Espectaculo> espectaculos;
	HashMap<String, Sede> sedes;
	HashMap<String, Funcion> funciones; //el string de funciones es una fecha ? (no recuerdo, supongo que sí porque la fecha en la que se da es única??)
	HashMap<String, Entrada> entradas;
	
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad,
			int[] porcentajeAdicional) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
		
		if(usuarios.containsKey(email)) {
			throw new RuntimeException("El email ya está registrado");
		}
		
		if(email.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || contrasenia.isEmpty()) {
			throw new RuntimeException("Los datos no son aceptables");
		}
		
		Usuario usuario = new Usuario(email, nombre, apellido, contrasenia);
		usuarios.put(email, usuario);
		
	}

	@Override
	public void registrarEspectaculo(String nombre) {
		
		if(espectaculos.containsKey(nombre)) {
			throw new RuntimeException("Este espectaculo ya está registrado");
		}
		
		Espectaculo espectaculo = new Espectaculo(nombre);
		espectaculos.put(nombre, espectaculo);
		
	}

	@Override
	public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {

		if(!espectaculos.containsKey(nombreEspectaculo)) {
			throw new RuntimeException("El espectaculo no está registrado");
		}
		
		if(!sedes.containsKey(sede)) {
			throw new RuntimeException("La sede no está registrada");
		}
		
		if(funciones.containsKey(fecha)) {
			throw new RuntimeException("Ya hay una funcion para esa fecha");
		}
		
		Funcion funcion = new Funcion(nombreEspectaculo, fecha, sede, precioBase);
		funciones.put(fecha, funcion);
		
		espectaculos.get(nombreEspectaculo).agregarFuncion();
		
	}

	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			int cantidadEntradas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			String sector, int[] asientos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarFunciones(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
		// TODO Auto-generated method stub
		return 0;
	}

}
