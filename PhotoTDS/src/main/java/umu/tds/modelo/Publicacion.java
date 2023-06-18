package umu.tds.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Publicacion {
	
	//Atributos
	private int codigo;
	private String titulo;
	private LocalDate fecha;
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
		this.fecha = LocalDate.now();
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
	
	//Getters y setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public int getMegusta() {
		return megusta;
	}
	
	public void setMegusta(int megusta) {
		this.megusta = megusta;
	}
	
	//Otros get
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
	
	public void addComentario(Comentario comentario) {
		comentarios.add(comentario);
	}
	
	

}
