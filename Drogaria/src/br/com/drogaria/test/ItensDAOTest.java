package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ItensDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.dao.VendasDAO;
import br.com.drogaria.domain.Itens;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.domain.Vendas;

public class ItensDAOTest {
	@Test
	@Ignore
	public void salvar() {
		// chave estrangeira
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(1);
		// chave estrangeira
		VendasDAO vendasDAO = new VendasDAO();
		Vendas vendas = vendasDAO.buscarPorCodigo(8);

		Itens itens = new Itens();
		itens.setProduto(produto);
		itens.setQuantidade(98);
		itens.setValor(new BigDecimal(799.5));
		itens.setVenda(vendas);

		ItensDAO itensDAO = new ItensDAO();
		itensDAO.salvar(itens);
	}

	@Test
	@Ignore
	public void listar() {
		ItensDAO itensDAO = new ItensDAO();
		List<Itens> itens = itensDAO.listar();
		System.out.println(itens);

	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		ItensDAO itensDAO = new ItensDAO();
		Itens itens = itensDAO.buscarPorCodigo(1);
		System.out.println(itens);
	}
	@Test
	@Ignore
	public void excluir() {
		ItensDAO itensDAO = new ItensDAO();
		Itens itens = itensDAO.buscarPorCodigo(1);
		itensDAO.excluir(itens);
	}
	
	@Test
	public void editar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(2);
		
		VendasDAO vendasDAO = new VendasDAO();
		Vendas vendas = vendasDAO.buscarPorCodigo(3);
		
		ItensDAO itensDAO = new ItensDAO();
		Itens itens = itensDAO.buscarPorCodigo(2);
		
		itens.setProduto(produto);
		itens.setQuantidade(4);
		itens.setValor(new BigDecimal(32.58));
		itens.setVenda(vendas);
		
		itensDAO.editar(itens);
	}
}
