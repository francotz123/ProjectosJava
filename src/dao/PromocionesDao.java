package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.Usuario;
import model.Atraccion;
import model.Conection;
import model.Promocion;

public class PromocionesDao {

	Connection con;
	Conection cn = new Conection();
	PreparedStatement ps;
	ResultSet rs;

	boolean generado;
	Integer id = 0;
	Integer id_anterior = 0;
	String nombre = "";
	Integer tipo = 0;
	double descuento = 0;
	Integer total = 0;

	static ArrayList<Promocion> promocionList = new ArrayList<Promocion>();
	static ArrayList<Atraccion> atraccionPromocion = new ArrayList<Atraccion>();
	static ArrayList<Atraccion> atraccionFree = new ArrayList<Atraccion>();
	
	private static ArrayList<Promocion> promocionesConPreferencias = new ArrayList<Promocion>();
	private static ArrayList<Promocion> promocionesSinPreferencias = new ArrayList<Promocion>();

	public void cargarPromociones() {
		promocionList.clear();
		String sql = ("SELECT promociones.*, promociones_atracciones.promocion_id,  promociones_atracciones.atraccion_id,promociones_atracciones.atraccionGratis_id FROM promociones\r\n"
				+ "JOIN promociones_atracciones ON promociones.id = promociones_atracciones.promocion_id \r\n"
				+ "JOIN atracciones ON atracciones.id = promociones_atracciones.atraccion_id;\r\n" + "");

		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				nombre = rs.getString("nombrePromocion");
				tipo = rs.getInt("tipoPromocion");

				if (tipo == 1) {
					descuento = rs.getDouble("descuentoPorcentual");

				} else if (tipo == 2) {
					total = rs.getInt("costoFijo");
				}

				for (Atraccion atraccion : AtraccionDao.atraccionList) {
					if (atraccion.getId() == rs.getInt("atraccion_id")) {
						atraccionPromocion.add(atraccion);
					}
					if (tipo == 3) {
						if (atraccion.getId() == rs.getInt("atraccionGratis_id")) {
							atraccionFree.add(atraccion);
						}
					}
				}

				if (id == id_anterior) {
					this.cargarPromociones(tipo, nombre, descuento, total, id);
				}
				id_anterior = rs.getInt("id");
			}
			
		this.setGenerado(true);
		} catch (Exception e) {

		}finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// Falló al cerrar la conexión
				System.err.println(e.getMessage());
			}
		}

	}

	public void getPromociones(Usuario user) {
		promocionesSinPreferencias.clear();
		promocionesConPreferencias.clear();
		this.cargarPromociones();

		boolean controller = true;
		for (Promocion promocion : promocionList) {
				for (Atraccion atraccion : promocion.getAtracciones()) {
		
						if (atraccion.getTipoAtraccion().equals(user.getAtraccionFav()) && controller == true 
								&& !promocionesConPreferencias.contains(promocion)) {
							promocionesConPreferencias.add(promocion);
							controller = false;
						}
						if (controller  && !promocionesSinPreferencias.contains(promocion) 
								&&  !promocionesConPreferencias.contains(promocion)) {
							promocionesSinPreferencias.add(promocion);
						}
						controller = true;
					}
				
		}
	}
	
	public static void setPromocionList(ArrayList<Promocion> promocionList) {
		PromocionesDao.promocionList = promocionList;
	}

	public void leerPromociones() {
		for (Promocion promocion : promocionList) {
			System.out.println(promocion.toString());
		}	
	}

	public static ArrayList<Promocion> getPromocionList() {
		return promocionList;
	}
	
	public void cargarPromociones(Integer tipo, String nombre, double descuento, Integer total, Integer id) {
		switch (tipo) {
		case 1:

			PromocionPorcentual packAventura = new PromocionPorcentual(atraccionPromocion, tipo, nombre, descuento, id);
			promocionList.add(packAventura);

			break;
		case 2:
			PromocionAbsoluta packDegustacion = new PromocionAbsoluta(atraccionPromocion, tipo, nombre, total, id);
			promocionList.add(packDegustacion);
			break;
		case 3:
			PromocionAxB packPaisaje = new PromocionAxB(atraccionPromocion, tipo, nombre, atraccionFree, id);
			promocionList.add(packPaisaje);
			break;
		}

		atraccionPromocion.clear();
		atraccionFree.clear();
	}

	public boolean getGenerado() {
		return generado;
	}

	public void setGenerado(boolean generado) {
		this.generado = generado;
	}

	public ArrayList<Promocion> getPromocionesConPreferencias() {
		return promocionesConPreferencias;
	}

	public ArrayList<Promocion> getPromocionesSinPreferencias() {
		return promocionesSinPreferencias;
	}

	public static void borrarListas() {
		promocionesConPreferencias.clear();
		promocionesSinPreferencias.clear();
		promocionList.clear();
	}
	
	public Promocion conseguirPromocion(Integer id) {
		Promocion promo = null;
		this.cargarPromociones();

		for (Promocion promocion : promocionList) {
			if(promocion.getId().equals(id)) {
				 promo = promocion;
			}
		}
			return promo;

	}
	
	
	
	
}
