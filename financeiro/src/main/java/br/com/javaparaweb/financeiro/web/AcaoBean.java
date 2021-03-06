package br.com.javaparaweb.financeiro.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import br.com.javaparaweb.financeiro.bolsa.acao.Acao;
import br.com.javaparaweb.financeiro.bolsa.acao.AcaoRN;
import br.com.javaparaweb.financeiro.bolsa.acao.AcaoVirtual;
import br.com.javaparaweb.financeiro.web.util.YahooFinanceiroUtil;
import java.io.Serializable;
@ManagedBean(name = "acaoBean")
@SessionScoped
public class AcaoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8737614568862526335L;
	private AcaoVirtual selecionada = new AcaoVirtual();
	private List<AcaoVirtual> lista = null;
	private String linkCodigoAcao = null;

	private PieChartModel percentualQuantidade = new PieChartModel();
	private PieChartModel percentualValor = new PieChartModel();

	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;

	public void salvar() {
		AcaoRN acaoRN = new AcaoRN();
		Acao acao = this.selecionada.getAcao();

		acao.setSigla(acao.getSigla().toUpperCase());
		acao.setUsuario(contextoBean.getUsuarioLogado());

		acaoRN.salvar(acao);

		this.selecionada = new AcaoVirtual();
		this.lista = null;
	}

	public void excluir() {
		AcaoRN acaoRN = new AcaoRN();
		acaoRN.excluir(this.selecionada.getAcao());

		this.selecionada = new AcaoVirtual();
		this.lista = null;

	}

	public List<AcaoVirtual> getLista() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		AcaoRN acaoRN = new AcaoRN();

		System.out.println("getLista - usuario logado : " + contextoBean.getUsuarioLogado().getNome());
		try {
			if (this.lista == null) {
				this.lista = acaoRN.listarAcaoVirtual(contextoBean.getUsuarioLogado());
			}
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}

		if ( this.lista != null && this.lista.size() > 0 ) {
			System.out.println("getLista - itens da lista : " + this.lista.size() );
		}
		
		
		return this.lista;
	}


	public String getLinkCodigoAcao() {
		AcaoRN acaoRN = new AcaoRN();
		if (this.selecionada != null) {
			this.linkCodigoAcao = acaoRN.montaLinkAcao(this.selecionada.getAcao());
		} else {
			this.linkCodigoAcao = YahooFinanceiroUtil.INDICE_BOVESPA;
		}
		return this.linkCodigoAcao;
	}
	

	public PieChartModel getPercentualQuantidade() {
		List<AcaoVirtual> acoes = this.getLista();

		if (acoes.size() > 0) {
			this.percentualQuantidade.setLegendPosition("e");
			this.percentualQuantidade.setShowDataLabels(true);
			this.percentualQuantidade.setDataFormat("percent");

			for (AcaoVirtual acaoVirtual : acoes) {
				Acao acao = acaoVirtual.getAcao();
				this.percentualQuantidade.set(acao.getSigla(), acao.getQuantidade());
			}
		}

		return this.percentualQuantidade;
	}

	public PieChartModel getPercentualValor() {
		List<AcaoVirtual> acoes = this.getLista();

		if (acoes.size() > 0) {
			this.percentualQuantidade.setLegendPosition("e");
			this.percentualQuantidade.setShowDataLabels(true);
			this.percentualQuantidade.setDataFormat("percent");

			for (AcaoVirtual acaoVirtual : acoes) {
				Acao acao = acaoVirtual.getAcao();
				this.percentualValor.set(acao.getSigla(), acaoVirtual.getTotal());
			}
		}

		return this.percentualValor;
	}

	public AcaoVirtual getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(AcaoVirtual selecionada) {
		this.selecionada = selecionada;
	}

	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

	public void setLista(List<AcaoVirtual> lista) {
		this.lista = lista;
	}

	public void setLinkCodigoAcao(String linkCodigoAcao) {
		this.linkCodigoAcao = linkCodigoAcao;
	}

	public void setPercentualQuantidade(PieChartModel percentualQuantidade) {
		this.percentualQuantidade = percentualQuantidade;
	}

	public void setPercentualValor(PieChartModel percentualValor) {
		this.percentualValor = percentualValor;
	}

}
