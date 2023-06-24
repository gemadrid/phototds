package umu.tds.modelo.descuento;

import umu.tds.modelo.Usuario;

public interface Descuento {
	
	boolean esAplicable(Usuario usuario);
	
	double aplicarDescuento(double precio);
	
	String getNombre();

}
