package br.com.drogaria.test;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;

public class FuncionarioDAOTest {

	// salvar
	@Test
	@Ignore
	public void salvar() {
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("111.111.111-12");
		funcionario.setFuncao("DEV");
		funcionario.setNome(" SILVA");
		funcionario.setSenha("q1w2e3r5");

		FuncionarioDAO dao = new FuncionarioDAO();

		dao.Salvar(funcionario);
	}

	// listar
	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(1);
		System.out.println(funcionario);
	}

	// buscar
	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(1);
		System.out.println(funcionario);
	}

	// excluir
	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(1);
		dao.excluir(funcionario);
	}

	// editar
	@Test
	@Ignore
	public void editar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(2);
		
		funcionario.setCpf("444.444.444-44");
		funcionario.setFuncao("USUARIO");
		funcionario.setNome("JOSE DA SILVA");
		funcionario.setSenha("q1w2e3r4t5y6");
		
		dao.editar(funcionario);
	}
	
	@Test
	public void autenticar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.autenticar("495.781.668-93","12345678");
		
		System.out.println("Funcionario: " + funcionario);
	}
	
	
}
