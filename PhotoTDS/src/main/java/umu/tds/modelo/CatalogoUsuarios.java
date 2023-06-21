package umu.tds.modelo;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
		usuarios.put(usuario.getNombreUsuario(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios.remove(usuario.getNombreUsuario());
	}
	
	
	
	//Métodos get
	//Obtener usuarios seguidos por "usuario"
	public List<Usuario> getUsuariosSeguidos(Usuario usuario) {
		return usuarios.values().stream()
				.filter(u -> u.isUsuarioSeguidor(usuario))
				.collect(Collectors.toList());
	}
	
	//Obtener usuarios por nombre o email
	public List<Usuario> getUsuariosPorNombre(String nombre) {
		String cadena = normalizar(nombre);
		return usuarios.values().stream()
				.filter(u -> normalizar(u.getNombreCompleto()).contains(cadena) ||
								normalizar(u.getEmail()).contains(cadena) ||
								normalizar(u.getNombreUsuario()).contains(cadena))
				.collect(Collectors.toList());
	}
	
	//TODO Ver dónde meter este método
	public String normalizar(String cadena) {
		return Normalizer.normalize(cadena, Form.NFD)
				.replaceAll("\\p{M}", "")
				.toLowerCase();
	}

}
