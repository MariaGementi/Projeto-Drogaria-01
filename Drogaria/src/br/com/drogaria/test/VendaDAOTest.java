package br.com.drogaria.test;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.VendasDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Vendas;
import br.com.drogaria.filter.VendasFilter;

public class VendaDAOTest {

	@Test
	@Ignore
	public void salvar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(3);
		
		Vendas vendas = new Vendas();
		vendas.setFuncionario(funcionario);
		vendas.setHorario(new Date());
		vendas.setValor(new BigDecimal(1.30));
		
		VendasDAO vendasDAO  = new VendasDAO();
		vendasDAO.salvar(vendas);
	}
	
	@Ignore
	@Test
	public void listar() {
		VendasDAO vendaDAO = new VendasDAO();
		List<Vendas> vendas = vendaDAO.listar();
		System.out.println(vendas);

		}
	
	@Ignore
	@Test
	public void buscarPorCodigo() {
		VendasDAO vendaDAO = new VendasDAO();
		Vendas vendas = vendaDAO.buscarPorCodigo(2);
		System.out.println(vendas);
	}

	@Ignore
	@Test
	public void excluir() {
		VendasDAO vendaDAO = new VendasDAO();
		Vendas vendas = vendaDAO.buscarPorCodigo(2);
		vendaDAO.excluir(vendas);
	}
	
	@Ignore
	@Test
	public void editar () {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscarPorCodigo(3);
		
		VendasDAO vendasDAO = new VendasDAO();
		Vendas vendas = vendasDAO.buscarPorCodigo(6);
		
		vendas.setHorario(Calendar.getInstance().getTime());
		vendas.setValor(new BigDecimal(2.50));
		vendas.setFuncionario(funcionario);
		vendasDAO.editar(vendas);
		
	}
	
	@Test
	@Ignore
	public void buscar() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		VendasFilter filtro = new VendasFilter();
		filtro.setDataInicial(formato.parse("31/12/2020"));
		filtro.setDataFinal(formato.parse("23/01/2021"));
		
		VendasDAO vendasDAO = new VendasDAO();
		List<Vendas> vendas = vendasDAO.buscar(filtro);
		
		for (Vendas venda : vendas) {
			System.out.println(venda);
		}
	}
}
