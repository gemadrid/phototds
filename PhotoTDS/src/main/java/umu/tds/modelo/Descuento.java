package umu.tds.modelo;

public interface Descuento {
	
	boolean esAplicable(Usuario usuario);
	
	double aplicarDescuento(double precio);

}
