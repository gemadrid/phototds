package umu.tds.modelo;

import java.util.LinkedList;
import java.util.List;

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

}
