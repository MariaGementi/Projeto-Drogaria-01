package br.com.drogaria.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;

import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {

	// salvar produtos
	@Test
	@Ignore
	public void inserir() {
		// buscou pelo fabricante
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(1);

		// INSERIU O PRODUTO
		Produto produto = new Produto();
		produto.setDescricao("DESCRICAOZ");
		produto.setPreco(new BigDecimal(20.58D));
		produto.setQuantidade(23);
		produto.setFabricante(fabricante); // chamei o numero do fabricante aqui

		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}

	// buscar produtos
	@Test
	@Ignore
	public void buscarPorCodigo() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(3);
		System.out.println(produto);
	}
	
	@Test
	@Ignore
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> produtos = produtoDAO.listar();
		System.out.println(produtos);
	}
	
	@Test
	@Ignore
	public void excluir() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(3);
		produtoDAO.excluir(produto);
	}
	
	@Test
	@Ignore
	public void editar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscarPorCodigo(2);
		produto.setDescricao("DESCRICAOA");
		produto.setPreco(new BigDecimal(6.39));
		produto.setQuantidade(9);
		
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscarPorCodigo(1L);
		produto.setFabricante(fabricante);
		
		produtoDAO.editar(produto);
	}
	
}


