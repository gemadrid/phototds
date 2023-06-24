package umu.tds.modelo.descuento;

import umu.tds.modelo.Usuario;

public class DescuentoJoven implements Descuento {
	
	private static final String NOMBRE = "Joven";
	
	private static final double DESCUENTO = 0.25;
	
	@Override
	public boolean esAplicable(Usuario usuario) {
		return usuario.isJoven();
	}

	@Override
	public double aplicarDescuento(double precio) {
		return precio - precio*DESCUENTO;
	}
	
	@Override
	public String getNombre() {
		return NOMBRE;
	}

}
