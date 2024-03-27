package rang.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe responsável por realizar a conexão com a base de dados
 */
public class ConnectionFactory {
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Banco");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
