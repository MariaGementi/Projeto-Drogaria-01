package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Vendas;
import br.com.drogaria.filter.VendasFilter;
import br.com.drogaria.util.HibernateUtil;

public class VendasDAO {

	// salvar
	public int salvar(Vendas vendas) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		int codigo = 0; // em vez de -1 ele usa null

		try {
			transacao = sessao.beginTransaction();
			codigo = (int) sessao.save(vendas);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
		return codigo;
	}

	// listar
	@SuppressWarnings("unchecked")
	public List<Vendas> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Vendas> vendas = null;

		try {
			Query consulta = sessao.getNamedQuery("Vendas.listar");
			vendas = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return vendas;
	}

	// buscar
	public Vendas buscarPorCodigo(long codigo) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Vendas vendas = null;

		try {
			Query consulta = sessao.getNamedQuery("Vendas.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			vendas = (Vendas) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return vendas;
	}

	// excluir
	public void excluir(Vendas vendas) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(vendas);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	// editar
	public void editar(Vendas vendas) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(vendas);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Vendas> buscar(VendasFilter filtro) {
		List<Vendas> vendas = null;

		StringBuilder sql = new StringBuilder();
		sql.append("Select vendas FROM Vendas vendas ");

		if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
			sql.append("WHERE vendas.horario BETWEEN :dataInicial AND :dataFinal ");

		}
		sql.append("ORDER BY vendas.horario ");

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Query consulta = sessao.createQuery(sql.toString());

			if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
				sql.append("WHERE vendas.horario BETWEEN :dataInicial AND :dataFinal ");
				consulta.setDate("dataInicial", filtro.getDataInicial());
				consulta.setDate("dataFinal", filtro.getDataFinal());
			}
			vendas = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return vendas;
	}
}
