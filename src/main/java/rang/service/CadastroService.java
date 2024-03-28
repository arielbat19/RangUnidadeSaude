package rang.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rang.dao.DAO;
import rang.model.UnidadeSaude;
import rang.utils.ExceptionHandler;

public class CadastroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DAO<UnidadeSaude> dao;

	/**
	 * Faz as validações e salva na base de dados
	 * @return void
	 */
	public void salvar(UnidadeSaude u) throws ExceptionHandler {

		if (u.getNomeEstabelecimento().length() < 3) {
			throw new ExceptionHandler("O nome do estabelecimento não pode ser menor que 3 caracteres");
		}

		if (u.getCnes() == null || u.getCnes().equals("")) {
			throw new ExceptionHandler("O CNES não pode ser nulo ou vazio");
		}

		if (u.getFaixaInicioCep() == null || u.getFaixaInicioCep().equals("")) {
			throw new ExceptionHandler("A faixa de inicio do CEP não pode ser vazio ou nulo");
		}
		if (u.getFaixaFinalCep() == null || u.getFaixaFinalCep().equals("")) {
			throw new ExceptionHandler("A faixa de inicio do CEP não pode ser vazio ou nulo");
		}
		List<UnidadeSaude> unidadeSaudes = verificaFaixas(u.getFaixaInicioCep(), u.getFaixaFinalCep());
		if (unidadeSaudes.isEmpty()) {
			dao.salvar(u);
		} else {
			throw new ExceptionHandler("A faixa de CEP entre " + u.getFaixaInicioCep() + " - " + u.getFaixaFinalCep()
					+ " ja existe! Informe uma nova faixa");
		}
	}

	/**
	 * Faz a remoção de uma unidade de saude
	 * @return void
	 */
	public void remover(UnidadeSaude p) throws ExceptionHandler {
		dao.remover(UnidadeSaude.class, p.getCnes());
	}

	/**
	 * Lista todas as unidades de saude
	 * @return List<UnidadeSaude>
	 */
	public List<UnidadeSaude> todasUnidades() {
		return dao.buscarTodos("select u from UnidadeSaude u order by u.nomeEstabelecimento");
	}

	/**
	 * Consulta uma unidade de saúde mais próxima do usuário
	 * @return List<UnidadeSaude>
	 */
	public List<UnidadeSaude> buscarPorUnidadeProxima(Integer cep) {
		return dao.buscarUnidade("SELECT u FROM UnidadeSaude u WHERE :cep BETWEEN u.faixaInicioCep AND u.faixaFinalCep",
				Collections.singletonMap("cep", cep));
	}

	/**
	 * Monta a query para verificar se existe um conflito entre as faixas de CEP informadas 
	 * @return List<UnidadeSaude>
	 */
	public List<UnidadeSaude> verificaFaixas(Integer faixaInicial, Integer faixaFinal) {
		String sql = "SELECT * FROM UnidadeSaude WHERE (faixaInicioCep BETWEEN ? AND ?) OR (faixaFinalCep BETWEEN ? AND ?)";

		List<UnidadeSaude> resultados = dao.verificaFaixas(sql,
				Arrays.asList(faixaInicial, faixaFinal, faixaInicial, faixaFinal));

		return resultados;
	}

}
