package umu.tds.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Usuario {
	
	//TODO Cambiar fechaNacimiento de String a Date
	
	//Atributos
	private int codigo;
	
	private String nombre;
	private String apellidos;
	private String email;
	private String nombreUsuario;
	private String password;
	private String fechaNacimiento;
	//TODO Cambiar fecha de nacimiento a LocalDate
	private LocalDate nacimiento;
	
	private String fotoUsuario;
	private String presentacion;
	
	private boolean isPremium;
	
	//Referencias
	private Descuento descuento;
	private List<Publicacion> publicaciones;
	private List<Usuario> seguidores;
	private List<Notificacion> notificaciones;
	
	
	//Constructor
	public Usuario(String nombre, String apellidos, String email, String nombreUsuario, String password, String fechaNacimiento, String fotoUsuario, String presentacion) {
		this.codigo = 0;
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.fotoUsuario = fotoUsuario;
		this.presentacion = presentacion;
		
		isPremium = false;
		
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	//Get de las colecciones
	public List<Publicacion> getPublicaciones() {
		return Collections.unmodifiableList(publicaciones);
	}
	
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
	
	
	
	//Métodos get derivados
	public int getEdad() {
		return Period.between(nacimiento, LocalDate.now()).getYears();
	}
	
	
	
	//Métodos
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

	public String getFotoUsuario() {
		return fotoUsuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void addSeguidor(Usuario usuario) {
		seguidores.add(usuario);
	}

}
