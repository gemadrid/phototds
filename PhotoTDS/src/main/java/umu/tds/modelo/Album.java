package umu.tds.modelo;

import java.util.List;

public class Album extends Publicacion {
	
	//Atributos
	//Referencias
	private List<Foto> fotos;
	
	//Constructor

	//Métodos
	@Override
	public void darMegusta() {
		for(Foto foto : fotos) {
			foto.darMegusta();
		}
	}

}
