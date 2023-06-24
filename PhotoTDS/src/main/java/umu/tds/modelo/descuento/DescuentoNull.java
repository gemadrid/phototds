package umu.tds.modelo.descuento;

import umu.tds.modelo.Usuario;

public class DescuentoNull implements Descuento {
	
	private static final String NOMBRE = "Null";
	
	@Override
	public boolean esAplicable(Usuario usuario) {
		return true;
	}

	@Override
	public double aplicarDescuento(double precio) {
		return precio;
	}
	
	public String getNombre() {
		return NOMBRE;
	}

}
