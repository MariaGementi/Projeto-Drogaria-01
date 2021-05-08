package br.com.drogaria.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore // quando coloca um ignore é pq o teste será ignorado
	public void salvar() {
		Fabricante f1 = new Fabricante();
		f1.setDescricao("DESCRICAOA");

		Fabricante f2 = new Fabricante();
		f2.setDescricao("DESCRICAOB");

		FabricanteDAO dao = new FabricanteDAO();

		dao.salvar(f1);
		dao.salvar(f2);
	}

	
	// lista os fabricantes
	@Test
	@Ignore
	public void listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> fabricantes = dao.listar();

		// for aprimorado-percorre o array ou conjunto
		//for (Fabricante fabricante : fabricantes) {
		//	System.out.println(fabricante);

		System.out.print(fabricantes); // jeito mais facil de listar os fabricantes, mas sem formação
		}

	

	// buscar por codigo
	@Test
	@Ignore
	public void buscarPorCodigo() {
		FabricanteDAO dao = new FabricanteDAO();

		Fabricante f1 = dao.buscarPorCodigo(3L);
		Fabricante f2 = dao.buscarPorCodigo(7L);

		System.out.println(f1);
		System.out.println(f2);

	}
	
	// excluir um fabricante
	@Test
	@Ignore
	public void excluir() {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante fabricante = dao.buscarPorCodigo(4L);
		
		dao.excluir(fabricante);
		
	}
	
	// editar um fabricante
	@Test
	@Ignore
	public void editar() {
		FabricanteDAO dao = new FabricanteDAO();
		
		Fabricante fabricante = dao.buscarPorCodigo(6);
		
		fabricante.setDescricao("DESCRICAOX");
		
		dao.editar(fabricante);
	}
}
