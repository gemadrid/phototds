package umu.tds.persistencia;

import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Usuario;

public class TDSUsuarioDAO implements UsuarioDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static TDSUsuarioDAO unicaInstancia;
	
	//Constructor
	private TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	//getInstancia
	public static TDSUsuarioDAO getUnicaInstancia() {
		if (unicaInstancia == null) return new TDSUsuarioDAO();
		else return unicaInstancia;
	}

	@Override
	public void create(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
