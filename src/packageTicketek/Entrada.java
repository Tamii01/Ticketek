package packageTicketek;

public class Entrada implements IEntrada{
	
	String codigo;
	String espectaculo;
    String fecha;
    String sede;
    String sector;
    double precio;
    String usuario;
    int[] asientos;
	
    
    //Usamos dos constructores, de acuerdo a los parametros 
    public Entrada(String espectaculo, String fecha, String sede, double precio, String usuario) {
        this.espectaculo = espectaculo;
        this.fecha = fecha;
        this.sede = sede;
        this.precio = precio;
        this.usuario = usuario;
        this.sector = "CAMPO"; 
    }

    public Entrada(String espectaculo, String fecha, String sede, String sector, double precio, String usuario, int[] asientos) {
        this.espectaculo = espectaculo;
        this.fecha = fecha;
        this.sede = sede;
        this.sector = sector;
        this.precio = precio;
        this.usuario = usuario;
        this.asientos = asientos;
    }

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String getEspectaculo() {
		return espectaculo;
	}

	public void setEspectaculo(String espectaculo) {
		this.espectaculo = espectaculo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/*public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}*/

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public double precio() {
		// TODO Auto-generated method stub
		return precio;
	}

	@Override
	public String ubicacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
