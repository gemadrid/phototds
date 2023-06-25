package umu.tds.modelo.descuento;

public class FactoriaDescuento {
	
	//Descuento (path común)
	private static final String DESCUENTO = "umu.tds.modelo.descuento.Descuento";
	
	//Singleton
	private static FactoriaDescuento unicaInstancia = null;
	
	//getInstancia
	public static FactoriaDescuento getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new FactoriaDescuento();
		return unicaInstancia;
	}
	
	//Constructor vacío
	private FactoriaDescuento() {}
	
	//Crear descuento por defecto
	public Descuento crearDescuento() {
		return crearDescuento("Null");
	}
	
	//Crear descuento dado un tipo
	public Descuento crearDescuento(String tipoDescuento) {
		Descuento descuento;
		try {
			descuento = (Descuento) Class.forName(DESCUENTO + tipoDescuento).newInstance();
		} catch (Exception e) {
			descuento = new DescuentoNull();
			e.printStackTrace();
		}
		return descuento;
	}
	

}
