package umu.tds.modelo;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public enum CatalogoUsuarios {
	//Implementado como enumerado
	INSTANCE;
	
	//private FactoriaDAO factoria;
	
	private HashMap<String, Usuario> usuarios;
	private HashMap<Integer, Usuario> usuariosPorID;

	//Constructor
	private CatalogoUsuarios() {
		usuarios = new HashMap<String, Usuario>();
		usuariosPorID = new HashMap<Integer, Usuario>();
		
		try {
			FactoriaDAO factoria = FactoriaDAO.getInstancia();
			//Obtenemos todos los usuarios
			List<Usuario> listaUsuarios = factoria.getUsuarioDAO().getAll();
			for (Usuario usuario : listaUsuarios) {
				usuarios.put(usuario.getNombreUsuario(), usuario);
				usuariosPorID.put(usuario.getCodigo(), usuario);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Métodos
	public List<Usuario> getAllUsuarios() {
		return usuarios.values().stream().collect(Collectors.toList());
	}
	
	public Usuario findUsuario(String login) {
		return usuarios.get(login);
	}
	
	public Usuario findUsuario(int id) {
		return usuariosPorID.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		usuarios.put(usuario.getNombreUsuario(), usuario);
		usuariosPorID.put(usuario.getCodigo(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		usuarios.remove(usuario.getNombreUsuario());
		usuariosPorID.remove(usuario.getCodigo());
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
	
	private String normalizar(String cadena) {
		return Normalizer.normalize(cadena, Form.NFD)
				.replaceAll("\\p{M}", "")
				.toLowerCase();
	}

}
