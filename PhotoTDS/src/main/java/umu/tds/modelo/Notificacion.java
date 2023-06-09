package umu.tds.modelo;

import java.util.Date;

public class Notificacion {
	
	//Atributos
	private int codigo;
	private Date fecha;
	
	//Referencias
	private Publicacion publicacion;
	
	public Notificacion(Publicacion publicacion) {
		this.fecha = new Date();
		this.publicacion = publicacion;
	}

}
