package rang.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rang.model.UnidadeSaude;
import rang.service.CadastroService;
import rang.utils.ExceptionHandler;
import rang.utils.Utils;

@Named("bean")
@SessionScoped
public class CadastroUnidadeSaudeController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeSaude saude;

	@Inject
	private CadastroService service;

	private List<UnidadeSaude> listUnidadeSaude = new ArrayList<>();
	private List<UnidadeSaude> unidadeProxima = new ArrayList<>();
	private Integer cep;

	@PostConstruct
	public void init() {
		carregar();
	}

	/**
	 * Carrega a lista de unidades de saude
	 * @return void
	 */
	public void carregar() {
		listUnidadeSaude = service.todasUnidades();

	}

	/**
	 * Adicion uma unidades de saude
	 * @return void
	 */
	public void adicionar() {
		try {
			service.salvar(saude);
			saude = new UnidadeSaude();
			carregar();
			limpar();
			Utils.info("Salva com sucesso");

		} catch (ExceptionHandler e) {
			Utils.erro(e.getMessage());
		}
	}
	
	/**
	 * Consulta uma unidade de saúde mais próxima do usuário
	 * @return List<UnidadeSaude>
	 */
	public List<UnidadeSaude> consultar() {
		unidadeProxima = null;
		unidadeProxima = service.buscarPorUnidadeProxima(cep);
		return unidadeProxima;
	}

	/**
	 * Limpa os campos
	 * @return void
	 */
	private void limpar() {
		saude = new UnidadeSaude();
	}

	public UnidadeSaude getSaude() {
		return saude;
	}

	public void setSaude(UnidadeSaude saude) {
		this.saude = saude;
	}

	public List<UnidadeSaude> getListUnidadeSaude() {
		return listUnidadeSaude;
	}

	public void setListUnidadeSaude(List<UnidadeSaude> listUnidadeSaude) {
		this.listUnidadeSaude = listUnidadeSaude;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public List<UnidadeSaude> getUnidadeProxima() {
		return unidadeProxima;
	}

	public void setUnidadeProxima(List<UnidadeSaude> unidadeProxima) {
		this.unidadeProxima = unidadeProxima;
	}
}
