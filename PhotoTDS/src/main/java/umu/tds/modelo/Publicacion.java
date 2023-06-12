package umu.tds.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Publicacion {
	
	//Atributos
	private int codigo;
	private String titulo;
	private Date fecha;
	private String descripcion;
	private int megusta;
	private List<String> hashtags;
	//Referencias
	private Usuario usuario;
	private List<Comentario> comentarios;
	
	//Constructor
	//TODO ¿Es el constructor vacío necesario?
	public Publicacion() {};
	
	public Publicacion(String titulo, String descripcion, Usuario usuario) {
		this.codigo = 0;
		this.titulo = titulo;
		//Fecha
		this.fecha = new Date();
		this.descripcion = descripcion;
		this.megusta = 0;
		//Obtener hashtags
		obtenerHashtags();
		this.usuario = usuario;
	}
	
	//Métodos privados
	private void obtenerHashtags() {
		hashtags = new ArrayList<>();
		//Patrón regex que busca hashtags
		Pattern patron = Pattern.compile("#[\\p{L}\\p{M}0-9_]{1,15}\\b");
		//Matcher para buscar el patrón en la descripción de la publicación
		Matcher matcher = patron.matcher(descripcion);
		//Mientras haya coincidencias
		while (matcher.find()) {
			String hashtag = matcher.group().substring(1);
			hashtags.add(hashtag);
		}
	}
	
	//Métodos get
	public int getMegusta() {
		return megusta;
	}
	
	public String getFotoUsuario() {
		return usuario.getFotoUsuario();
	}
	
	public String getNombreUsuario() {
		return usuario.getNombreUsuario();
	}
	
	//TODO Comprobar si es la mejor opción, o hacer método getMiniatura()
	public abstract String getPath();
	
	//Métodos (funcionalidad)
	public void darMegusta() {
		megusta++;
	}
	
	

}
