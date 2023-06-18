package umu.tds.modelo;

import java.util.ArrayList;
import java.util.List;

public class Album extends Publicacion {
	
	//Atributos
	//Referencias
	private List<Publicacion> fotos;

	//Constructor
	public Album(String titulo, String descripcion, Usuario usuario) {
		super(titulo, descripcion, usuario);
		this.fotos = new ArrayList<>();
	}
	
	//Getters y setters
	public List<Publicacion> getFotos() {
		return fotos;
	}

	public void setFotos(List<Publicacion> fotos) {
		this.fotos = fotos;
	}

	//Métodos
	public void addFoto(Publicacion foto) {
		fotos.add(foto);
	}
	
	@Override
	public void darMegusta() {
		//TODO Dar me gusta al propio ábum
		for(Publicacion foto : fotos) {
			foto.darMegusta();
		}
	}

	@Override
	public String getPath() {
		return fotos.get(0).getPath();
	}

}
