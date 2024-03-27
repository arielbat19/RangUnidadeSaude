package rang.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UnidadeSaude implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cnes; 
	private String nomeEstabelecimento; 
	private Integer faixaInicioCep; 
	private Integer faixaFinalCep;
	
	public UnidadeSaude() {
	}
	
	public Long getCnes() {
		return cnes;
	}
	public void setCnes(Long cnes) {
		this.cnes = cnes;
	}
	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}
	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}
	public Integer getFaixaInicioCep() {
		return faixaInicioCep;
	}
	public void setFaixaInicioCep(Integer faixaInicioCep) {
		this.faixaInicioCep = faixaInicioCep;
	}
	public Integer getFaixaFinalCep() {
		return faixaFinalCep;
	}
	public void setFaixaFinalCep(Integer faixaFinalCep) {
		this.faixaFinalCep = faixaFinalCep;
	}

	@Override
	public String toString() {
		return "UnidadeSaude [CNES=" + cnes + ", nomeEstabelecimento=" + nomeEstabelecimento + ", faixaInicioCep="
				+ faixaInicioCep + ", faixaFinalCep=" + faixaFinalCep + "]";
	} 
	
}
