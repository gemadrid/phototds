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

}
