package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Atraccion;
import model.Conection;
import model.Promocion;
import model.Usuario;
import model.validar;

public class UsuarioDao implements validar {

	Connection con;
	Conection cn = new Conection();
	PreparedStatement ps;
	ResultSet rs;
	int r = 0;
	
	static ArrayList<Usuario> usuariosList = new ArrayList<Usuario>();
	
	@Override
	public int validar(Usuario per) {
		
		String sql ="SELECT usuarios.*, tipoAtracciones.nombre_atraccion AS 'atraccionFav'  FROM usuarios\r\n"
				+ "JOIN tipoAtracciones ON usuarios.atraccionFav_id = tipoAtracciones.id WHERE usuarios.nombre =? AND usuarios.password=?";

		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, per.getNombre());
			ps.setString(2, per.getPassword());
			rs = ps.executeQuery();
			while(rs.next()) {
				r=r+1;
				 per.setId(rs.getInt("id"));
				 per.setNombre(rs.getString("nombre"));
				 per.setPresupuesto(rs.getInt("presupuesto"));
				 per.setTimpoDisponible(rs.getDouble("tiempoDisponible"));
				 per.setIsAdmin(rs.getInt("is_admin"));
				 per.setAtraccionFav(rs.getString("atraccionFav"));		 
			}
			
