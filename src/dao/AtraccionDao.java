package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import model.Atraccion;
import model.Conection;
import model.Usuario;

public class AtraccionDao {

	boolean seguir;
	Connection con;
	Conection cn = new Conection();
	PreparedStatement ps;
	ResultSet rs;
	int r = 0;

	static ArrayList<Atraccion> atraccionList = new ArrayList<Atraccion>();
	static ArrayList<Atraccion> atraccionesConPreferencias = new ArrayList<Atraccion>();
	static ArrayList<Atraccion> atraccionesSinPrefenecias = new ArrayList<Atraccion>();

	public void getAtracciones(Usuario user) {
		atraccionesSinPrefenecias.clear();
		atraccionesConPreferencias.clear();
		this.cargarAtracciones();
		for (Atraccion atraccion : atraccionList) {
			if (atraccion.getTipoAtraccion().equals(user.getAtraccionFav())) {
				atraccionesConPreferencias.add(atraccion);
			} else {
					atraccionesSinPrefenecias.add(atraccion);		
			}
		}

	}

	public void cargarAtracciones() {
		atraccionList.clear();
		String sql = ("SELECT * FROM atracciones JOIN tipoAtracciones ON tipoAtraccion_id = tipoAtracciones.id  ORDER BY costo DESC;");

		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				Integer id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Integer costoVisita = rs.getInt("costo");
				Double promedioTiempo = rs.getDouble("tiempo_hs");
				Integer cupo = rs.getInt("cupo");
				String tipoAtraccion = rs.getString("nombre_atraccion");
				Integer activo = rs.getInt("activo");

				Atraccion nuevaAtraccion = new Atraccion(id, nombre, costoVisita, promedioTiempo, cupo, tipoAtraccion,activo);
				atraccionList.add(nuevaAtraccion);

			}

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

	public static void borrarListaAtracciones() {
		atraccionList.clear();
		atraccionesSinPrefenecias.clear();
		atraccionesConPreferencias.clear();
	}

	public static ArrayList<Atraccion> getAtraccionesConPreferencias() {
		return atraccionesConPreferencias;
	}

	public static ArrayList<Atraccion> getAtraccionesSinPrefenecias() {
		return atraccionesSinPrefenecias;
	}

	public boolean isSeguir() {
		return seguir;
	}

	public void setSeguir(boolean seguir) {
		this.seguir = seguir;
	}

	public Atraccion conseguirAtraccion(Integer id) {
		String sql = ("SELECT * FROM atracciones JOIN tipoAtracciones ON tipoAtraccion_id = tipoAtracciones.id  WHERE atracciones.id="
				+ id + ";");
		int r = 0;
		try {
			con = cn.getConnection();
			// ps.setNString(1, id);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				r = 1;
				Integer ids = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Integer costoVisita = rs.getInt("costo");
				Double promedioTiempo = rs.getDouble("tiempo_hs");
				Integer cupo = rs.getInt("cupo");
				String tipoAtraccion = rs.getString("nombre_atraccion");
				Integer activo = rs.getInt("activo");
				Atraccion atraccionConseguida = new Atraccion(ids, nombre, costoVisita, promedioTiempo, cupo,
						tipoAtraccion,activo);
				return atraccionConseguida;
			}
			if (r == 0) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// Falló al cerrar la conexión
				System.err.println(e.getMessage());
			}
		}
		return null;

	}
	
	public void actualizarCupo(int idAtraccion, int totalAtracciones) {
		String sql ="UPDATE atracciones SET cupo = "+totalAtracciones+" WHERE atracciones.id = "+idAtraccion;
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	public static ArrayList<Atraccion> getAtraccionList() {
		return atraccionList;
	}

	
	
	public void actualizarAtraccion(int id,String nombre, int costo, double tiempo, int cupo , int idTipoAtraccion, int activo) {
		String sql ="UPDATE atracciones SET tipoAtraccion_id=" +idTipoAtraccion+", nombre= '"+nombre+"',costo="+costo+",tiempo_hs="+tiempo+
				",cupo="+cupo+",activo="+activo+" WHERE atracciones.id = "+id;
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	public void crearAtraccion(String nombre, int costo, double tiempo, int cupo , int idTipoAtraccion, int activo) {
		
		String sql ="INSERT INTO atracciones VALUES(null,"+idTipoAtraccion+",'"+nombre+"',"+costo+","+tiempo+
				","+cupo+","+activo+")";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
}
