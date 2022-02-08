package model;

public class Atraccion {
	
	private Integer id;
	private Integer costoVisita;
	private double promedioTiempo;
	private int cupoVisitantes;
	private String tipoAtraccion;
	private String nombre;
	private Integer activo;

	
	public Atraccion(Integer id,String nombre, int costoVisita, double promedioTiempo, int cupoVisitantes, String tipoAtraccion,Integer activo) {
		this.nombre = nombre;
		this.costoVisita = costoVisita;
		this.promedioTiempo = promedioTiempo;
		this.cupoVisitantes = cupoVisitantes;
		this.tipoAtraccion = tipoAtraccion;
		this.id = id;
		this.activo = activo;
	}
	
	public String toString() {
		
		return "Nombre atraccion: "+this.nombre+"\nCosto: "+this.costoVisita+"\nTiempo que requiere: "+this.promedioTiempo+"\nCupo total: "+this.cupoVisitantes+"\nTipo de atraccion: "+this.tipoAtraccion+"\n\r";
	}
	
	public boolean puedeComprar(int presupuesto) {
		return this.getCostoVisita() <= presupuesto;	
	}
	
	public boolean tiempoDisponible(Double tiempoUser) {
		return this.getPromedioTiempo() <= tiempoUser;
	}
	
	public boolean cupoDisponible() {
		return this.getCupoVisitantes() > 0;
	}

	public int getCostoVisita() {
		return costoVisita;
	}

	public double getPromedioTiempo() {
		return promedioTiempo;
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public int getCupoVisitantes() {
		return cupoVisitantes;
	}

	public String getTipoAtraccion() {
		return tipoAtraccion;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setCupoVisitantes(int cupoVisitantes) {
		this.cupoVisitantes = cupoVisitantes;
	}

	public int compareTo(Atraccion t) {
		return costoVisita.compareTo(t.getCostoVisita());
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	
	public boolean isActivo(){
		
		if(this.getActivo() == 1) {
			return true;
		}else {
			return false;
		}
	
	}
	
	
}