			if(r==1) {
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
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
	
	public Usuario actualizarUsario(Usuario per) {
		
		String sql ="SELECT usuarios.*, tipoAtracciones.nombre_atraccion AS 'atraccionFav'  FROM usuarios\r\n"
				+ "JOIN tipoAtracciones ON usuarios.atraccionFav_id = tipoAtracciones.id WHERE usuarios.nombre =?";
		
		
		String sql2 ="SELECT itinerarios_detalles_atracciones.atraccion_id FROM  itinerarios_cabecera\r\n"
				+ "JOIN itinerarios_detalles_atracciones ON itinerarios_detalles_atracciones.itinerario_cabecera_id = itinerarios_cabecera.id\r\n"
				+ "WHERE itinerarios_cabecera.usuario_id = "+per.getId();
		
		String sql3 ="SELECT itinerarios_detalles_promociones.promocion_id FROM  itinerarios_cabecera\r\n"
				+ "JOIN itinerarios_detalles_promociones ON itinerarios_detalles_promociones.itinerario_cabecera_id = itinerarios_cabecera.id\r\n"
				+ "WHERE itinerarios_cabecera.usuario_id = "+per.getId();
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, per.getNombre());
			rs = ps.executeQuery();
			while(rs.next()) {
				r=r+1;
				 per.setId(rs.getInt("id"));
				 per.setNombre(rs.getString("nombre"));
				 per.setPresupuesto(rs.getInt("presupuesto"));
				 per.setTimpoDisponible(rs.getDouble("tiempoDisponible"));
				 per.setAtraccionFav(rs.getString("atraccionFav"));
			}
			
			con = cn.getConnection();
			ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			per.borrarAtraccionesCompradas();
			while(rs.next()) {
				Integer idAtraccion = rs.getInt("atraccion_id");
				for (Atraccion atraccion : AtraccionDao.atraccionList) {
					if(atraccion.getId().equals(idAtraccion)) {
						per.setAtraccionComprada(atraccion);
					}
				}
			}
			
			ps = con.prepareStatement(sql3);
			rs = ps.executeQuery();
			per.borrarPromocionesCompradas();
			while(rs.next()) {
				Integer idPromocion = rs.getInt("promocion_id");
				for (Promocion promocion : PromocionesDao.promocionList) {
					if(promocion.getId().equals(idPromocion)) {
						per.setPromocionesCompradas(promocion);
					}
				}
			}
			
			if(r==1) {
				return per;
			}else {
				return null;
			}

		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// Falló al cerrar la conexión
				System.err.println(e.getMessage());
			}
		}
	}
	
	public void actualizarUsuarioDB(Usuario user) {
		String sql ="UPDATE usuarios SET presupuesto = "+user.getPresupuesto()+", tiempoDisponible = "+user.getTimpoDisponible()
		+" WHERE usuarios.id = "+user.getId();
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
	
	public void agregarAtraccionComprada(int idAtraccion, int idUsuario) {
		
		Integer idItinerario = null;
		String sql ="INSERT INTO itinerarios_cabecera VALUES (null,"+idUsuario+")";
		String sql2 = "SELECT id FROM itinerarios_cabecera ORDER BY id DESC LIMIT 1";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
			//Obtener ultima entrada de el itinerario
			ps = con.prepareStatement(sql2);
			rs = ps.executeQuery();
			idItinerario = rs.getInt("id");

			//Insetar AtraccionComprada
			String sql3 = "INSERT INTO itinerarios_detalles_atracciones VALUES(null,"+idItinerario+","+idAtraccion+")";
			ps = con.prepareStatement(sql3);
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


public void agregarPromocionComprada(int idPromocion, int idUsuario) {
	
	Integer idItinerario = null;
	String sql ="INSERT INTO itinerarios_cabecera VALUES (null,"+idUsuario+")";
	String sql2 = "SELECT id FROM itinerarios_cabecera ORDER BY id DESC LIMIT 1";
	try {
		con = cn.getConnection();
		ps = con.prepareStatement(sql);
		ps.executeUpdate();
		
		//Obtener ultima entrada de el itinerario
		ps = con.prepareStatement(sql2);
		rs = ps.executeQuery();
		idItinerario = rs.getInt("id");

		//Insetar AtraccionComprada
		String sql3 = "INSERT INTO itinerarios_detalles_promociones VALUES(null,"+idItinerario+","+idPromocion+")";
		ps = con.prepareStatement(sql3);
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
	public ArrayList<Usuario> getUsuariosList(){
		
		usuariosList.clear();
		String sql ="SELECT usuarios.*, tipoAtracciones.nombre_atraccion AS 'atraccionFav'  FROM usuarios\r\n"
				+ "JOIN tipoAtracciones ON usuarios.atraccionFav_id = tipoAtracciones.id";
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				 Usuario per = new Usuario();
				 per.setId(rs.getInt("id"));
				 per.setNombre(rs.getString("nombre"));
				 per.setPresupuesto(rs.getInt("presupuesto"));
				 per.setTimpoDisponible(rs.getDouble("tiempoDisponible"));
				 per.setAtraccionFav(rs.getString("atraccionFav"));
				 per.setIsAdmin(rs.getInt("is_admin"));
				 usuariosList.add(per);
			}
			
			return usuariosList;
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
	
	public Usuario conseguirUsuario(int id) {
		String sql ="SELECT usuarios.*, tipoAtracciones.nombre_atraccion AS 'atraccionFav'  FROM usuarios\r\n"
				+ "JOIN tipoAtracciones ON usuarios.atraccionFav_id = tipoAtracciones.id WHERE usuarios.id="+id;
		
		try {
			con = cn.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			Usuario per = new Usuario();
			while(rs.next()) {
				 per.setId(rs.getInt("id"));
				 per.setNombre(rs.getString("nombre"));
				 per.setPresupuesto(rs.getInt("presupuesto"));
				 per.setTimpoDisponible(rs.getDouble("tiempoDisponible"));
				 per.setAtraccionFav(rs.getString("atraccionFav"));
				 per.setIsAdmin(rs.getInt("is_admin"));
			}
			return per;
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
	
	public void actualizarUsuarioDB2(int id,String nombre, int presupuesto, double tiempo , int isAdmin, int atraccionFav) {
		String sql ="UPDATE usuarios SET atraccionFav_id=" +atraccionFav+", nombre= '"+nombre+"',presupuesto="+presupuesto+",tiempoDisponible="+tiempo+
				",is_admin="+isAdmin+" WHERE id="+id;
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
	
	public void crearUsuario(String nombre, int presupuesto, double tiempo , int isAdmin, int atraccionFav , String password) {
		System.out.println("entro");
		String sql ="INSERT INTO usuarios VALUES(null,"+atraccionFav+",'"+nombre+"','"+password+"',"+presupuesto+","+tiempo+","+isAdmin+")";
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
