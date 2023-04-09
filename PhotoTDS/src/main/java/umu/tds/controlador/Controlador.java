package umu.tds.controlador;

import umu.tds.modelo.CatalogoPublicaciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Foto;
import umu.tds.modelo.Publicacion;
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
	//TODO Añadir foto --> Guardar en umu.tds.resources.usuarios y pasarle el path al usuario al crearlo
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, String fechaNacimiento, String fotoUsuario, String presentacion) {
		if (esUsuarioRegistrado(login)) return false;
		
		//TODO Guardar imagen en path local y pasarlo al constructor de Usuario
		
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento, fotoUsuario, presentacion);
		
		//TODO FactoriaDAO
		
		CatalogoUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}
	
	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.INSTANCE.findUsuario(login) != null;
	}
	
	
	
	//Métodos ventana principal (donde se muestran las fotos más recientes)
	public String getPathPublicacion(Publicacion publicacion) {
		return publicacion.getPath();
	}
	
	public int getNumMegusta(Publicacion publicacion) {
		return publicacion.getMegusta();
	}
	
	public String getFotoUsuario(Publicacion publicacion) {
		return publicacion.getFotoUsuario();
	}
	
	public String getNombreUsuario(Publicacion publicacion) {
		return publicacion.getNombreUsuario();
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
