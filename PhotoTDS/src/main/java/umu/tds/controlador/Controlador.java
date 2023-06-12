package umu.tds.controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.eclipse.persistence.internal.jpa.querydef.PathImpl;

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
	public void darMegusta(Publicacion publicacion) {
		publicacion.darMegusta();
	}
	
	
	
	//Métodos subir foto
	public void subirFoto(String descripcion, String path) {
		//Movemos la foto a un path relativo (dentro de /umu/tds/fotos)
		//TODO Comprobar que esté bien (¿mover funcionalidad a clase publicación?)
		File from = new File(path);
		File to = new File("fotos/");
		try {
			Files.copy(
					from.toPath(),
					Paths.get(to.getAbsolutePath() + "\\" + from.getName()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String pathRelativo = "fotos/" + from.getName();
		
		//Creamos la foto y la añadimos al catálogo y a la lista del usuario que ha subido la foto
		Publicacion foto = new Foto("", descripcion, usuarioActual, pathRelativo);
		CatalogoPublicaciones.INSTANCE.addPublicacion(foto);
		usuarioActual.addPublicacion(foto);
		
		//Creamos una notificación para los seguidores del usuario
		usuarioActual.notificar(foto);
	}
	
	
	
	//Métodos perfil de usuario
	public int getNumSeguidos(Usuario usuario) {
		//TODO Se puede hacer con expresiones lambda
		//Tenemos que obtener CatalogoUsuarios completo y buscar aquellos que tengan a "usuario" en su lista de seguidores
		return 0;
	}
	
	public boolean esUsuarioActual(Usuario usuario) {
		//TODO Comprobar si está bien
		return usuario == usuarioActual;
	}
	
	public void seguirUsuario(Usuario usuario) {
		usuario.addSeguidor(usuarioActual);
	}
	
	public void eliminarPublicacion(Publicacion publicacion) {
		CatalogoPublicaciones.INSTANCE.removePublicacion(publicacion);
		//TODO Eliminar de la base de datos
	}

}
