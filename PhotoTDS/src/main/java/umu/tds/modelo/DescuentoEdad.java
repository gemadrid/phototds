package umu.tds.modelo;

public class DescuentoEdad implements Descuento {
	
	private static final int EDAD_JOVEN_MIN = 18;
	private static final int EDAD_JOVEN_MAX = 25;
	
	private static final int EDAD_MAYOR = 65;
	
	private static final double DESCUENTO = 0.1;

	@Override
	public boolean esAplicable(Usuario usuario) {
		boolean aplicable = false;
		int edad = usuario.getEdad();
		if ((edad >= EDAD_JOVEN_MIN && edad <= EDAD_JOVEN_MAX) || edad >= EDAD_MAYOR)
			aplicable = true;
		return aplicable;
	}

	@Override
	public double aplicarDescuento(double precio) {
		//TODO Mejorar
		return precio - precio*DESCUENTO;
	}

}
