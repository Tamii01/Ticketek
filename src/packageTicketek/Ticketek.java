package packageTicketek;

import java.util.HashMap;
import java.util.List;

public class Ticketek implements ITicketek {

	HashMap<String, Usuario> usuarios;
	HashMap<String, Espectaculo> espectaculos;
	HashMap<String, Sede> sedes;
	HashMap<String, Funcion> funciones;
	HashMap<String, Entrada> entradas;

	
	
	public Ticketek() {
        usuarios = new HashMap<>();
        espectaculos = new HashMap<>();
        sedes = new HashMap<>();
        funciones = new HashMap<>();
        entradas = new HashMap<>();
	}
	//REGISTRO DE ESTADIO
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
        
		//Verifica si la sede ya esta registrada
		if (sedes.containsKey(nombre)) {
			throw new RuntimeException("Esta sede ya está registrada");
		}

		if (direccion.isEmpty() || direccion == null) {
			throw new RuntimeException("La dirección no puede estar vacía");
		}
		
		if(capacidadMaxima <= 0) {
			throw new RuntimeException("La capacidad debe ser mayor a 0");
		}
		
		Estadio estadio = new Estadio(nombre, direccion, capacidadMaxima);
		sedes.put(nombre, estadio);
	}

	//REGISTRO DE TEATRO
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		
		if(sedes.containsKey(nombre)) {
			throw new RuntimeException("Esta sede ya está registrada");
		}
		
		if(direccion.isEmpty() || direccion == null) {
			throw new RuntimeException("La dirección no puede estar vacía");
		}
		
		if(capacidadMaxima <= 0 || asientosPorFila <= 0) {
			throw new RuntimeException("Debe ser mayor a 0");
		}
		
		if(capacidad == null || porcentajeAdicional == null ) {
			throw new RuntimeException("No debe ser null");
		}
		
		if(sectores == null) {
			throw new RuntimeException("Debe tener sectores");
		}
		
		Teatro teatro = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional);
		sedes.put(nombre, teatro);
		
	}

	//REGISTRO DE MINIESTADIO
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad,
			int[] porcentajeAdicional) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {

		if (usuarios.containsKey(email)) {
			throw new RuntimeException("El email ya está registrado");
		}

		if (email.isEmpty() || email == null || nombre.isEmpty() || nombre == null || apellido.isEmpty()
				|| apellido == null || contrasenia.isEmpty() || contrasenia == null) {

			throw new RuntimeException("Los datos no son aceptables");
		}

		Usuario usuario = new Usuario(email, nombre, apellido, contrasenia);
		usuarios.put(email, usuario);

	}

	@Override
	public void registrarEspectaculo(String nombre) {

		if (espectaculos.containsKey(nombre)) {
			throw new RuntimeException("Este espectaculo ya está registrado");
		}

		Espectaculo espectaculo = new Espectaculo(nombre);
		espectaculos.put(nombre, espectaculo);

	}

	/**
	 * 14) Agrega una funcion nueva a un espectaculo ya registrado.
	 * 
	 * Si el espectaculo no está registrado o la sede o algun campo no es valido, se
	 * lanza una excepcion. Si ya hay una funcion para esa fecha, lanza excepcion.
	 * 
	 * @param nombreEspectaculo
	 * @param fecha             en formato: dd/mm/YY
	 * @param sede
	 * @param precioBase
	 */

	// ------------------------------------ CHECKEAR--------------------
	@Override
	public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {

		if (!espectaculos.containsKey(nombreEspectaculo)) {
			throw new RuntimeException("El espectaculo no está registrado");
		}

		if (!sedes.containsKey(sede)) {
			throw new RuntimeException("La sede no está registrada");
		}

		if (funciones.containsKey(fecha)) {
			throw new RuntimeException("Ya hay una funcion para esa fecha");
		}

		Sede sedeEspectaculo = sedes.get(sede);

		Funcion funcion = new Funcion(nombreEspectaculo, fecha, sedeEspectaculo, precioBase);
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

	/**
	 * 5) Devuelve un string donde cada fila representa una funcion y se detalla con
	 * el siguiente formato: - Si es estadio: " - ({FECHA}) {NOMBRE SEDE} -
	 * {ENTRADAS VENDIDAS} / {CAPACIDAD SEDE}" - si no es estadio: " - ({FECHA})
	 * {NOMBRE SEDE} - {NOMBRE SECTOR1}: {ENTRADAS VENDIDAS 1} / {CAPACIDAD SECTOR}
	 * | {NOMBRE SECTOR 2}: {ENTRADAS VENDIDAS 2} / {CAPACIDAD SECTOR 2} ..."
	 * 
	 * Por ejemplo: - (24/07/2025) El Monumental - 200/500 - (31/07/2025) Teatro
	 * Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea
	 * Alta: 50/50
	 * 
	 * @return un string con la lista de funciones del espectaculo.
	 */
	@Override
	public String listarFunciones(String nombreEspectaculo) {

		StringBuilder sb = new StringBuilder();

		for (Funcion funcion : funciones.values()) {

			if (funcion.getNombreEspectaculo().equals(nombreEspectaculo)) {
				Sede sede = funcion.getSede();

				if (sede instanceof Estadio) {
					sb.append(" - (" + funcion.getFecha() + ") " + sede.getNombre() + " - "
							+ funcion.getEntradasVendidas() + "/" + sede.getCapacidadMaxima());
				}

				if (sede instanceof Teatro) {
					Teatro teatro = (Teatro) sede;
					sb.append("- (" + funcion.getFecha() + ") " + sede.getNombre() + " - ");
				}
			}
		}
		return sb.toString();

	}

	@Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {

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
