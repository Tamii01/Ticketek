package packageTicketek;

public class Entrada implements IEntrada{
	
	String codigo;
	String espectaculo;
    String fecha;
    Sede sede;
    String sector;
    double precio;
    String usuario;
    int[] asientos;
	
    
    //Usamos dos constructores, de acuerdo a los parametros 
    public Entrada(String espectaculo, String fecha, Sede sede, double precio, String usuario) {
        this.espectaculo = espectaculo;
        this.fecha = fecha;
        this.sede = sede;
        this.precio = precio;
        this.usuario = usuario;
        this.sector = "CAMPO"; 
    }

    public Entrada(String espectaculo, String fecha, Sede sede, String sector, double precio, String usuario, int[] asientos) {
        this.espectaculo = espectaculo;
        this.fecha = fecha;
        this.sede = sede;
        this.sector = sector;
        this.precio = precio;
        this.usuario = usuario;
        this.asientos = asientos;
    }

    @Override
    public double precio() {

        if (sector.equals("VIP")) {
            precio *= 1.70;
        } else if (sector.equals("Comun")) {
            precio *= 1.40;
        } else if (sector.equals("Baja")) {
            precio *= 1.50;
        }
        
        if (sede.tieneConsumicionLibre()) {
            precio += 15000;
        }

        return precio;
    }

    
	@Override
	public String ubicacion() {
		if (asientos == null) 
			return sector;
		return sector + " f:" + asientos[0] + " a:" + asientos[1];
	} 
    
    
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
