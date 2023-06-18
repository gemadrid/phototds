package umu.tds.persistencia;

public class TDSFactoriaDAO extends FactoriaDAO {
	
	public TDSFactoriaDAO() {}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return TDSUsuarioDAO.getUnicaInstancia();
	}

	@Override
	public PublicacionDAO getPublicacionDAO() {
		return TDSPublicacionDAO.getUnicaInstancia();
	}

	@Override
	public NotificacionDAO getNotificacionDAO() {
		return TDSNotificacionDAO.getUnicaInstancia();
	}

	@Override
	public ComentarioDAO getComentarioDAO() {
		return TDSComentarioDAO.getUnicaInstancia();
	}

}
