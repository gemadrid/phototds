package umu.tds.modelo;

import java.util.List;

public class Usuario {
	
	//Atributos
	private int codigo;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private String fechaNacimiento;
	//private ? foto (path)
	private boolean isPremium;
	//Referencias
	private Descuento descuento;
	private List<Publicacion> publicaciones;
	private List<Usuario> seguidores;
	private List<Notificacion> notificaciones;
	
	//Constructor
	
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
	public boolean isPremium() {
		return isPremium;
	}
	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}
	public Descuento getDescuento() {
		return descuento;
	}
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}
	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}
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

}
