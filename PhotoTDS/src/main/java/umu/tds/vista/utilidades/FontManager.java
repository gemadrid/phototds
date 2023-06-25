package umu.tds.vista.utilidades;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
	private static Font fuente;
	private static Font fuenteBold;
	
	public static Font getFuente(float size) {
		if (fuente == null) {
			try {
				InputStream fontStream = FontManager.class.getResourceAsStream("/umu/tds/resources/fuentes/OpenSans.ttf");
				fuente = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
				fuente = new Font("Verdana", Font.PLAIN, 12);
			}
		}
		return fuente.deriveFont(size);
	}
	
	public static Font getFuenteBold(float size) {
		if (fuenteBold == null) {
			try {
				InputStream fontStream = FontManager.class.getResourceAsStream("/umu/tds/resources/fuentes/OpenSans-Bold.ttf");
				fuenteBold = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
				fuenteBold = new Font("Verdana", Font.BOLD, 12);
			}
		}
		return fuenteBold.deriveFont(size);
	}

}
