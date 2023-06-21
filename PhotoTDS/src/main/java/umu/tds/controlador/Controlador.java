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
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, Date fechaNacimiento, String fotoUsuario, String presentacion) {
		if (esUsuarioRegistrado(login)) return false;
		
		String pathFotoUsuario;
		if (fotoUsuario.isEmpty()) {
			pathFotoUsuario = Controlador.class.getResource("/umu/tds/resources/fotoperfil.jpg").getPath();
		}
		else {
			pathFotoUsuario = copiarImagen(fotoUsuario, "fotosperfil/");
		}
		
		LocalDate fecha = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fecha, pathFotoUsuario, presentacion);
		
		//TODO FactoriaDAO
		
		CatalogoUsuarios.INSTANCE.addUsuario(usuario);
		return true;
	}
	
	public boolean esUsuarioRegistrado(String login) {
		return CatalogoUsuarios.INSTANCE.findUsuario(login) != null;
	}
	
	
	
	//Métodos ventana principal (donde se muestran las fotos más recientes)
	public String getFotoUsuarioActual() {
		return usuarioActual.getFotoUsuario();
	}
	
	public List<Publicacion> getPublicacionesNotificaciones(int num) {
		return usuarioActual.getPublicacionesNotificaciones(num);
	}
	
	public void darMegusta(Publicacion publicacion) {
		publicacion.darMegusta();
	}
	
	public void publicarComentario(Publicacion publicacion, String texto) {
		publicacion.publicarComentario(texto, usuarioActual);
	}
	
	
	
	//Métodos subir foto
	public void subirFoto(String descripcion, String path) {
		//Movemos la foto a un path relativo (dentro de fotos/)
		String pathRelativo = copiarImagen(path, "fotos/");
		
		//Subimos la foto y la añadimos al catálogo de publicaciones
		Publicacion foto = usuarioActual.subirFoto(descripcion, pathRelativo);
		CatalogoPublicaciones.INSTANCE.addPublicacion(foto);
	}
	
	
	
	//Métodos búsqueda
	public List<Usuario> buscarUsuarios(String nombre) {
		return CatalogoUsuarios.INSTANCE.getUsuariosPorNombre(nombre);
	}
	
	
	
	//Métodos perfil de usuario
	public int getNumSeguidos(Usuario usuario) {
		return CatalogoUsuarios.INSTANCE.getUsuariosSeguidos(usuario).size();
	}
	
	public boolean esUsuarioActual(Usuario usuario) {
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
	
	
	
	//Método para copiar una imagen a un path del proyecto
	private String copiarImagen(String pathImagen, String pathFolderDestino) {
		File from = new File(pathImagen);
		File to = new File(pathFolderDestino);
		try {
			Files.copy(
					from.toPath(),
					Paths.get(to.getAbsolutePath() + "\\" + from.getName()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String pathRelativo = pathFolderDestino + from.getName();
		
		return pathRelativo;
	}

}
