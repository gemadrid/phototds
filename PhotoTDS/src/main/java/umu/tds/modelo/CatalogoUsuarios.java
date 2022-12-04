package umu.tds.modelo;

import java.util.HashMap;

public enum CatalogoUsuarios {
	//Implementado como enumerado
	INSTANCE;
	
	//private FactoriaDAO factoria;
	
	private HashMap<String, Usuario> usuarios;

	//Constructor
	private CatalogoUsuarios() {
		usuarios = new HashMap<String, Usuario>();
	}
	
	//public List<Usuario> findUsuarios() throws DAOException
	
	//Métodos
	public Usuario findUsuario(String login) {
		return usuarios.get(login);
	}
	
	public void addUsuario(Usuario usuario) {
		usuarios.put(usuario.getLogin(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios.remove(usuario.getLogin());
	}

}
