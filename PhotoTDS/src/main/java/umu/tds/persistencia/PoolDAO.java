package umu.tds.persistencia;

import java.util.Hashtable;

//Pool para aquellos adaptadores que lo necesiten, para evitar doble referencia

public class PoolDAO {
	private static PoolDAO unicaInstancia;
	private Hashtable<Integer, Object> pool;
	
	//Constructor
	private PoolDAO() {
		pool = new Hashtable<Integer, Object>();
	}
	
	//getInstancia
	public static PoolDAO getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new PoolDAO();
		return unicaInstancia;
	}
	
	//Métodos para manejar el pool (get, add, contains)
	public Object getObjeto(int id) {
		return pool.get(id);
	}
	
	public void addObjeto(int id, Object objeto) {
		pool.put(id, objeto);
	}
	
	public boolean contiene(int id) {
		return pool.containsKey(id);
	}

}
