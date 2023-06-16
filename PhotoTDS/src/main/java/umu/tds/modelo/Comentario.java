package umu.tds.modelo;

import java.time.LocalDate;

public class Comentario {
	
	//Atributos
	private int codigo;
	private String texto;
	private LocalDate fecha;
	
	//Referencias
	private Usuario usuario;
	
	//Constructor
	public Comentario(String texto, Usuario usuario) {
		this.codigo = 0;
		this.texto = texto;
		this.fecha = LocalDate.now();
		this.usuario = usuario;
	}

	//Get y set
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
