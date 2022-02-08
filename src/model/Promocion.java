package model;

import java.util.ArrayList;

public abstract class Promocion {
	
	ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
	private String nombre;
	private Integer tipo = 0;
	private Integer id;

	private String nombresAtraccion = "";
	int totalPagar = 0;
	Double tiempoTotal = 0.0;

	public Promocion(ArrayList<Atraccion> nombreAtraccion,Integer tipo, String nombre,Integer id) {
		this.atracciones.addAll(nombreAtraccion);
		this.tipo = tipo;
		this.nombre = nombre;
		this.id = id;
	}

	public abstract void beneficios();

	public boolean puedeComprar(int presupuesto) {
		return this.getTotalPagar() <= presupuesto;
	}

	public boolean tiempoDisponible(Double tiempoUser) {
		return this.getTiempoTotal() <= tiempoUser;
	}
	
	public boolean cupoDisponible() {
		boolean cupoDisponible = false;
		for (Atraccion atraccion : atracciones) {
			if(atraccion.cupoDisponible()) {
				cupoDisponible = true;
			}else {
				cupoDisponible = false;
				break;
			}
		}
		return cupoDisponible;
	}

	public void tiempoTotal() {
		for (Atraccion atraccion : this.getAtracciones()) {
			this.tiempoTotal += atraccion.getPromedioTiempo();
		}	
	}
	
	public void tiempoTotal(ArrayList<Atraccion> atraccionGratis) {
		for (Atraccion atraccion : this.getAtracciones()) {
			this.tiempoTotal += atraccion.getPromedioTiempo();
		}
		for (Atraccion atraccion : atraccionGratis) {
			this.tiempoTotal += atraccion.getPromedioTiempo();
		}	
	}
	
	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getTipo() {
		return tipo;
	}

	public String getNombresAtraccion() {
		this.nombresAtraccion = "";
		for (Atraccion atraccion : atracciones) {
			this.nombresAtraccion += atraccion.getNombre() + ", ";
		}
		return nombresAtraccion;
	}

	public Integer getTotalPagar() {
		return totalPagar;
	}
	
	

	public Integer getId() {
		return id;
	}

	public Double getTiempoTotal() {
		return tiempoTotal;
	}
	

}
