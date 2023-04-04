package umu.tds.controlador;

import umu.tds.modelo.CatalogoPublicaciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Foto;
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
	
	//Métodos subir foto
	public void subirFoto(String titulo, String descripcion, String path) {
		Foto foto = new Foto(titulo, descripcion, usuarioActual, path);
		CatalogoPublicaciones.INSTANCE.addPublicacion(foto);
		usuarioActual.addPublicacion(foto);
	}
	
	//Métodos perfil de usuario
	public String getEmailUsuario(Usuario usuario) {
		return usuario.getEmail();
	}
	
	public int getNumPublicaciones(Usuario usuario) {
		return usuario.getNumPublicaciones();
	}
	
	public int getNumSeguidores(Usuario usuario) {
		return usuario.getNumSeguidores();
	}
	
	public int getNumSeguidos(Usuario usuario) {
		//TODO Se puede hacer con expresiones lambda
		//Tenemos que obtener CatalogoUsuarios completo y buscar aquellos que tengan a "usuario" en su lista de seguidores
		return 0;
	}
	
	public String getNombreCompleto(Usuario usuario) {
		return usuario.getNombreCompleto();
	}

}
