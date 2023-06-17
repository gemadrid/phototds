package umu.tds;

import java.awt.EventQueue;
import java.util.Date;

import umu.tds.controlador.Controlador;
import umu.tds.vista.VentanaLogin;

/**
 * Hello world!
 *
 */
public class Lanzador 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebas();
					VentanaLogin ventana = new VentanaLogin();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public static void pruebas() {
		Controlador.INSTANCE.registrarUsuario("Gema", "Madrid Sánchez", "gema@gmail.com", "gema", "gema", new Date(), "", "");
	}
	
}
