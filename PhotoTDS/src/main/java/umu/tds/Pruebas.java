package umu.tds;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
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
		File from = new File("C:\\Users\\Gema\\Documents\\Gema\\3º Curso\\acceso_aulas_aulario_norte.jpeg");
		System.out.println(from.getAbsolutePath());
		System.out.println();
		
		File to = new File("fotos/");
		System.out.println(to.getAbsolutePath() + "\\" + from.getName());
		System.out.println();
		
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
