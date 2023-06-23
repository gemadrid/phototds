package umu.tds.servicios;

import umu.tds.modelo.Usuario;

public class GeneradorInforme {
	
	//Métodos
	public static void generarInforme(Usuario usuario, BuilderInforme builder) {
		//Cabecera
		builder.crearCabecera();
		//Añadimos cada usuario seguidor al informe
		usuario.getSeguidores().forEach(u -> builder.crearUsuario(u));
		//Guardar documento
		builder.guardarInforme();
	}

}
