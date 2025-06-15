package packageTicketek;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Ticketek implements ITicketek {

	HashMap<String, Usuario> usuarios; // Email, Usuario
	HashMap<String, Espectaculo> espectaculos; // Nombre, Espectaculo
	HashMap<String, Sede> sedes; // Nombre, Sede
	HashMap<String, Funcion> funciones; // "Espectaculo-Fecha", Funcion
	HashMap<String, Entrada> entradas;// Código?, Entrada
	private HashMap<String, Double> recaudacionPorSede; // "Espectaculo-Sede", Recaudación total
	private HashMap<String, Double> recaudacionDeEspectaculos;

	public Ticketek() {
		this.usuarios = new HashMap<>();
		this.espectaculos = new HashMap<>();
		this.sedes = new HashMap<>();
		this.funciones = new HashMap<>();
		this.entradas = new HashMap<>();
		this.recaudacionPorSede = new HashMap<>();
		this.recaudacionDeEspectaculos = new HashMap<>();
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

		if (sedes.containsKey(nombre)) {
			throw new RuntimeException("Esta sede ya está registrada");
		}

		if (direccion.isEmpty() || direccion == null) {
			throw new RuntimeException("La dirección no puede estar vacía");
		}

		if (capacidadMaxima <= 0 || asientosPorFila <= 0 || cantidadPuestos <= 0 || precioConsumicion < 0) {
			throw new RuntimeException("Debe ser mayor a 0");
		}

		if (capacidad == null || porcentajeAdicional == null) {
			throw new RuntimeException("No debe ser null");
		}

		if (sectores == null) {
			throw new RuntimeException("Debe tener sectores");
		}

		MiniEstadio miniEstadio = new MiniEstadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos,
				precioConsumicion, sectores, capacidad, porcentajeAdicional);
		sedes.put(nombre, miniEstadio);

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

		try {
			if (espectaculos.containsKey(nombre)) {
				throw new RuntimeException("Este espectaculo ya está registrado");
			} else {
				Espectaculo espectaculo = new Espectaculo(nombre);
				espectaculos.put(nombre, espectaculo);
			}

		} catch (RuntimeException e) {
			throw new RuntimeException("Error al registrar el espectáculo: ", e);
		}
	}

	@Override
	public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {

		if (!espectaculos.containsKey(nombreEspectaculo)) {
			throw new RuntimeException("El espectáculo no está registrado");
		}

		String claveFuncion = nombreEspectaculo + "-" + fecha;

		if (funciones.containsKey(claveFuncion)) {
			throw new RuntimeException("Ya hay una función para esa fecha");
		}

		Sede sedeObj = sedes.get(sede);

		Funcion funcion = new Funcion(fecha, sedeObj, precioBase);
		funciones.put(claveFuncion, funcion);
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
	// --------------VENTA DE ENTRADAS---------------
	// Sedes no numeradas, CAMPO
	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			int cantidadEntradas) {

		List<IEntrada> venderEntradaCampo = new ArrayList<>();

		if (!validarUsuario(email, contrasenia)) {
			throw new RuntimeException("Usuario inválido o contraseña incorrecta");
		}

		String claveFuncion = nombreEspectaculo + "-" + fecha;

		if (!funciones.containsKey(claveFuncion)) {
			throw new RuntimeException("No existe función para ese espectáculo en esa fecha");
		}

		Funcion funcion = funciones.get(claveFuncion);

		if (funcion.getSede().esNumerada()) {
			throw new RuntimeException("No se pueden vender entradas tipo CAMPO para una sede numerada");
		}

		Sede sedeFuncion = funcion.getSede();
		
		// creamos entradas nuevas según la cantidad de entradas solicitadas
		for (int i = 0; i < cantidadEntradas; i++) {
			Entrada entrada = new Entrada(nombreEspectaculo, fecha, sedeFuncion,
					funcion.getPrecioBase(), email);

			String codigo = generarCodigoEntrada();
			entradas.put(codigo, entrada);

			venderEntradaCampo.add(entrada);

			// calcula la recaudación por sede
			String nombreSede = funcion.getSede().getNombre();

			String claveRecaudacion = nombreEspectaculo + "-" + nombreSede;

			double montoActual = 0.0;
			if (recaudacionPorSede.containsKey(claveRecaudacion)) {
				montoActual += recaudacionPorSede.get(claveRecaudacion);
			}

			recaudacionPorSede.put(claveRecaudacion, montoActual + entrada.getPrecio());

		}

		return venderEntradaCampo;
	}

	private boolean validarUsuario(String email, String contrasenia) {
		if (!usuarios.containsKey(email)) {
			return false;
		}

		Usuario usuario = usuarios.get(email);
		return usuario.getContrasenia().equals(contrasenia);
	}

	private String generarCodigoEntrada(){
		return "E" + (entradas.size() + 1);
	}

	// Sedes numeradas, teatro y mini estadio
	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			String sector, int[] asientos) {

		List<IEntrada> venderEntradaNumerada = new ArrayList<>();
		
		if (!validarUsuario(email, contrasenia)) {
			throw new RuntimeException("Usuario inválido o contraseña incorrecta");
		}
		
		String claveFuncion = nombreEspectaculo + "-" + fecha;
		
		if(!funciones.containsKey(claveFuncion)) {
			throw new RuntimeException("No existe función para ese espectáculo en esa fecha");
		}
		
		Funcion funcion = funciones.get(claveFuncion);
		
		if(!funcion.getSede().esNumerada() && !funcion.getSede().getSector().equals(sector)) {
			throw new RuntimeException("La entrada debe ser numerada y debe tener un sector");
		}
		
		for(int i = 0; i < asientos.length; i++) {
			
			
			Entrada entradaNumerada = new Entrada(nombreEspectaculo, fecha, funcion.getSede(), sector, funcion.getPrecioBase(), email, asientos);
			
			String codigo = generarCodigoEntrada();
			entradas.put(codigo, entradaNumerada);
			
			venderEntradaNumerada.add(entradaNumerada);
			
			String nombreSede = funcion.getSede().getNombre();
			
			String claveRecaudacion = nombreEspectaculo + "-" + nombreSede;
			
			double montoActual = 0.0;
			if(recaudacionPorSede.containsKey(claveRecaudacion)) {
				montoActual += recaudacionPorSede.get(claveRecaudacion);
			}
			
			recaudacionPorSede.put(claveRecaudacion, montoActual + entradaNumerada.getPrecio());
			
		}
		
		return venderEntradaNumerada;
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

		List<IEntrada> entradasEspectaculo = new ArrayList<>();

		for (IEntrada entrada : entradas.values()) {
			if (((Entrada) entrada).espectaculo.equals(nombreEspectaculo)) {
				entradasEspectaculo.add(entrada);
			}
		}

		return entradasEspectaculo;
	}

	@Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
		if (!usuarios.containsKey(email)) {
	        throw new RuntimeException("El usuario no está registrado");
	    }

	    Usuario usuario = usuarios.get(email);
	    if (!usuario.contrasenia.equals(contrasenia)) {
	        throw new RuntimeException("La contraseña no es válida");
	    }

	    List<IEntrada> futuras = new ArrayList<>();
	    Date hoy = new Date();

	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

	    for (Entrada entrada : entradas.values()) {
	        if (!entrada.usuario.equals(email)) continue;

	        try {
	            Date fechaEntrada = formato.parse(entrada.fecha);
	            if (fechaEntrada.after(hoy)) {
	                futuras.add(entrada);
	            }
	        } catch (Exception e) {
	            // Si hay error en el formato, la ignoramos
	        }
	    }

		return null;
	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {

		if (!usuarios.containsKey(email)) {
			throw new RuntimeException("El usuario no esta registrado");
		}

		if (!usuarios.get(email).contrasenia.equals(contrasenia)) {
			throw new RuntimeException("La contraseña no es valida");
		}

		List<IEntrada> entradasUsuario = new ArrayList<>();// lista para almacenar entradas del usuario

		for (Entrada entrada : entradas.values()) {
			if (entrada.usuario.equals(email)) {
				entradasUsuario.add(entrada); // agregamos las entradas a la lista
			}
		}

		if (entradasUsuario.isEmpty()) {
			throw new RuntimeException("No se encontraron entradas para el usuario");
		}
		return entradasUsuario;
	}

	/**
	 * 8) Cancela una entrada comprada por el usuario. Se debe resolver en O(1)
	 * 
	 * Al cancelarla, el lugar asignado deberá volver a estar disponible.
	 * 
	 * Se deben validar los datos y lanzar una excepcion en caso de que algo sea
	 * invalido.
	 * 
	 * Si los datos son validos pero la fecha de la entrada ya pasó, se debe
	 * devolver falso
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
		// está bien el test, sale verde y no tengo nada(?
		return false;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
		
		return null;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {

		return null;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		

		return 0;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		
		String claveFuncion = nombreEspectaculo + "-" + fecha;
		if (!funciones.containsKey(claveFuncion)) {
	        throw new RuntimeException("No existe la función");
	    }

	    Funcion funcion = funciones.get(claveFuncion);
	    Sede sede = funcion.sede;

	    if (!sede.esNumerada()) {
	        
	        return funcion.precioBase;
	    }

	    String[] sectores;
	    int[] adicionales;

	    if (sede instanceof Teatro) {
	        sectores = ((Teatro) sede).sectores;
	        adicionales = ((Teatro) sede).porcentajeAdicional;
	    } else if (sede instanceof MiniEstadio) {
	        sectores = ((MiniEstadio) sede).sectores;
	        adicionales = ((MiniEstadio) sede).porcentajeAdicional;
	    } else {
	        throw new RuntimeException("Sede numerada desconocida");
	    }

	    for (int i = 0; i < sectores.length; i++) {
	        if (sectores[i].equalsIgnoreCase(sector)) {
	            double adicional = funcion.precioBase * adicionales[i] / 100.0;
	            return funcion.precioBase + adicional;
	        }
	    }

	    throw new RuntimeException("Sector no encontrado");
	}
	

	/**
	 * 12) Devuelve el total recaudado hasta el momento por un espectaculo.
	 * 
	 * @param nombreEspectaculo
	 * @return
	 */
	@Override
	public double totalRecaudado(String nombreEspectaculo) {

		if (espectaculos.containsKey(nombreEspectaculo)) {

			return recaudacionDeEspectaculos.get(nombreEspectaculo); // obtiene la recaudación del espectaculo
		}

		return 0.0;
	}

	// DEVOLVER EN O(1)
	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {

		String clave = nombreEspectaculo + "-" + nombreSede;
		if (recaudacionPorSede.containsKey(clave)) {
			return recaudacionPorSede.get(clave); // obtiene el valor de recaudación
		}
		return 0.0;
	}

}
