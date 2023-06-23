package umu.tds.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class Usuario {
	
	//TODO Cambiar fechaNacimiento de String a Date
	
	//Atributos
	private int codigo;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String nombreUsuario;
	private String password;
	private LocalDate fechaNacimiento;
	
	private String fotoUsuario;
	private String presentacion;
	
	private boolean premium;
	
	//Referencias
	private Descuento descuento;
	private List<Publicacion> publicaciones;
	private List<Usuario> seguidores;
	private List<Notificacion> notificaciones;
	
	
	//Constructor
	public Usuario(String nombre, String apellidos, String email, String nombreUsuario, String password, LocalDate fechaNacimiento, String fotoUsuario, String presentacion) {
		this.codigo = 0;
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.fotoUsuario = fotoUsuario;
		this.presentacion = presentacion;
		
		premium = false;
		
		publicaciones = new LinkedList<>();
		seguidores = new LinkedList<>();
		notificaciones = new LinkedList<>();	
	}
	
	//Métodos get y set
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getFotoUsuario() {
		return fotoUsuario;
	}
	
	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	
	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	//Getters y setters de las colecciones
	public List<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
	
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	
	
	//Get (fotos y álbumes)
	public List<Publicacion> getFotos() {
		return publicaciones.stream()
				.filter(Foto.class::isInstance)
				.collect(Collectors.toList());
	}
	
	public List<Publicacion> getAlbumes() {
		return publicaciones.stream()
				.filter(Album.class::isInstance)
				.collect(Collectors.toList());	
	}
	
	public List<Publicacion> getPublicacionesNotificaciones(int num) {
		return notificaciones.stream()
				.sorted(Comparator.comparing(Notificacion::getFecha).reversed())
				.limit(num)
				.map(Notificacion::getPublicacion)
				.collect(Collectors.toList());
	}
	
	//Get Top 10 Fotos con más megusta
	public List<Publicacion> getTopPublicaciones() {
		return publicaciones.stream()
				.sorted(Comparator.comparing(Publicacion::getMegusta).reversed())
				.limit(10)
				.collect(Collectors.toList());
	}
	
	
	
	//Métodos get derivados
	public int getEdad() {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}
	
	
	
	//Métodos subir foto
	public Publicacion subirFoto(String descripcion, String path) {
		//Creamos la foto y la añadimos a la colección de publicaciones del usuario
		Publicacion foto = new Foto("", descripcion, this, path);
		addPublicacion(foto);
		//Notificamos de la subida de la foto
		notificar(foto);
		//Devolvemos la foto
		return foto;
	}
	
	public Publicacion crearAlbum(String titulo, Publicacion primeraFoto) {
		//Creamos el album y lo añadimos a la colección de publicaciones
		Album album = new Album(titulo, "", this);
		addPublicacion(album);
		//Añadimos la primera foto
		album.addFoto(primeraFoto);
		//Devolvemos el album
		return album;
	}
	
	public void addFotoAlbum(Publicacion album, Publicacion foto) {
		//TODO Ver si hay mejor forma de hacerlo
		((Album)album).addFoto(foto);
	}
	
	public void addPublicacion(Publicacion publicacion) {
		publicaciones.add(publicacion);
	}
	
	public void notificar(Publicacion publicacion) {
		//Creamos la notificación
		Notificacion notificacion = new Notificacion(publicacion);
		//Añadimos la notificación al propio usuario
		addNotificacion(notificacion);
		//Mandamos la notificación a los seguidores del usuario
		seguidores.forEach(usuario -> usuario.addNotificacion(notificacion));
	}
	
	public void addNotificacion(Notificacion notificacion) {
		notificaciones.add(notificacion);
	}
	
	
	
	//Métodos perfil de usuario
	public int getNumPublicaciones() {
		return publicaciones.size();
	}
	
	public int getNumSeguidores() {
		return seguidores.size();
	}
	
	public boolean isUsuarioSeguidor(Usuario usuario) {
		return seguidores.contains(usuario);
	}
	
	public String getNombreCompleto() {
		return nombre + " " + apellidos;
	}
	
	public void addSeguidor(Usuario usuario) {
		seguidores.add(usuario);
	}
	
	public boolean esTituloAlbumValido(String titulo) {
		return getAlbumes().stream()
				.noneMatch(a -> a.isTitulo(titulo));
	}
	
	public void editarPerfil(String pathFotoPerfil, String password, String presentacion) {
		if (!pathFotoPerfil.isEmpty())
			setFotoUsuario(pathFotoPerfil);
		if (!password.isEmpty())
			setPassword(password);
		setPresentacion(presentacion);
	}

}
