package umu.tds.modelo;

public class DescuentoPopularidad implements Descuento {
	
	private static final int NUM_FOTOS = 20;
	private static final int NUM_MEGUSTA = 50;
	
	private static final double DESCUENTO = 0.05;

	@Override
	public boolean esAplicable(Usuario usuario) {
		long numPublicaciones = usuario.getPublicaciones().stream()
									.filter(p -> p.getMegusta() >= NUM_MEGUSTA)
									.count();
		return numPublicaciones >= NUM_FOTOS;
	}

	@Override
	public double aplicarDescuento(double precio) {
		//TODO Mejorar
		return precio - precio*DESCUENTO;
	}

}
