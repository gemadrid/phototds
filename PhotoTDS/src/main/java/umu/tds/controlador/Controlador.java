package umu.tds.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.ListModel;

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
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, Date fechaNacimiento, String fotoUsuario, String presentacion) {
		if (esUsuarioRegistrado(login)) return false;
		
		//TODO Guardar imagen en path local y pasarlo al constructor de Usuario
		
		LocalDate fecha = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fecha, fotoUsuario, presentacion);
		
		//TODO FactoriaDAO
		
		CatalogoUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}
	
	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.INSTANCE.findUsuario(login) != null;
	}
	
	
	
	//Métodos ventana principal (donde se muestran las fotos más recientes)
	public List<Publicacion> getPublicacionesNotificaciones(int num) {
		return usuarioActual.getPublicacionesNotificaciones(num);
	}
	
	public void darMegusta(Publicacion publicacion) {
		publicacion.darMegusta();
	}
	
	public void comentar(Publicacion publicacion, String texto) {
		publicacion.comentar(texto, usuarioActual);
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
		
		//Subimos la foto y la añadimos al catálogo de publicaciones
		Publicacion foto = usuarioActual.subirFoto(descripcion, pathRelativo);
		CatalogoPublicaciones.INSTANCE.addPublicacion(foto);
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
		//TODO Si es un álbum hay que eliminar todas las fotos que contiene
		//TODO Eliminar de la base de datos
	}

}
