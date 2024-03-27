package rang.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import rang.model.UnidadeSaude;

/**
 * Classe responsavel por executar as operação na base de dados
 */
public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static EntityManager manager = ConnectionFactory.getEntityManager();

	/**
	 * Busca unidade de saude por id
	 * @return T
	 */
	public T buscarPorId(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}

	/**
	 * Salva uma unidade de saude
	 * @return void
	 */
	public void salvar(T t) {
		try {
			manager.getTransaction().begin();

			if (t.getClass() == null) {
				manager.persist(t);
			} else {
				manager.merge(t);
			}

			manager.getTransaction().commit();

		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	/**
	 * Remove uma unidade de saúde da base
	 * @return void
	 */
	public void remover(Class<T> clazz, Long id) {
		T t = buscarPorId(clazz, id);

		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}

	}

	/**
	 * Faz a busca por todas as unidades de saude
	 * @return List<UnidadeSaude>
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(String jpql) {
		Query query = manager.createQuery(jpql);
		return query.getResultList();

	}
	
	/**
	 * Busca por uma unidade de saude mais proxima ao usuario
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarUnidade(String jpql, Map<String, Object> parametros) {
	    Query query = manager.createQuery(jpql);
	    for (Map.Entry<String, Object> entry : parametros.entrySet()) {
	        query.setParameter(entry.getKey(), entry.getValue());
	    }
	    return query.getResultList();
	}
	
	/**
	 * Verifica se a faixa de CEP informada pelo usuario ja existe
	 * @return List<UnidadeSaude>
	 */
	@SuppressWarnings("unchecked")
    public List<UnidadeSaude> verificaFaixas(String sql, List<Object> parametros) {
        Query query = manager.createNativeQuery(sql);
        int index = 1;
        for (Object parametro : parametros) {
            query.setParameter(index++, parametro);
        }
        return query.getResultList();
    }

}
