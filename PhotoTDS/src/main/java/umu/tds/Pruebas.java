package umu.tds;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.h2.expression.function.ToChar;

import umu.tds.controlador.Controlador;
import umu.tds.modelo.Foto;
import umu.tds.modelo.Publicacion;
import umu.tds.vista.VentanaLogin;

/**
 * Hello world!
 *
 */
public class Pruebas 
{
	public static void main(String[] args) {
		URL url = Controlador.class.getResource("/umu/tds/resources/fotoperfil.jpg");
		System.out.println(url);
		System.out.println(url.getPath());
		File file = new File(url.getPath());
		System.out.println(file.getPath());
		
		/*File to2 = new File(Controlador.class.getResource("/umu/tds/fotos/").getPath());
		System.out.println(to2.getAbsolutePath() + "\\" + from.getName());*/
		
		/*try {
			Files.copy(
					from.toPath(),
					Paths.get(to.getAbsolutePath() + "\\" + from.getName()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	
	}
	
}
