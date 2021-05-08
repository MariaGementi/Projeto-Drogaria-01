package br.com.drogaria.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.ItensDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendasDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Itens;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Vendas;
import br.com.drogaria.filter.VendasFilter;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@ViewScoped
public class VendaBean {

	private List<Produto> listaProdutos;
	private List<Produto> ListaProdutosFiltrados;

	private Vendas vendaCadastro;
	private List<Itens> listaItens;

	@ManagedProperty(value = "#{autenticacaoBean}") // pegar o objeto que está instanciado na memoria e colocar no
													// objeto
	private AutenticacaoBean autenticacaoBean; // injeção

	private VendasFilter filtro;

	private List<Vendas> listaVendasFiltradas;

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosFiltrados() {
		return ListaProdutosFiltrados;
	}

	public void setListaProdutosFiltrados(List<Produto> listaProdutosFiltrados) {
		ListaProdutosFiltrados = listaProdutosFiltrados;
	}

	public List<Itens> getListaItens() {
		if (listaItens == null) {
			listaItens = new ArrayList<>();
		}
		return listaItens;
	}

	public void setListaItens(List<Itens> listaItens) {
		this.listaItens = listaItens;
	}

	public Vendas getVendaCadastro() {
		if (vendaCadastro == null) {
			vendaCadastro = new Vendas();
			vendaCadastro.setValor(new BigDecimal("0.00"));
			vendaCadastro.setQuantidade(0);
		}
		return vendaCadastro;
	}

	public void setVendaCadastro(Vendas vendaCadastro) {
		this.vendaCadastro = vendaCadastro;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public List<Vendas> getListaVendasFiltradas() {
		return listaVendasFiltradas;
	}

	public void setListaVendasFiltradas(List<Vendas> listaVendasFiltradas) {
		this.listaVendasFiltradas = listaVendasFiltradas;
	}

	//////////////////////////////////////////////////////////
	public void carregarProdutos() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listaProdutos = produtoDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os produtos" + ex.getMessage());
		}
	}

	public VendasFilter getFiltro() {
		if (filtro == null) {
			filtro = new VendasFilter();
		}
		return filtro;
	}

	public void setFiltro(VendasFilter filtro) {
		this.filtro = filtro;
	}

	//////////////////////////////////////////////////

	public void adicionar(Produto produto) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			Itens itemTemp = listaItens.get(pos);

			if (itemTemp.getProduto().equals(produto)) {
				posicaoEncontrada = pos;
			}
		}

		Itens itens = new Itens();
		itens.setProduto(produto);

		if (posicaoEncontrada < 0) {
			itens.setQuantidade(1);
			itens.setValor(produto.getPreco());
			listaItens.add(itens);
		} else {
			Itens itemTemp = listaItens.get(posicaoEncontrada);
			itens.setQuantidade(itemTemp.getQuantidade() + 1);
			itens.setValor(produto.getPreco().multiply(new BigDecimal(itens.getQuantidade())));
			listaItens.set(posicaoEncontrada, itens);
		}

		vendaCadastro.setValor(vendaCadastro.getValor().add(produto.getPreco()));
		vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() + 1);
	}

	//////////////////////////////////////////

	public void remover(Itens itens) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			Itens itemTemp = listaItens.get(pos);

			if (itemTemp.getProduto().equals(itens.getProduto())) {
				posicaoEncontrada = pos;
			}
		}
		if (posicaoEncontrada > -1) {
			listaItens.remove(posicaoEncontrada);
			vendaCadastro.setValor(vendaCadastro.getValor().subtract(itens.getValor()));
			vendaCadastro.setQuantidade(vendaCadastro.getQuantidade() - itens.getQuantidade());
		}
	}

	//////////////////////////////////////////
	public void carregarDadosVenda() {
		vendaCadastro.setHorario(new Date());

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodigo());
		vendaCadastro.setFuncionario(funcionario);
	}

	/////////////////////////////////////////

	public void salvar() {
		try {
			VendasDAO vendasDAO = new VendasDAO();
			int codigoVenda = vendasDAO.salvar(vendaCadastro);
			Vendas vendaFK = vendasDAO.buscarPorCodigo(codigoVenda);

			for (Itens itens : listaItens) {
				itens.setVenda(vendaFK);

				ItensDAO itensDAO = new ItensDAO();
				itensDAO.salvar(itens);
			}

			vendaCadastro = new Vendas();
			vendaCadastro.setValor(new BigDecimal("0.00"));

			listaItens = new ArrayList<Itens>();

			FacesUtil.adicionarMsgInfo("Venda salva com sucesso!");
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar salvar a venda: " + ex.getMessage());
		}

	}

	//////////////////////////////////
	public void buscar() {
		try {
			VendasDAO vendasDAO = new VendasDAO();
			listaVendasFiltradas = vendasDAO.buscar(filtro);
			
			for (Vendas vendas : listaVendasFiltradas) {
				System.out.println(vendas);
			}
		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar buscar a venda: " + ex.getMessage());
		}

	}
}
