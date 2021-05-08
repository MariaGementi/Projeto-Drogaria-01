package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.HibernateUtil;

public class FabricanteDAO {
	// salvando um fabricante
	public void salvar(Fabricante fabricante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; // inicia uma transação nula

		try { // bloco try - lida com erros-
			transacao = sessao.beginTransaction(); // inicia a transação
			sessao.save(fabricante);
			transacao.commit(); // confirma a transação
		} catch (RuntimeException ex) {
			if (transacao != null) { // entrou, deu erro e caiu aqui
				transacao.rollback(); // desfazer transação
			}

			// throw ex; - quem chamar o metodo precisa tratar ele - desfazer e repropagar a
			// expressao

		} finally { // finalizador
			sessao.close();
		}

	}

	// listar os fabricantes
	@SuppressWarnings("unchecked")
	public List<Fabricante> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Fabricante> fabricantes = null;

		try {
			Query consulta = sessao.getNamedQuery("Fabricante.listar");
			fabricantes = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return fabricantes;
	}

	// buscar por codigo um fabricante
	public Fabricante buscarPorCodigo(long fabricante2) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Fabricante fabricante = null;

		try {
			Query consulta = sessao.getNamedQuery("Fabricante.buscarPorCodigo");
			consulta.setLong("codigo", fabricante2);
			fabricante = (Fabricante) consulta.uniqueResult(); // usado quando tem certeza de um unico restado
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return fabricante;
	}

	// excluir por entidade
	public void excluir(Fabricante fabricante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null; 

		try { 
			transacao = sessao.beginTransaction(); 
			sessao.delete(fabricante);
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
	
	// editar um fabricante
	public void editar(Fabricante fabricante) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			// pego os dados velhos -------------- armazeno os dados novos
			// Fabricante fabricanteEditar = buscarPorCodigo((long) fabricante.getCodigo());
			// fabricanteEditar.setDescricao(fabricante.getDescricao());
			// sessao.update(fabricanteEditar);
			sessao.update(fabricante); // valida a tela, certeza que todos os campos foram preenchidos
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
}
