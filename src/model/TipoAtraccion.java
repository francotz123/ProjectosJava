package model;

public class TipoAtraccion {
	private Integer id ;
	private String nombreTipoAtraccion;
	
	public TipoAtraccion(Integer id, String nombreTipoAtraccion) {
		this.id = id;
		this.nombreTipoAtraccion = nombreTipoAtraccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreTipoAtraccion() {
		return nombreTipoAtraccion;
	}

	public void setNombreTipoAtraccion(String nombreTipoAtraccion) {
		this.nombreTipoAtraccion = nombreTipoAtraccion;
	}
	
}
