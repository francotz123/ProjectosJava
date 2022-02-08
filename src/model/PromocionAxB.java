package model;

import java.util.ArrayList;



public class PromocionAxB extends Promocion {
	ArrayList<Atraccion> atraccionFree;
	ArrayList<Atraccion> nombresFree;
	String nombresFrees = " ";

	public PromocionAxB(ArrayList<Atraccion> nombreAtraccion, Integer tipo, String nombre,
			ArrayList<Atraccion> atraccionFree, Integer id) {
		super(nombreAtraccion, tipo, nombre, id);
		this.atraccionFree = atraccionFree;
		this.beneficios();
		super.tiempoTotal(atraccionFree);
	}

	@Override
	public void beneficios() {
		for (Atraccion atraccion : super.getAtracciones()) {
			super.totalPagar += atraccion.getCostoVisita();
		}
		for (Atraccion atraccion : atraccionFree) {
			nombresFrees += atraccion.getNombre() + ", ";
		}
	}

	@Override
	public String toString() {
		return "Pack: " + super.getNombre() + "- Atracciones que incluye: " + super.getNombresAtraccion()
				+ " obten gratis" + this.getNombresFrees() + " tiempo que requiere: " + super.getTiempoTotal()
				+ " horas. " + " total a pagar: $" + this.getTotalPagar() + " ";
	}

	public String getNombresFrees() {
		return nombresFrees;
	}
}
