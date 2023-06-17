package umu.tds.persistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Comentario;
import umu.tds.modelo.Usuario;

public class TDSComentarioDAO implements ComentarioDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static TDSComentarioDAO unicaInstancia;
	
	//Para formatear la fecha del comentario
	private DateTimeFormatter dateFormat;
	
	//Constructor
	private TDSComentarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
	//getInstancia
	public static TDSComentarioDAO getUnicaInstancia() {
		if (unicaInstancia == null) return new TDSComentarioDAO();
		else return unicaInstancia;
	}

	
	
	//Crear comentario
	@Override
	public void create(Comentario comentario) {
		Entidad eComentario = null;
		
		//Comprobamos si el comentario ya está persistido
		try {
			eComentario = servPersistencia.recuperarEntidad(comentario.getCodigo());
		} catch (NullPointerException e) {}
		//Si está, volvemos
		if (eComentario != null) return;
		
		//Registamos primero los atributos que son objetos
		TDSUsuarioDAO usuarioDAO = TDSUsuarioDAO.getUnicaInstancia();
		usuarioDAO.create(comentario.getUsuario());
		
		//Creamos la entidad comentario con sus atributos
		eComentario = new Entidad();
		eComentario.setNombre("comentario");
		eComentario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("texto", comentario.getTexto()),
						new Propiedad("fecha", dateFormat.format(comentario.getFecha())),
						new Propiedad("usuario", String.valueOf(comentario.getUsuario().getCodigo())))));
		
		//Registramos la entidad comentario
		eComentario = servPersistencia.registrarEntidad(eComentario);
		
		//Asignamos el identificador único (creado por el servicio de persistencia)
		comentario.setCodigo(eComentario.getId());
	}

	//Borrar comentario
	@Override
	public boolean delete(Comentario comentario) {
		Entidad eComentario = servPersistencia.recuperarEntidad(comentario.getCodigo());
		return servPersistencia.borrarEntidad(eComentario);
	}

	//Actualizar comentario
	@Override
	public void update(Comentario comentario) {
		Entidad eComentario = servPersistencia.recuperarEntidad(comentario.getCodigo());
		
		for (Propiedad prop : eComentario.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(comentario.getCodigo()));
			} else if (prop.getNombre().equals("texto")) {
				prop.setValor(String.valueOf(comentario.getTexto()));
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(dateFormat.format(comentario.getFecha()));
			} else if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(comentario.getUsuario().getCodigo()));
			}
			servPersistencia.modificarPropiedad(prop);
		}	
	}

	//Obtener comentario
	@Override
	public Comentario get(int id) {
		Entidad eComentario;
		//Atributos
		String texto;
		LocalDate fecha;
		Usuario usuario;
		
		//Recuperar entidad
		eComentario = servPersistencia.recuperarEntidad(id);
		
		//Recuperar propiedades que no son objetos
		texto = servPersistencia.recuperarPropiedadEntidad(eComentario, "texto");
		fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eComentario, "fecha"), dateFormat);
		
		//Para recuperar el usuario lo solicitamos al TDSUsuarioDAO
		TDSUsuarioDAO usuarioDAO = TDSUsuarioDAO.getUnicaInstancia();
		usuario = usuarioDAO.get(Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eComentario, "usuario")));
		
		//Creamos el comentario
		Comentario comentario = new Comentario(texto, usuario);
		comentario.setCodigo(id);
		comentario.setFecha(fecha);
		
		return comentario;
	}

}
