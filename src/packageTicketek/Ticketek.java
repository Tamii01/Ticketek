package packageTicketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ticketek implements ITicketek {

	HashMap<String, Usuario> usuarios; //Email, Usuario
	HashMap<String, Espectaculo> espectaculos; //Códgio, Usuario
	HashMap<String, Sede> sedes;
	HashMap<String, Funcion> funciones;
	HashMap<String, Entrada> entradas;

	public Ticketek() {
		this.usuarios = new HashMap<>();
		this.espectaculos = new HashMap<>();
		this.sedes = new HashMap<>();
		this.funciones = new HashMap<>();
		this.entradas = new HashMap<>();
	}
	// REGISTRO DE ESTADIO

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima) {

		if (sedes.containsKey(nombre)) {
			throw new RuntimeException("Esta sede ya está registrada");
		}

		if (direccion.isEmpty() || direccion == null) {
			throw new RuntimeException("La dirección no puede estar vacía");
		}

		if (capacidadMaxima <= 0) {
			throw new RuntimeException("La capacidad debe ser mayor a 0");
		}

		Estadio estadio = new Estadio(nombre, direccion, capacidadMaxima);
		sedes.put(nombre, estadio);
	}

	// REGISTRO DE TEATRO
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {

		if (sedes.containsKey(nombre)) {
			throw new RuntimeException("Esta sede ya está registrada");
		}

		if (direccion.isEmpty() || direccion == null) {
			throw new RuntimeException("La dirección no puede estar vacía");
		}

		if (capacidadMaxima <= 0 || asientosPorFila <= 0) {
			throw new RuntimeException("Debe ser mayor a 0");
		}

		if (capacidad == null || porcentajeAdicional == null) {
			throw new RuntimeException("No debe ser null");
		}

		if (sectores == null) {
			throw new RuntimeException("Debe tener sectores");
		}

		Teatro teatro = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad,
				porcentajeAdicional);
		sedes.put(nombre, teatro);

	}

	// REGISTRO DE MINIESTADIO
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

	// Profe, no se ria de nuestro tp humilde, gracias
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

		//<Nombre, Sede>sedes


		if (espectaculos.containsKey(nombreEspectaculo)) {
			if (espectaculos.containsKey(fecha)) {
				throw new RuntimeException("Ya hay una funcion para esa fecha");
			}
		}
//		if (!sedes.containsKey(sede)) {
//			throw new RuntimeException("La sede no está registrada");
//		}

		Funcion funcion = new Funcion(nombreEspectaculo, fecha, sede, precioBase);
		funciones.put(fecha, funcion);


	}

	/**
	 * 4) Vende una o varias entradas a un usuario para funciones en sedes no
	 * numeradas
	 * 
	 * Devuelve una lista con las entradas vendidas (Ver interfaz IEntrada).
	 * 
	 * Se debe lanzar una excepcion si: - Si la sede de la funcion está numerada -
	 * si el usuario no está registrado - si el espectaculo no está registrado - si
	 * la contraseña no es valida - etc...
	 * 
	 * @param nombreEspectaculo
	 * @param fecha             en formato: dd/mm/YY
	 * @param email
	 * @param contrasenia
	 * @param cantidadEntradas
	 * @return
	 */
	//--------------VENTA DE ENTRADAS---------------
	//Sedes no numeradas, CAMPO
	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
		int cantidadEntradas) {
		List<IEntrada> entradasV = new ArrayList<>(); //lista de entradas vendidas
		String nomSede=null;
		Sede sede = null;
		//for (Espectaculo esp: espectaculos.values()) {
			    //if(esp.nombre.equals(nombreEspectaculo)) {
			    	for (Funcion func : funciones.values()) {
			            if (func.fecha.equals(fecha)) {
			            	nomSede=func.sede;
			            	sede = sedes.get(nomSede);
			            	break;
			            }
			            
			    	}
			   // }
			   
		//}//fin for
		
		if (sede == null) {
		    //throw new RuntimeException("No se encontró la sede"); //Me da error
		} /*else if (!(sede instanceof Teatro)) {
		    throw new RuntimeException("La sede no es un teatro");
		}*/
		// TODO Auto-generated method stub
		return entradasV;
	}
     
	//Sedes numeradas, teatro y mini estadio
	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			String sector, int[] asientos) {
		
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
				}
			}
		return sb.toString();
	}

	/**
	 * 15) Busca todas las entradas vendidas para un espectaculo, es decir, las
	 * entradas para todas sus funciones.
	 * 
	 * Ver interfaz IEntrada.
	 * 
	 * @param nombreEspectaculo
	 * @return
	 */
	@Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {

		List<IEntrada> entradasEspectaculo = new ArrayList<IEntrada>();

		for (String entrada : entradas.keySet()) {

		}

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

	/**
     * 8) Cancela una entrada comprada por el usuario. Se debe resolver en O(1)
     * 
     * Al cancelarla, el lugar asignado deberá volver a estar disponible.
     * 
     * Se deben validar los datos y lanzar una excepcion en caso de que 
     * algo sea invalido.
     * 
     * Si los datos son validos pero la fecha de la entrada ya pasó,
     * se debe devolver falso
     * 
     * Ver interfaz IEntrada.
     * 
     * HACERLO EN O(1)
     * 
     * @param Entrada
     * @param contrasenia
     * @return
     *  
     */
	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
	
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

	/**
	 * 12) Devuelve el total recaudado hasta el momento por un espectaculo.
	 * 
	 * @param nombreEspectaculo
	 * @return
	 */
	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		
		double recaud = 0;
		
		for(Entrada entrada : entradas.values()){
			if(entrada.getEspectaculo().equals(nombreEspectaculo)) {
				if(entrada.getSede().equals("Estadio")) {
					recaud += entrada.precio();
				}
				if(entrada.getSede().equals("Teatro") && entrada.getUbicacion().equals("VIP")) { //Suponiendo que ubicación es sector (Aunque lo dudo)
					recaud += entrada.precio() * 1.70;
				}
				if(entrada.getSede().equals("Teatro") && entrada.getUbicacion().equals("Comun")) { //Suponiendo que ubicación es sector (Aunque lo dudo)
					recaud += entrada.precio() * 1.40;
				}
				if(entrada.getSede().equals("Teatro") && entrada.getUbicacion().equals("Baja")) { //Suponiendo que ubicación es sector (Aunque lo dudo)
					recaud += entrada.precio() * 1.50;
				}
				if(entrada.getSede().equals("Teatro") && entrada.getUbicacion().equals("Alta")) { //Suponiendo que ubicación es sector (Aunque lo dudo)
					recaud += entrada.precio();
				}
				
			}
		}
		
		return recaud;
	}

	//DEVOLVER EN O(1)
	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
		// TODO Auto-generated method stub
		return 0;
	}

}
