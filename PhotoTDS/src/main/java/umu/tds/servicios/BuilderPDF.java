package umu.tds.servicios;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.modelo.Usuario;

public class BuilderPDF extends BuilderInforme {
	
	private static final String PATH_DESTINO = "informes/informePDF.pdf";
	
	//Atributos
	private Document documento;
	//Tabla
	private PdfPTable tabla;
	
	public BuilderPDF() {
		inicializar();
	}
	
	private void inicializar() {
		//Documento PDF
		documento = new Document();
		try {
			PdfWriter.getInstance(documento, new FileOutputStream(PATH_DESTINO));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		documento.open();
		//Tabla
		tabla = new PdfPTable(3);
	}

	//Métodos
	@Override
	public void crearCabecera() {
		tabla.addCell("Nombre");
		tabla.addCell("Email");
		tabla.addCell("Presentación");
	}

	@Override
	public void crearUsuario(Usuario usuario) {
		tabla.addCell(usuario.getNombreCompleto());
		tabla.addCell(usuario.getEmail());
		tabla.addCell(usuario.getPresentacion());
	}

	@Override
	public void guardarInforme() {
		//Añadimos la tabla al documento
		try {
			documento.add(tabla);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//Cerramos el documento
		documento.close();
	}

}
