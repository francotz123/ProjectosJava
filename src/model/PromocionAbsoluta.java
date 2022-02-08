package model;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion{

	int total= 0;
	
	public PromocionAbsoluta(ArrayList<Atraccion> nombreAtraccion, Integer tipo, String nombre, Integer total, Integer id) {
		super(nombreAtraccion, tipo, nombre,id);
		super.totalPagar = total;
		super.tiempoTotal();
	}

	@Override
	public void beneficios() {
	
	}


	public Integer getTotal() {
		return total;
	}

	public Integer getDescuento() {
		return total;
	}
	
	
	@Override
	public String toString() {
		return  super.getNombre()+ ",  Las Atracciones que incluye son ="
				+ super.getNombresAtraccion() + " tiempo que requiere: "+super.getTiempoTotal()+" horas. "+ "precio = $"+super.getTotalPagar();
	}


}
