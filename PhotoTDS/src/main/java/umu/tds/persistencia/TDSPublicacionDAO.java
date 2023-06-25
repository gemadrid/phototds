package umu.tds.persistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Album;
import umu.tds.modelo.Comentario;
import umu.tds.modelo.Foto;
import umu.tds.modelo.Publicacion;
import umu.tds.modelo.Usuario;

public class TDSPublicacionDAO implements PublicacionDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static TDSPublicacionDAO unicaInstancia;
	
	//Para formatear la fecha de la publicación
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
		Entidad ePublicacion = null;
		
		//Comprobamos si la publicación está ya persistida
		try {
			ePublicacion = servPersistencia.recuperarEntidad(publicacion.getCodigo());
		} catch (NullPointerException e) {}
		//Si está, volvemos
		if (ePublicacion != null) return;
		
		//Registramos primero los atributos que son objetos
		//Usuario
		TDSUsuarioDAO usuarioDAO = TDSUsuarioDAO.getUnicaInstancia();
		usuarioDAO.create(publicacion.getUsuario());
		//Comentarios
		TDSComentarioDAO comentarioDAO = TDSComentarioDAO.getUnicaInstancia();
		for (Comentario c : publicacion.getComentarios())
			comentarioDAO.create(c);
		//Fotos (si es un Álbum)
		if (publicacion instanceof Album) {
			TDSPublicacionDAO publicacionDAO = TDSPublicacionDAO.getUnicaInstancia();
			for (Publicacion p : ((Album)publicacion).getFotos())
				publicacionDAO.create(p);
		}
		
		//Creamos la entidad publicación con sus atributos
		ePublicacion = new Entidad();
		ePublicacion.setNombre("publicacion");
		ePublicacion.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("titulo", publicacion.getTitulo()),
						new Propiedad("fecha", dateFormat.format(publicacion.getFecha())),
						new Propiedad("descripcion", publicacion.getDescripcion()),
						new Propiedad("megusta", String.valueOf(publicacion.getMegusta())),
						new Propiedad("hashtags", obtenerHashtags(publicacion.getHashtags())),
						new Propiedad("usuario", String.valueOf(publicacion.getUsuario().getCodigo())),
						new Propiedad("comentarios", obtenerCodigosComentarios(publicacion.getComentarios())))));
		
		//Si es una Foto añadimos también su path
		if (publicacion instanceof Foto) {
			servPersistencia.anadirPropiedadEntidad(ePublicacion, "path", ((Foto)publicacion).getPath());
		}
		
		//Si es un Album añadimos también su lista de Fotos
		if (publicacion instanceof Album) {
			servPersistencia.anadirPropiedadEntidad(ePublicacion, "fotos", obtenerCodigosPublicaciones(((Album)publicacion).getFotos()));
		}
		
		//Registramos la entidad publicación
		ePublicacion = servPersistencia.registrarEntidad(ePublicacion);
		
		//Asignamos el identificador único
		publicacion.setCodigo(ePublicacion.getId());
	}

	//Borrar publicación
	@Override
	public boolean delete(Publicacion publicacion) {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(publicacion.getCodigo());
		return servPersistencia.borrarEntidad(ePublicacion);
		
		//TODO Si es un album hay que eliminar todas las foto
	}

	//Actualizar publicación
	@Override
	public void update(Publicacion publicacion) {
		Entidad ePublicacion = servPersistencia.recuperarEntidad(publicacion.getCodigo());
		
		for (Propiedad prop : ePublicacion.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(publicacion.getCodigo()));
			} else if (prop.getNombre().equals("titulo")) {
				prop.setValor(publicacion.getTitulo());
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(dateFormat.format(publicacion.getFecha()));
			} else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(publicacion.getDescripcion());
			} else if (prop.getNombre().equals("megusta")) {
				prop.setValor(String.valueOf(publicacion.getMegusta()));
			} else if (prop.getNombre().equals("hashtags")) {
				prop.setValor(obtenerHashtags(publicacion.getHashtags()));
			} else if (prop.getNombre().equals("usuario")) {
				prop.setValor(String.valueOf(publicacion.getUsuario().getCodigo()));
			} else if (prop.getNombre().equals("comentarios")) {
				String comentarios = obtenerCodigosComentarios(publicacion.getComentarios());
				prop.setValor(comentarios);
			} else if (prop.getNombre().equals("path")) {
				prop.setValor(((Foto)publicacion).getPath());
			} else if (prop.getNombre().equals("fotos")) {
				String fotos = obtenerCodigosPublicaciones(((Album)publicacion).getFotos());
				prop.setValor(fotos);
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	//Obtener publicación
	@Override
	public Publicacion get(int id) {
		//PoolDAO - Devolver entidad
		if (PoolDAO.getUnicaInstancia().contiene(id))
			return (Publicacion) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		Entidad ePublicacion;
		//Atributos
		String titulo;
		LocalDate fecha;
		String descripcion;
		int megusta;
		List<String> hashtags;
		//Foto
		String path;
		
		//Referencias
		Usuario usuario;
		List<Comentario> comentarios;
		//Album
		List<Publicacion> fotos;
		
		//Recuperar entidad
		ePublicacion = servPersistencia.recuperarEntidad(id);
		
		//Recuperar propiedades que no son objetos
		titulo = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "titulo");
		fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "fecha"), dateFormat);
		descripcion = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "descripcion");
		megusta = Integer.valueOf(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "megusta"));
		hashtags = obtenerHashtagsSeparados(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "hashtags"));
		//Path
		path = servPersistencia.recuperarPropiedadEntidad(ePublicacion, "path");
		
		//Creamos la publicación (de momento el usuario es null)
		Publicacion publicacion;
		//Tipo de publicación (0 == foto, 1 == álbum)
		int tipo;
		//Si path != NULL, es una foto
		if (path != null) {
			tipo = 0;
			publicacion = new Foto(titulo, descripcion, null, path);
		} else {
			tipo = 1;
			publicacion = new Album(titulo, descripcion, null);
		}
		publicacion.setCodigo(id);
		publicacion.setFecha(fecha);
		publicacion.setMegusta(megusta);
		publicacion.setHashtags(hashtags);
		
		//PoolDAO - Añadir publicación antes de llamar a los otros adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(id, publicacion);
		
		//Recuperamos los objetos
		//Usuario
		TDSUsuarioDAO usuarioDAO = TDSUsuarioDAO.getUnicaInstancia();
		usuario = usuarioDAO.get(Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "usuario")));
		//Comentarios
		comentarios = obtenerComentariosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "comentarios"));
		//Fotos
		if (tipo == 1) {
			fotos = obtenerPublicacionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(ePublicacion, "fotos"));
			//Añadimos las publicaciones
			for (Publicacion f : fotos)
				((Album)publicacion).addFoto(f);
		}
		
		//Añadimos los valores que faltan
		publicacion.setUsuario(usuario);
		for (Comentario c: comentarios)
			publicacion.addComentario(c);
		
		return publicacion;
	}

	//Obtener todas las publicaciones
	@Override
	public List<Publicacion> getAll() {
		List<Entidad> ePublicaciones = servPersistencia.recuperarEntidades("publicacion");
		List<Publicacion> publicaciones = new LinkedList<Publicacion>();
		
		for (Entidad ePublicacion : ePublicaciones) {
			publicaciones.add(get(ePublicacion.getId()));
		}
		
		return publicaciones;
	}
	
	
	
	//Métodos auxiliares
	//Hashtags
	private String obtenerHashtags(List<String> hashtags) {
		String aux = "";
		for (String h : hashtags) {
			aux += h + " ";
		}
		return aux.trim();
	}
	
	private List<String> obtenerHashtagsSeparados(String hashtags) {
		List<String> listaHashtags = new LinkedList<String>();
		StringTokenizer strTok = new StringTokenizer(hashtags, " ");
		while (strTok.hasMoreTokens()) {
			listaHashtags.add(strTok.nextToken());
		}
		return listaHashtags;
	}
	
	//Comentarios
	private String obtenerCodigosComentarios(List<Comentario> comentarios) {
		String aux = "";
		for (Comentario c : comentarios) {
			aux += c.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	private List<Comentario> obtenerComentariosDesdeCodigos(String comentarios) {
		List<Comentario> listaComentarios = new LinkedList<Comentario>();
		StringTokenizer strTok = new StringTokenizer(comentarios, " ");
		TDSComentarioDAO comentarioDAO = TDSComentarioDAO.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaComentarios.add(comentarioDAO.get(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaComentarios;
	}
	
	//Fotos (para la subclase Álbum)
	private String obtenerCodigosPublicaciones(List<Publicacion> publicaciones) {
		String aux = "";
		for (Publicacion p : publicaciones) {
			aux += p.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	private List<Publicacion> obtenerPublicacionesDesdeCodigos(String publicaciones) {
		List<Publicacion> listaPublicaciones = new LinkedList<Publicacion>();
		StringTokenizer strTok = new StringTokenizer(publicaciones, " ");
		TDSPublicacionDAO publicacionDAO = TDSPublicacionDAO.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaPublicaciones.add(publicacionDAO.get(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaPublicaciones;
	}

}
