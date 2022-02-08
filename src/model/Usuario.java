package model;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import dao.AtraccionDao;


public class Usuario {
	private int presupuesto;
	private Integer id;
	private double timpoDisponible;
	private String atraccionFav;
	private int atraccionFav_id;
	private String nombre;
	private boolean compro = false;
	private int presupuestoAux;
	private double timpoDisponibleAux;
	
	private String password;
	
	private Integer isAdmin;
	
	private ArrayList<Atraccion> atraccionesCompradas = new ArrayList<Atraccion>();
	private ArrayList<Atraccion> atraccionesEnPromos = new ArrayList<Atraccion>();
	private ArrayList<Promocion> promocionesCompradas = new ArrayList<Promocion>();
	
	

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTimpoDisponible() {
		return timpoDisponible;
	}

	public void setTimpoDisponible(double timpoDisponible) {
		this.timpoDisponible = timpoDisponible;
	}

	public String getAtraccionFav() {
		return atraccionFav;
	}

	public void setAtraccionFav(String atraccionFav) {
		this.atraccionFav = atraccionFav;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isCompro() {
		return compro;
	}

	public void setCompro(boolean compro) {
		this.compro = compro;
	}

	public int getPresupuestoAux() {
		return presupuestoAux;
	}

	public void setPresupuestoAux(int presupuestoAux) {
		this.presupuestoAux = presupuestoAux;
	}

	public double getTimpoDisponibleAux() {
		return timpoDisponibleAux;
	}

	public void setTimpoDisponibleAux(double timpoDisponibleAux) {
		this.timpoDisponibleAux = timpoDisponibleAux;
	}
	
	public boolean seguirComprando() {
		return (this.getPresupuesto() > 0 && this.getTimpoDisponible() > 0);
	}
	
	public boolean puedeComprarAtraccion(Atraccion atraccion) {
		if (atraccion.getCostoVisita() <= this.getPresupuesto()
				&& atraccion.getPromedioTiempo() <= this.getTimpoDisponible()){
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	public boolean isAdmin() {
		if(this.getIsAdmin() == 1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean puedeComprarPromocion(Promocion promocion) {
		if (promocion.puedeComprar(this.getPresupuesto()) && promocion.tiempoDisponible(this.getTimpoDisponible())
				&& promocion.cupoDisponible() && this.seguirComprando()) {	
			return true;
		}else {
			return false;
		}
	}
	
	public void userCompro(Atraccion atraccion) {
		this.compro = true;
		atraccion.setCupoVisitantes(atraccion.getCupoVisitantes() -1); 
		this.presupuesto -= atraccion.getCostoVisita();
		this.timpoDisponible -= atraccion.getPromedioTiempo();
	}
	
	public void userComproPromocion(Promocion promocion, AtraccionDao atracciones) {
		
		this.compro = true;
		this.promocionesCompradas.add(promocion);
		this.presupuesto -= promocion.getTotalPagar();
		this.timpoDisponible -= promocion.getTiempoTotal();
	}
	
	
	public void setAtraccionComprada(Atraccion atraccion) {
		atraccionesCompradas.add(atraccion);
	}
	
	

	public void setPromocionesCompradas(Promocion promocion) {
		promocionesCompradas.add(promocion);
	}

	public boolean isContieneAtraccion(Atraccion atraccion) {

		boolean contiene = false;
		for (Atraccion atraccion2 : atraccionesCompradas) {
			if(atraccion2.getId().equals(atraccion.getId())) {
				contiene = true;
			}
		}
		return contiene;
	}
	
	public boolean isContienePromocion(Promocion promocion) {

		boolean contiene = false;
		for (Promocion promocion2 : promocionesCompradas) {
			if(promocion2.getId().equals(promocion.getId())) {
				contiene = true;
			}
		}
		return contiene;
	}
	
	public void borrarAtraccionesCompradas() {
		atraccionesCompradas.clear();
	}
	
	public void borrarPromocionesCompradas() {
		promocionesCompradas.clear();
	}


	public ArrayList<Atraccion> getAtraccionesCompradas() {
		return atraccionesCompradas;
	}
	
	public ArrayList<Promocion> getPromocionesCompradas() {
		return promocionesCompradas;
	}
	
	public  ArrayList<Atraccion> getAtraccionesCompradasEnPromos(){
		atraccionesEnPromos.clear();
		System.out.println("Entro");
		for (Promocion promociones : this.getPromocionesCompradas()) {
			atraccionesEnPromos.addAll(promociones.getAtracciones());
		}
		System.out.println(atraccionesEnPromos);
		return atraccionesEnPromos;
	}
	
	public boolean isComproAtraccionEnPromo(Atraccion atraccion) {
		this.getAtraccionesCompradasEnPromos();
		boolean contiene = false;
		for (Atraccion atraccion2 : atraccionesEnPromos) {
			if(atraccion2.getId().equals(atraccion.getId())) {
				contiene = true;
			}
		}
		return contiene;
	}
	
	public String isAdminString() {
		if(this.isAdmin()) {
			return "Si";
		}else {
			return "No";
		}
	}
	

}
