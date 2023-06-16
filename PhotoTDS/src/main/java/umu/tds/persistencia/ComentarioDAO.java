package umu.tds.persistencia;

import umu.tds.modelo.Comentario;

public interface ComentarioDAO {
	
	void create(Comentario comentario);
	boolean delete(Comentario comentario);
	void update(Comentario comentario);
	Comentario get(int id);

}
