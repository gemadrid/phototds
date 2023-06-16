package umu.tds.persistencia;

public abstract class FactoriaDAO {
	
	//Nombre de la factoría concreta a instanciar
	public static final String DAO_TDS = "umu.tds.persistencia.TDSFactoriaDAO";
	
	private static FactoriaDAO unicaInstancia = null;
	
	//Crea un tipo de factoría DAO / devuelve la instancia actual
	public static FactoriaDAO getInstancia(String tipo) throws DAOException {
		if (unicaInstancia == null)
			try {
				unicaInstancia = (FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		return unicaInstancia;
	}
	
	public static FactoriaDAO getInstancia() throws DAOException {
		return getInstancia(FactoriaDAO.DAO_TDS);
	}
	
	protected FactoriaDAO () {}
	
	//Métodos factoría para obtener adaptadores
	public abstract UsuarioDAO getUsuarioDAO();
	public abstract PublicacionDAO getPublicacionDAO();
	public abstract NotificacionDAO getNotificacionDAO();
	public abstract ComentarioDAO getComentarioDAO();

}
