package umu.tds.modelo;

public class Foto extends Publicacion {

	//Atributos
	private String path;
	
	//Constructor
	public Foto(String titulo, String descripcion, Usuario usuario, String path) {
		super(titulo, descripcion, usuario);
		this.path = path;
	}

	//Métodos
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
