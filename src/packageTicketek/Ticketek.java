package packageTicketek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Ticketek implements ITicketek {

	HashMap<String, Usuario> usuarios; // Email, Usuario
	HashMap<String, Espectaculo> espectaculos; // Nombre, Espectaculo
	HashMap<String, Sede> sedes; // Nombre, Sede
	HashMap<String, Funcion> funciones; // "Espectaculo-Fecha", Funcion
	HashMap<String, Entrada> entradas;// Código, Entrada
	private HashMap<String, Double> recaudacionPorSede; // "Espectaculo-Sede", Recaudación total
	private HashMap<String, Double> recaudacionPorEspectaculo; // "Espectaculo", Recaudación total por espectáculo
	private HashMap<String, Set<Integer>> asientosOcupados; //.

	public Ticketek() {
		this.usuarios = new HashMap<>();
		this.espectaculos = new HashMap<>();
		this.sedes = new HashMap<>();
		this.funciones = new HashMap<>();
		this.entradas = new HashMap<>();
		this.recaudacionPorSede = new HashMap<>();
		this.recaudacionPorEspectaculo = new HashMap<>();
		this.asientosOcupados = new HashMap<>();
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

		if (espectaculos.containsKey(nombre)) {
			throw new RuntimeException("Este espectáculo ya está registrado");
		}

		Espectaculo espectaculo = new Espectaculo(nombre);
		espectaculos.put(nombre, espectaculo);

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

		if (funcion.sede.esNumerada()) {
			throw new RuntimeException("No se pueden vender entradas tipo CAMPO para una sede numerada");
		}

		Sede sede = funcion.sede;

		// creamos entradas nuevas según la cantidad de entradas solicitadas
		for (int i = 0; i < cantidadEntradas; i++) {

			Entrada entrada = new Entrada(nombreEspectaculo, fecha, sede, funcion.precioBase, email);

			String codigo = generarCodigoEntrada();
			entrada.setCodigo(codigo);
			entradas.put(codigo, entrada);

			venderEntradaCampo.add(entrada);

			String nombreSede = funcion.sede.nombre;
			String claveSede = nombreEspectaculo + "-" + nombreSede;

			// Recaudación por sede
			double recaudacionActualSede = recaudacionPorSede.getOrDefault(claveSede, 0.0);
			recaudacionPorSede.put(claveSede, recaudacionActualSede + entrada.precio());

			// Recaudación por espectáculo
			double recaudacionActualEspectaculo = recaudacionPorEspectaculo.getOrDefault(nombreEspectaculo, 0.0);
			recaudacionPorEspectaculo.put(nombreEspectaculo, recaudacionActualEspectaculo + entrada.precio());

		}

		return venderEntradaCampo;
	}

	private boolean validarUsuario(String email, String contrasenia) {
		if (!usuarios.containsKey(email)) {
			return false;
		}

		Usuario usuario = usuarios.get(email);
		return usuario.contrasenia.equals(contrasenia);
	}

	private String generarCodigoEntrada() {
		return "E" + (entradas.size() + 0);
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

		if (!funciones.containsKey(claveFuncion)) {
			throw new RuntimeException("No existe función para ese espectáculo en esa fecha");
		}

		Funcion funcion = funciones.get(claveFuncion);

		for (int i = 0; i < asientos.length; i++) {

			Entrada entradaNumerada = new Entrada(nombreEspectaculo, fecha, funcion.sede, sector, funcion.precioBase,
					email, asientos);

			String codigo = generarCodigoEntrada();
			entradaNumerada.setCodigo(codigo);
			entradas.put(codigo, entradaNumerada);

			venderEntradaNumerada.add(entradaNumerada);

			String nombreSede = funcion.sede.nombre;
			String claveSede = nombreEspectaculo + "-" + nombreSede;

			// Recaudación por sede
			double recaudacionActualSede = recaudacionPorSede.getOrDefault(claveSede, 0.0);
			recaudacionPorSede.put(claveSede, recaudacionActualSede + entradaNumerada.precio());

			// Recaudación por espectáculo
			double recaudacionActualEspectaculo = recaudacionPorEspectaculo.getOrDefault(nombreEspectaculo, 0.0);
			recaudacionPorEspectaculo.put(nombreEspectaculo, recaudacionActualEspectaculo + entradaNumerada.precio());

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

		for (String clave : funciones.keySet()) {
			if (clave.startsWith(nombreEspectaculo)) {
				Funcion funcion = funciones.get(clave);
				Sede sede = funcion.sede;
				String fecha = funcion.fecha;

				sb.append(sede.resumenFuncion(fecha, nombreEspectaculo, entradas.values()));
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
			throw new RuntimeException("Usuario no registrado");
		}

		Usuario u = usuarios.get(email);

		if (!u.contrasenia.equals(contrasenia)) {
			throw new RuntimeException("Contraseña incorrecta");
		}

		List<IEntrada> futuras = new ArrayList<>();
		LocalDate hoy = LocalDate.now();

		Iterator<Entrada> it = entradas.values().iterator();
		while (it.hasNext()) {
			Entrada entrada = it.next();
			if (entrada.usuario.equals(email)) {
				LocalDate fechaEntrada = LocalDate.parse(entrada.fecha, DateTimeFormatter.ofPattern("dd/MM/yy"));
				if (fechaEntrada.isAfter(hoy)) {
					futuras.add(entrada);
				}
			}
		}

		return futuras;
	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {

		if (!usuarios.containsKey(email)) {
			throw new RuntimeException("El usuario no esta registrado");
		}

		if (!usuarios.get(email).contrasenia.equals(contrasenia)) {
			throw new RuntimeException("La contraseña no es valida");
		}

		List<IEntrada> entradasUsuario = new ArrayList<>();

		for (Entrada entrada : entradas.values()) {
			if (entrada.usuario.equals(email)) {
				entradasUsuario.add(entrada); 
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
		if (entrada == null) {
			throw new RuntimeException("La entrada no puede ser null");
		}

		Entrada ticket = (Entrada) entrada;

		if (!entradas.containsKey(ticket.codigo)) {
			throw new RuntimeException("La entrada no existe");
		}

		Usuario user = usuarios.get(ticket.usuario);
		if (user == null || !user.contrasenia.equals(contrasenia)) {
			throw new RuntimeException("Usuario inválido o contraseña incorrecta");
		}

		if (fechaYaPaso(ticket.fecha)) {
			return false;
		}

		entradas.remove(ticket.codigo);

		int[] asientos = ticket.asientos;
		if (asientos != null) {
			String clave = ticket.espectaculo + "-" + ticket.fecha + "-" + ticket.sector;
			Set<Integer> ocupados = asientosOcupados.get(clave);

			if (ocupados != null) {
				for (int asiento : asientos) {
					ocupados.remove(asiento);
				}
			}
		}

		return true;
	}

	private boolean fechaYaPaso(String fecha) {
		LocalDate fechaEntrada = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yy"));
		return fechaEntrada.isBefore(LocalDate.now());
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
		Entrada e = (Entrada) entrada;
		String email = e.usuario;

		if (!usuarios.containsKey(email)) {
			throw new RuntimeException("Usuario no registrado");
		}

		Usuario usuario = usuarios.get(email);

		if (!usuario.contrasenia.equals(contrasenia)) {
			throw new RuntimeException("Contraseña inválida");
		}

		LocalDate fechaEntrada = LocalDate.parse(e.fecha, DateTimeFormatter.ofPattern("dd/MM/yy"));
		if (fechaEntrada.isBefore(LocalDate.now())) {
			throw new RuntimeException("La entrada original está en el pasado");
		}

		String claveNuevaFuncion = e.espectaculo + "-" + fecha;
		if (!funciones.containsKey(claveNuevaFuncion)) {
			throw new RuntimeException("No existe función en esa fecha");
		}

		Funcion nuevaFuncion = funciones.get(claveNuevaFuncion);
		if (!nuevaFuncion.sede.esNumerada()) {
			throw new RuntimeException("La nueva función no es en sede numerada");
		}

		// Anular entrada anterior
		entradas.remove(e.codigo);
		if (usuario.entradas == null) {
			usuario.setEntradas(new ArrayList<>());
		}
		usuario.entradas.remove(e);

		int[] asientoPar = { asiento / 100, asiento % 100 }; // ejemplo: 305 → fila 3, asiento 5
		// o si ya te pasan directamente la fila y asiento separados:
		// int[] asientoPar = {fila, asiento};

		Entrada nuevaEntrada = new Entrada(e.espectaculo, fecha, nuevaFuncion.sede, sector, nuevaFuncion.precioBase,
				email, asientoPar);

		String nuevoCodigo = generarCodigoEntrada();
		nuevaEntrada.setCodigo(nuevoCodigo);

		entradas.put(nuevoCodigo, nuevaEntrada);
		usuario.entradas.add(nuevaEntrada);

		return nuevaEntrada;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
		Entrada e = (Entrada) entrada;
		String email = e.usuario;

		if (!usuarios.containsKey(email)) {
			throw new RuntimeException("Usuario no registrado");
		}

		Usuario usuario = usuarios.get(email);

		if (!usuario.contrasenia.equals(contrasenia)) {
			throw new RuntimeException("Contraseña inválida");
		}

		LocalDate fechaEntrada = LocalDate.parse(e.fecha, DateTimeFormatter.ofPattern("dd/MM/yy"));
		if (fechaEntrada.isBefore(LocalDate.now())) {
			throw new RuntimeException("La entrada original está en el pasado");
		}

		String claveNuevaFuncion = e.espectaculo + "-" + fecha;
		if (!funciones.containsKey(claveNuevaFuncion)) {
			throw new RuntimeException("No existe función en esa fecha");
		}

		Funcion nuevaFuncion = funciones.get(claveNuevaFuncion);
		if (nuevaFuncion.sede.esNumerada()) {
			throw new RuntimeException("No se puede cambiar a una sede numerada desde este método");
		}

		// Anular entrada anterior
		entradas.remove(e.codigo);
		if (usuario.entradas == null) {
			usuario.setEntradas(new ArrayList<>());
		}
		usuario.entradas.remove(e);

		// Crear nueva entrada
		Entrada nuevaEntrada = new Entrada(e.espectaculo, fecha, nuevaFuncion.sede, nuevaFuncion.precioBase, email);

		String nuevoCodigo = generarCodigoEntrada();
		nuevaEntrada.setCodigo(nuevoCodigo);

		entradas.put(nuevoCodigo, nuevaEntrada);
		usuario.entradas.add(nuevaEntrada);

		return nuevaEntrada;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		String clave = nombreEspectaculo + "-" + fecha;

		if (!funciones.containsKey(clave)) {
			throw new RuntimeException("No existe función para ese espectáculo y fecha");
		}

		Funcion funcion = funciones.get(clave);
		Sede sede = funcion.sede;

		if (sede.esNumerada()) {
			throw new RuntimeException("La función no es en un estadio");
		}

		return sede.calcularCostoEntradaSinNumerar(funcion);
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		String claveFuncion = nombreEspectaculo + "-" + fecha;
		if (!funciones.containsKey(claveFuncion)) {
			throw new RuntimeException("No existe la función");
		}

		Funcion funcion = funciones.get(claveFuncion);
		return funcion.sede.calcularCostoEntrada(funcion, sector);
	}

	/**
	 * 12) Devuelve el total recaudado hasta el momento por un espectaculo.
	 * 
	 * @param nombreEspectaculo
	 * @return
	 */
	@Override
	public double totalRecaudado(String nombreEspectaculo) {

		if (recaudacionPorEspectaculo.containsKey(nombreEspectaculo)) {
			return recaudacionPorEspectaculo.get(nombreEspectaculo);
		}

		return 0.0;
	}

	// DEVOLVER EN O(1)
	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {

		String clave = nombreEspectaculo + "-" + nombreSede;
		if (recaudacionPorSede.containsKey(clave)) {
			return recaudacionPorSede.get(clave);
		}

		return 0.0;
	}

}
