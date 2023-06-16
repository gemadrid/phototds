package umu.tds.persistencia;

import umu.tds.modelo.Notificacion;

public interface NotificacionDAO {
	
	void create(Notificacion notificacion);
	boolean delete(Notificacion notificacion);
	void update(Notificacion notificacion);
	Notificacion get(int id);

}
