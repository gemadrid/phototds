package umu.tds;

import java.awt.EventQueue;

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
					VentanaLogin ventana = new VentanaLogin();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
