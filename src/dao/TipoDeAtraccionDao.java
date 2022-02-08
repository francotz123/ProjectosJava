package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Conection;
import model.TipoAtraccion;

public class TipoDeAtraccionDao {
	
	boolean seguir;
	Connection con;
	Conection cn = new Conection();
	PreparedStatement ps;
	ResultSet rs;
	int r = 0;
	
	static ArrayList<TipoAtraccion> tiposAtraccionesList = new ArrayList<TipoAtraccion>();
	
	public void cargarTiposAtracciones() {
		tiposAtraccionesList.clear();
		String sql = ("SELECT * FROM tipoAtracciones;");

		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				Integer id = rs.getInt("id");
				String nombre = rs.getString("nombre_atraccion");

				TipoAtraccion nuevoTipoAtraccion = new TipoAtraccion(id, nombre);
				tiposAtraccionesList.add(nuevoTipoAtraccion);
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
	
	public TipoAtraccion conseguirTipoAtraccion(Integer id){
		String sql = ("SELECT * FROM tipoAtracciones WHERE id="+id);
		try {
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Integer id1 = rs.getInt("id");
				String nombre = rs.getString("nombre_atraccion");
				TipoAtraccion nuevoTipo = new TipoAtraccion(id1,nombre);
				return nuevoTipo;
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
		return null;
	}

	public void actualizarTipoAtraccion(int id, String nombre) {
		String sql ="UPDATE tipoAtracciones SET nombre_atraccion = '"+nombre+"' WHERE tipoAtracciones.id = "+id;
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
	
	public static ArrayList<TipoAtraccion> getTiposAtraccionesList() {
		return tiposAtraccionesList;
	}
	
	
	public void crearTipoAtraccion(String nombre) {
		String sql ="INSERT INTO tipoAtracciones VALUES(null,'"+nombre+"')";
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
