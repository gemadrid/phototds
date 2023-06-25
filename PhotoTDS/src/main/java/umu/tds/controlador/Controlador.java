package umu.tds.controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.fotos.CargadorFotos;
import umu.tds.fotos.Foto;
import umu.tds.fotos.Fotos;
import umu.tds.fotos.FotosEvent;
import umu.tds.fotos.FotosListener;
import umu.tds.modelo.Album;
import umu.tds.modelo.CatalogoPublicaciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.Comentario;
import umu.tds.modelo.Notificacion;
import umu.tds.modelo.Publicacion;
import umu.tds.modelo.Usuario;
import umu.tds.modelo.descuento.Descuento;
import umu.tds.modelo.descuento.DescuentoJoven;
import umu.tds.modelo.descuento.DescuentoMayor;
import umu.tds.modelo.descuento.DescuentoPopularidad;
import umu.tds.persistencia.ComentarioDAO;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.persistencia.NotificacionDAO;
import umu.tds.persistencia.PublicacionDAO;
import umu.tds.persistencia.UsuarioDAO;
import umu.tds.servicios.BuilderExcel;
import umu.tds.servicios.BuilderPDF;
import umu.tds.servicios.GeneradorInforme;

public enum Controlador implements FotosListener {
	INSTANCE;
	
	private static final double PRECIO_PREMIUM = 8;
	
	//Atributos
	private Usuario usuarioActual;
	//private FactoriaDAO factoria;
	
	//Atributos álbum
	private boolean isModoAlbum;
	private Publicacion publicacionAlbum;
	
	//Descuentos disponibles
	private List<Descuento> descuentosDisponibles;
	
	//Componente CargadorFotos
	CargadorFotos cargadorFotos;
	
	//Adaptadores DAO
	private UsuarioDAO usuarioDAO;
	private PublicacionDAO publicacionDAO;
	private NotificacionDAO notificacionDAO;
	private ComentarioDAO comentarioDAO;
	
	
	
	//Constructor
	private Controlador() {
		//Adaptadores
		inicializarAdaptadores();
		
		usuarioActual = null;
		
		//Cargador fotos
		cargadorFotos = new CargadorFotos();
		cargadorFotos.addFotosListener(this);
		
		//Descuentos
		inicializarDescuentos();
	}
	
	//Inicializaciones
	private void inicializarDescuentos() {
		descuentosDisponibles = new LinkedList<>();
		Collections.addAll(descuentosDisponibles, new DescuentoJoven(), new DescuentoMayor(), new DescuentoPopularidad());
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		usuarioDAO = factoria.getUsuarioDAO();
		publicacionDAO = factoria.getPublicacionDAO();
		notificacionDAO = factoria.getNotificacionDAO();
		comentarioDAO = factoria.getComentarioDAO();
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
		
		//Registrar usuario
		usuarioDAO.create(usuario);
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
		publicacionDAO.update(publicacion);
		//TODO Si la publicación es un álbum también hay que actualizar sus fotos
		if (publicacion instanceof Album)
			((Album)publicacion).getFotos().forEach(f -> publicacionDAO.update(f));
	}
	
	public void publicarComentario(Publicacion publicacion, String texto) {
		Comentario comentario = publicacion.publicarComentario(texto, usuarioActual);
		comentarioDAO.create(comentario);
		publicacionDAO.update(publicacion);
	}
	
	
	
	//Subir foto
	public Publicacion subirFoto(String descripcion, String path) {
		//Movemos la foto a un path relativo (dentro de fotos/)
		String pathRelativo = copiarImagen(path, "fotos/");
		
		//Subimos la foto y la añadimos al catálogo de publicaciones
		Publicacion foto = usuarioActual.subirFoto(descripcion, pathRelativo);
		publicacionDAO.create(foto);
		CatalogoPublicaciones.INSTANCE.addPublicacion(foto);
		//Creamos la notificación
		Notificacion notificacion = usuarioActual.notificar(foto);
		notificacionDAO.create(notificacion);
		//Actualizamos el usuario y sus seguidores
		usuarioDAO.update(usuarioActual);
		usuarioActual.getSeguidores().forEach(s -> usuarioDAO.update(s));
		
		//Si la foto es para un álbum, guardamos el objeto para luego añadirlo al álbum
		if (isModoAlbum)
			publicacionAlbum = foto;
		
		return foto;
	}
	
