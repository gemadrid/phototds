package umu.tds.servicios;

import umu.tds.modelo.Usuario;

public abstract class BuilderInforme {
	
	//Métodos
	public abstract void crearCabecera();
	public abstract void crearUsuario(Usuario usuario);
	public abstract void guardarInforme();

}
