package umu.tds.modelo.descuento;

import umu.tds.modelo.Usuario;

public class DescuentoPopularidad implements Descuento {
	
	private static final String NOMBRE = "Popularidad";
	
	private static final double DESCUENTO = 0.1;
	
	@Override
	public boolean esAplicable(Usuario usuario) {
		return usuario.isPopular();
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
