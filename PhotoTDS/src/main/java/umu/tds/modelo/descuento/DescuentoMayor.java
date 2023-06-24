package umu.tds.modelo.descuento;

import umu.tds.modelo.Usuario;

public class DescuentoMayor implements Descuento {
	
	private static final String NOMBRE = "Mayor";
	
	private static final double DESCUENTO = 0.2;
	
	@Override
	public boolean esAplicable(Usuario usuario) {
		return usuario.isMayor();
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
