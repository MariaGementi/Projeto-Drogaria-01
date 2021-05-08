package br.com.drogaria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.FacesUtil;

@ManagedBean // mensagems de acerto ou erro dos botoes
@ViewScoped
public class FabricanteBean {
	// modelo- varios objetos que guardao o modelo da tela
	private Fabricante fabricanteCadastro;

	// filtrando os fabricantes
	private List<Fabricante> listaFabricante;
	private List<Fabricante> listaFabricantesFiltrados;

	private String acao; 
	private Long codigo;

	public Fabricante getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(Fabricante fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public void novo() {
		fabricanteCadastro = new Fabricante();
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	
	public List<Fabricante> getListaFabricante() {
		return listaFabricante;
	}

	public List<Fabricante> getListaFabricantesFiltrados() {
		return listaFabricantesFiltrados;
	}

	public void setListaFabricante(List<Fabricante> listaFabricante) {
		this.listaFabricante = listaFabricante;
	}

	public void setListaFabricantesFiltrados(List<Fabricante> listaFabricantesFiltrados) {
		this.listaFabricantesFiltrados = listaFabricantesFiltrados;
	}

	// salvar
	public void salvar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.salvar(fabricanteCadastro);

			fabricanteCadastro = new Fabricante();// reinstanciei o fabrincante, limpando assim os campos

			FacesUtil.adicionarMsgInfo("Fabricante salvo com sucesso!");

		} catch (RuntimeException ex) {
			// ex.printStackTrace(); // debuga linha por linha
			FacesUtil.adicionarMsgErro("Erro ao tentar incluir um fabricante" + ex.getMessage());
		}
	}

	public void carregar() { // carrega a pesquisa
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			listaFabricante = fabricanteDAO.listar();

		} catch (RuntimeException ex) {
			// ex.printStackTrace(); // debuga linha por linha
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os fabricantes" + ex.getMessage());
		}
	}

	public void carregarCadastro() {
		try {
			
			if (codigo != null) {
				FabricanteDAO fabricanteDAO = new FabricanteDAO();
				fabricanteCadastro = fabricanteDAO.buscarPorCodigo(codigo);
			} else {
				fabricanteCadastro = new Fabricante();
			}
		} catch (RuntimeException ex) {
			// ex.printStackTrace(); // debuga linha por linha
			FacesUtil.adicionarMsgErro("Erro ao tentar obter os dados do fabricante:" + ex.getMessage());
		}

	}

	// excluir
	public void excluir() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.excluir(fabricanteCadastro);

			FacesUtil.adicionarMsgInfo("Fabricante removido com sucesso!");

		} catch (RuntimeException ex) {
			// ex.printStackTrace(); // debuga linha por linha
			FacesUtil.adicionarMsgErro("Erro ao tentar remover o fabricante" + ex.getMessage());
		}
	}

	// editar
	public void editar() {
		try {
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricanteDAO.editar(fabricanteCadastro);

			FacesUtil.adicionarMsgInfo("Fabricante editado com sucesso!");

		} catch (RuntimeException ex) {
			// ex.printStackTrace(); // debuga linha por linha
			FacesUtil.adicionarMsgErro("Erro ao tentar editar os dados do fabricante" + ex.getMessage());
		}
	}
}
