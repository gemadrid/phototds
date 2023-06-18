package umu.tds.persistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Notificacion;
import umu.tds.modelo.Publicacion;

public class TDSNotificacionDAO implements NotificacionDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static TDSNotificacionDAO unicaInstancia;
	
	//Para formatear la fecha de la notificación
	private DateTimeFormatter dateFormat;
	
	//Constructor
	private TDSNotificacionDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
	//getInstancia
	public static TDSNotificacionDAO getUnicaInstancia() {
		if (unicaInstancia == null) return new TDSNotificacionDAO();
		else return unicaInstancia;
	}

	
	
	//Crear notificación
	@Override
	public void create(Notificacion notificacion) {
		Entidad eNotificacion = null;
		
		//Comprobamos si la notificación está ya persistida
		try {
			eNotificacion = servPersistencia.recuperarEntidad(notificacion.getCodigo());
		} catch (NullPointerException e) {}
		//Si está, volvemos
		if (eNotificacion != null) return;
		
		//Registramos primero los atributos que son objetos
		TDSPublicacionDAO publicacionDAO = TDSPublicacionDAO.getUnicaInstancia();
		publicacionDAO.create(notificacion.getPublicacion());
		
		//Creamos la entidad notificación con sus atributos
		eNotificacion = new Entidad();
		eNotificacion.setNombre("notificacion");
		eNotificacion.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("fecha", dateFormat.format(notificacion.getFecha())),
						new Propiedad("publicacion", String.valueOf(notificacion.getPublicacion().getCodigo())))));
		
		//Registramos la entidad notificación
		eNotificacion = servPersistencia.registrarEntidad(eNotificacion);
		
		//Asignamos el identificador único
		notificacion.setCodigo(eNotificacion.getId());
	}

	//Borrar notificación
	@Override
	public boolean delete(Notificacion notificacion) {
		Entidad eNotificacion = servPersistencia.recuperarEntidad(notificacion.getCodigo());
		return servPersistencia.borrarEntidad(eNotificacion);
	}

	//Actualizar notificación
	@Override
	public void update(Notificacion notificacion) {
		Entidad eNotificacion = servPersistencia.recuperarEntidad(notificacion.getCodigo());
		
		for (Propiedad prop : eNotificacion.getPropiedades()) {
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(notificacion.getCodigo()));
			} else if (prop.getNombre().equals("fecha")) {
				prop.setValor(dateFormat.format(notificacion.getFecha()));
			} else if (prop.getNombre().equals("publicacion")) {
				prop.setValor(String.valueOf(notificacion.getPublicacion().getCodigo()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
		
	}

	//Obtener notificación
	@Override
	public Notificacion get(int id) {
		//TODO PoolDAO - Devolvemos la entidad
		if (PoolDAO.getUnicaInstancia().contiene(id))
			return (Notificacion) PoolDAO.getUnicaInstancia().getObjeto(id);
		
		Entidad eNotificacion;
		//Atributos
		LocalDate fecha;
		Publicacion publicacion;
		
		//Recuperar entidad
		eNotificacion = servPersistencia.recuperarEntidad(id);
		
		//Recuperar propiedades que no son objetos
		fecha = LocalDate.parse(servPersistencia.recuperarPropiedadEntidad(eNotificacion, "fecha"), dateFormat);
		
		//Creamos la notificación
		Notificacion notificacion = new Notificacion(null);
		notificacion.setCodigo(id);
		notificacion.setFecha(fecha);
		
		//TODO PoolDAO - Añadimos la notificación antes de llamar al adaptador de publicaciones
		PoolDAO.getUnicaInstancia().addObjeto(id, notificacion);
		
		//Para recuperar la publicación la solicitamos al TDSPublicacionDAO
		TDSPublicacionDAO publicacionDAO = TDSPublicacionDAO.getUnicaInstancia();
		publicacion = publicacionDAO.get(Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eNotificacion, "publicacion")));
		
		//Creamos la notificación
		/*Notificacion notificacion = new Notificacion(publicacion);
		notificacion.setCodigo(id);
		notificacion.setFecha(fecha);*/
		notificacion.setPublicacion(publicacion);
		
		return notificacion;
	}

}
