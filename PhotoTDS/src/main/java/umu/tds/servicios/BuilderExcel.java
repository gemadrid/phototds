package umu.tds.servicios;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import umu.tds.modelo.Usuario;

public class BuilderExcel extends BuilderInforme {
	
	private static final String PATH_DESTINO = "informes/informeExcel.xls";
	
	//Documento y hoja
	private HSSFWorkbook documento;
	private HSSFSheet hoja;
	//Contador de fila
	private int numFila;
	
	public BuilderExcel() {
		inicializar();
	}
	
	private void inicializar() {
		documento = new HSSFWorkbook();
		hoja = documento.createSheet("Lista de seguidores");
		numFila = 0;
	}

	//Métodos
	@Override
	public void crearCabecera() {
		//Crear fila 0 (cabeceras)
		String[] cabecera = new String[] {"Nombre", "Email", "Presentación"};
		HSSFRow filaCabecera = hoja.createRow(numFila);
		for (int i = 0; i < cabecera.length; ++i) {
			HSSFCell celda = filaCabecera.createCell(i);
			celda.setCellValue(cabecera[i]);
		}
		numFila++;
	}

	@Override
	public void crearUsuario(Usuario usuario) {
		//Crear filas
		String[] datos = new String[] {usuario.getNombreCompleto(),
				usuario.getEmail(), usuario.getPresentacion()};
		HSSFRow fila = hoja.createRow(numFila);
		for (int i = 0; i < datos.length; ++i) {
			HSSFCell celda = fila.createCell(i);
			celda.setCellValue(datos[i]);
		}
		numFila++;
	}

	@Override
	public void guardarInforme() {
		try {
			FileOutputStream file = new FileOutputStream(PATH_DESTINO);
			documento.write(file);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