	//Indicar que se está trabajando sobre un álbum
	public void modoAlbum() {
		isModoAlbum = true;
	}
	
	//
	public void crearAlbum(String tituloAlbum) {
		//Creamos el álbum (si se ha subido la primera foto) y lo añadimos al catálogo de publicaciones
		if (publicacionAlbum != null) {
			Publicacion album = usuarioActual.crearAlbum(tituloAlbum, publicacionAlbum);
			publicacionDAO.create(album);
			CatalogoPublicaciones.INSTANCE.addPublicacion(album);
			usuarioDAO.update(usuarioActual);
		}
		publicacionAlbum = null;
		isModoAlbum = false;
	}
	
	public void addPublicacionAlbum(Publicacion album) {
		if (publicacionAlbum != null) {
			usuarioActual.addFotoAlbum(album, publicacionAlbum);
			publicacionDAO.update(album);
			usuarioDAO.update(usuarioActual);
		}
		publicacionAlbum = null;
		isModoAlbum = false;
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
	
	public void editarPerfil(String pathFoto, String password, String presentacion) {
		String pathRelativo = pathFoto;
		if (!pathFoto.isEmpty()) {
			pathRelativo = copiarImagen(pathFoto, "fotosperfil/");
		}
		usuarioActual.editarPerfil(pathRelativo, password, presentacion);
		usuarioDAO.update(usuarioActual);
	}
	
	public void seguirUsuario(Usuario usuario) {
		usuario.addSeguidor(usuarioActual);
		usuarioDAO.update(usuario);
	}
	
	public boolean esTituloAlbumValido(String titulo) {
		return !titulo.isEmpty() && usuarioActual.esTituloAlbumValido(titulo);
	}
	
	public void eliminarPublicacion(Publicacion publicacion) {
		//TODO CatalogoPublicaciones.INSTANCE.removePublicacion(publicacion);
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
	
	
	
	//Funcionalidad premium
	public List<String> getDescuentosAplicables() {
		return descuentosDisponibles.stream()
					.filter(d -> d.esAplicable(usuarioActual))
					.map(d -> d.getNombre())
					.collect(Collectors.toList());
	}
	
	public double hacersePremium(String nombreDescuento) {
		usuarioActual.hacersePremium(nombreDescuento);
		usuarioDAO.update(usuarioActual);
		return usuarioActual.calcularDescuento(PRECIO_PREMIUM);
	}
	
	public void generarPDF() {
		GeneradorInforme.generarInforme(usuarioActual, new BuilderPDF());
	}
	
	public void generarExcel() {
		GeneradorInforme.generarInforme(usuarioActual, new BuilderExcel());
	}
	
	public List<Publicacion> getTopMeGusta() {
		return usuarioActual.getTopPublicaciones();
	}
	
	
	
	//Funcionalidad CargadorFotos
	public void cargarFotos(String archivoXml) {
		cargadorFotos.setArchivoFotos(archivoXml);
	}

	@Override
	public void notificaNuevasFotos(EventObject evento) {
		if (evento instanceof FotosEvent) {
			Fotos fotos = ((FotosEvent)evento).getFotos();
			extraerNuevasFotos(fotos);
		}
	}
	
	private void extraerNuevasFotos(Fotos fotos) {
		List<Foto> listaFoto = fotos.getFoto();
		for (Foto foto : listaFoto) {
			//Si la foto existe la subimos
			if (new File(foto.getPath()).exists()) {
				Publicacion publicacion = subirFoto(foto.getDescripcion(), foto.getPath());
				publicacion.setTitulo(foto.getTitulo());
				publicacion.setHashtags(foto.getHashTags().stream()
											.flatMap(h -> h.getHashTag().stream())
											.collect(Collectors.toList()));
			}
		}
	}

}
