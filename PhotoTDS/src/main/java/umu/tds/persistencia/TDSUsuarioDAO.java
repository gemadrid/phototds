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
import umu.tds.modelo.Notificacion;
import umu.tds.modelo.Publicacion;
import umu.tds.modelo.Usuario;
import umu.tds.modelo.descuento.FactoriaDescuento;

public class TDSUsuarioDAO implements UsuarioDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static TDSUsuarioDAO unicaInstancia;
	
	//Para formatear la fecha de nacimiento
	private DateTimeFormatter dateFormat;
	
	//Constructor
	private TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
	//getInstancia
	public static TDSUsuarioDAO getUnicaInstancia() {
		if (unicaInstancia == null) return new TDSUsuarioDAO();
		else return unicaInstancia;
	}

	
	
	//Crear usuario
	@Override
	public void create(Usuario usuario) {
		Entidad eUsuario = null;
		
		//Comprobamos si el usuario ya está persistido
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {}
		//Si está, volvemos
		if (eUsuario != null) return;
		
		//Registramos primero los atributos que son objetos
		//Publicaciones
		TDSPublicacionDAO publicacionDAO = TDSPublicacionDAO.getUnicaInstancia();
		for (Publicacion p : usuario.getPublicaciones())
			publicacionDAO.create(p);
		//Seguidores
		TDSUsuarioDAO usuarioDAO = TDSUsuarioDAO.getUnicaInstancia();
		for (Usuario s : usuario.getSeguidores())
			usuarioDAO.create(s);
		//Notificaciones
		TDSNotificacionDAO notificacionDAO = TDSNotificacionDAO.getUnicaInstancia();
		for (Notificacion n : usuario.getNotificaciones())
			notificacionDAO.create(n);
		
		//Creamos la entidad usuario con sus atributos
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", usuario.getNombre()),
						new Propiedad("apellidos", usuario.getApellidos()),
						new Propiedad("email", usuario.getEmail()),
						new Propiedad("nombreusuario", usuario.getNombreUsuario()),
						new Propiedad("password", usuario.getPassword()),
						new Propiedad("fechanacimiento", dateFormat.format(usuario.getFechaNacimiento())),
						new Propiedad("fotousuario", usuario.getFotoUsuario()),
						new Propiedad("presentacion", usuario.getPresentacion()),
						new Propiedad("premium", String.valueOf(usuario.isPremium())),
						new Propiedad("descuento", usuario.getDescuento().getNombre()),
						new Propiedad("publicaciones", obtenerCodigosPublicaciones(usuario.getPublicaciones())),
						new Propiedad("seguidores", obtenerCodigosSeguidores(usuario.getSeguidores())),
						new Propiedad("notificaciones", obtenerCodigosNotificaciones(usuario.getNotificaciones())))));
		
		//Registramos la entidad usuario
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		
		//Asignamos el identificador único
		usuario.setCodigo(eUsuario.getId());
	}

	//Borrar usuario
	@Override
	public boolean delete(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		return servPersistencia.borrarEntidad(eUsuario);
	}

	//Actualizar usuario
	@Override
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		
		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(usuario.getCodigo()));
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals("apellidos")) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals("nombreusuario")) {
				prop.setValor(usuario.getNombreUsuario());
			} else if (prop.getNombre().equals("password")) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals("fechanacimiento")) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			} else if (prop.getNombre().equals("fotousuario")) {
				prop.setValor(usuario.getFotoUsuario());
			} else if (prop.getNombre().equals("presentacion")) {
				prop.setValor(usuario.getPresentacion());
			} else if (prop.getNombre().equals("premium")) {
				prop.setValor(String.valueOf(usuario.isPremium()));
			} else if (prop.getNombre().equals("descuento")) {
				prop.setValor(usuario.getDescuento().getNombre());
			} else if (prop.getNombre().equals("publicaciones")) {
				String publicaciones = obtenerCodigosPublicaciones(usuario.getPublicaciones());
				prop.setValor(publicaciones);
			} else if (prop.getNombre().equals("seguidores")) {
				String seguidores = obtenerCodigosSeguidores(usuario.getSeguidores());
				prop.setValor(seguidores);
			} else if (prop.getNombre().equals("notificaciones")) {
				String notificaciones = obtenerCodigosNotificaciones(usuario.getNotificaciones());
				prop.setValor(notificaciones);
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	//Obtener usuario
	@Override
	public Usuario get(int id) {
		//PoolDAO - Devolvemos la entidad si está en el PoolDAO
		if (PoolDAO.getUnicaInstancia().contiene(id))
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		Entidad eUsuario;
		//Atributos
		String nombre;
		String apellidos;
		String email;
		String nombreUsuario;
		String password;
		LocalDate fechaNacimiento;
		String fotoUsuario;
		String presentacion;
		boolean premium;
		//Referencias
		String nombreDescuento;
		List<Publicacion> publicaciones;
		List<Usuario> seguidores;
		List<Notificacion> notificaciones;
		
		//Recuperar entidad
		eUsuario = servPersistencia.recuperarEntidad(id);
		
		//Recuperar propiedades que no son objetos
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		nombreUsuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombreusuario");
		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		fechaNacimiento = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechanacimiento"), dateFormat);
		fotoUsuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fotousuario");
		presentacion = servPersistencia.recuperarPropiedadEntidad(eUsuario, "presentacion");
		premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		nombreDescuento = servPersistencia.recuperarPropiedadEntidad(eUsuario, "descuento");
		
		//Creamos el usuario
		Usuario usuario = new Usuario(nombre, apellidos, email, nombreUsuario, password, fechaNacimiento, fotoUsuario, presentacion);
		usuario.setCodigo(id);
		usuario.setPremium(premium);
		usuario.setDescuento(FactoriaDescuento.getUnicaInstancia().crearDescuento(nombreDescuento));
		
		//PoolDAO - Añadir cliente al PoolDAO antes de llamar a los otros adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(id, usuario);
		
		//Recuperar objetos
		publicaciones = obtenerPublicacionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "publicaciones"));
		seguidores = obtenerSeguidoresDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "seguidores"));
		notificaciones = obtenerNotificacionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eUsuario, "notificaciones"));
		
		//Añadimos los valores que faltan
		for (Publicacion p : publicaciones)
			usuario.addPublicacion(p);
		for (Usuario s : seguidores)
			usuario.addSeguidor(s);
		for (Notificacion n : notificaciones)
			usuario.addNotificacion(n);
		
		return usuario;
	}

	//Obtener todos los usuarios
	@Override
	public List<Usuario> getAll() {
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();
		
		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(get(eUsuario.getId()));
		}
		
		return usuarios;
	}
	
	
	
	//Métodos auxiliares
	//Publicaciones
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
	
	//Seguidores
	private String obtenerCodigosSeguidores(List<Usuario> seguidores) {
		String aux = "";
		for (Usuario s : seguidores) {
			aux += s.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	private List<Usuario> obtenerSeguidoresDesdeCodigos(String seguidores) {
		List<Usuario> listaSeguidores = new LinkedList<Usuario>();
		StringTokenizer strTok = new StringTokenizer(seguidores, " ");
		TDSUsuarioDAO usuarioDAO = TDSUsuarioDAO.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaSeguidores.add(usuarioDAO.get(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaSeguidores;
	}
	
	//Notificaciones
	private String obtenerCodigosNotificaciones(List<Notificacion> notificaciones) {
		String aux = "";
		for (Notificacion n : notificaciones) {
			aux += n.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	private List<Notificacion> obtenerNotificacionesDesdeCodigos(String notificaciones) {
		List<Notificacion> listaNotificaciones = new LinkedList<Notificacion>();
		StringTokenizer strTok = new StringTokenizer(notificaciones, " ");
		TDSNotificacionDAO notificacionDAO = TDSNotificacionDAO.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaNotificaciones.add(notificacionDAO.get(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaNotificaciones;
	}

}
