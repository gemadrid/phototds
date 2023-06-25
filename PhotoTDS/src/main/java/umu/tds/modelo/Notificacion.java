package umu.tds.modelo;

import java.time.LocalDate;

public class Notificacion {
	
	//Atributos
	private int codigo;
	private LocalDate fecha;
	
	//Referencias
	private Publicacion publicacion;
	
	//Constructor
	public Notificacion(Publicacion publicacion) {
		this.codigo = 0;
		this.fecha = LocalDate.now();
		this.publicacion = publicacion;
	}

	//Getters y setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	
	public boolean isPublicacion(Publicacion publicacion) {
		return this.publicacion == publicacion;
	}

}
