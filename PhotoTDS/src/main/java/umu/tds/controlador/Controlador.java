package umu.tds.controlador;

import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Usuario;

public enum Controlador {
	INSTANCE;
	
	//Atributos
	private Usuario usuarioActual;
	//private FactoriaDAO factoria;
	
	//Constructor
	private Controlador() {
		usuarioActual = null;
		//TODO getInstancia() FactoriaDAO
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	//Métodos Login
	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = CatalogoUsuarios.INSTANCE.findUsuario(nombre);
		if (usuario != null && usuario.getPassword().equals(password)) {
			usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	//Métodos Registro
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, String fechaNacimiento) {
		if (esUsuarioRegistrado(login)) return false;
		
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);
		
		//TODO FactoriaDAO
		
		CatalogoUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}
	
	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.INSTANCE.findUsuario(login) != null;
	}

}
