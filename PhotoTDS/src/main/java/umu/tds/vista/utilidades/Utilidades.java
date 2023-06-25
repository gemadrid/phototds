package umu.tds.vista.utilidades;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utilidades {
	
	private static final int ESCALADO_DEFAULT = Image.SCALE_FAST;
	
	public static ImageIcon getImagenRedimensionada(String path, int ancho, int alto) {
		return getImagenRedimensionada(path, ancho, alto, ESCALADO_DEFAULT);
	}
	
	public static ImageIcon getImagenRedimensionada(String path, int ancho, int alto, int tipoEscalado) {
		try {
			//Leemos la imagen
			BufferedImage img = ImageIO.read(new File(path));
			//Reescalamos la imagen
			Image imgNueva = img.getScaledInstance(ancho, alto, tipoEscalado);
			//Devolvemos la imagen
			return new ImageIcon(imgNueva);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
