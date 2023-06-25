package umu.tds;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import umu.tds.modelo.descuento.DescuentoJoven;
import umu.tds.modelo.descuento.DescuentoNull;


public class TestDescuento {
	
	@Test
	public void testDescuentoJoven() {
		double precio = 8.0;
		double precioEsperado = 6.0;
		assertEquals(precioEsperado, new DescuentoJoven().aplicarDescuento(precio), 0);
	}
	
	@Test
	public void testDescuentoNulo() {
		double precio = 8.0;
		double precioEsperado = 8.0;
		assertEquals(precioEsperado, new DescuentoNull().aplicarDescuento(precio), 0);
	}


}
