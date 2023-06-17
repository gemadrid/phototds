package umu.tds.persistencia;

import java.time.format.DateTimeFormatter;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Publicacion;

public class TDSPublicacionDAO implements PublicacionDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static TDSPublicacionDAO unicaInstancia;
	
	//Para formatear la fecha del comentario
	private DateTimeFormatter dateFormat;
	
	//Constructor
	private TDSPublicacionDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
	//getInstancia
	public static TDSPublicacionDAO getUnicaInstancia() {
		if (unicaInstancia == null) return new TDSPublicacionDAO();
		else return unicaInstancia;
	}

	
	
	//Crear publicación
	@Override
	public void create(Publicacion publicacion) {
		// TODO Auto-generated method stub
		
	}

	//Borrar publicación
	@Override
	public boolean delete(Publicacion publicacion) {
		// TODO Auto-generated method stub
		return false;
	}

	//Actualizar publicación
	@Override
	public void update(Publicacion publicacion) {
		// TODO Auto-generated method stub
		
	}

	//Obtener publicación
	@Override
	public Publicacion get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//Obtener todas las publicaciones
	@Override
	public List<Publicacion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
