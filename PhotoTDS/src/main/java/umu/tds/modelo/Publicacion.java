package umu.tds.modelo;

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

}
