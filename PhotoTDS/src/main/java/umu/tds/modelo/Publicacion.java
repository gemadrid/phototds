package umu.tds.modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public abstract class Publicacion {
	
	//Atributos
	private int codigo;
	private String titulo;
	private String fecha;
	private String descripcion;
	private int megusta;
	private List<String> hashtags;
	//Referencias
	private Usuario usuario;
	private List<Comentario> comentarios;
	
	//Constructor
	public Publicacion() {};
	
	public Publicacion(String titulo, String descripcion, Usuario usuario) {
		this.codigo = 0;
		this.titulo = titulo;
		//Fecha
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.fecha = dateFormat.format(LocalDate.now());
		this.megusta = 0;
		this.descripcion = descripcion;
		//Obtener hashtags
		obtenerHashtags();
		this.usuario = usuario;
	}
	
	//Métodos
	private void obtenerHashtags() {
		
	}
	
	

}
