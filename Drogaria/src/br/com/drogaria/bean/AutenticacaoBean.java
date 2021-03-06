package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String autenticar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getCpf(),
					DigestUtils.md5Hex(funcionarioLogado.getSenha()));
			if (funcionarioLogado == null) {
				FacesUtil.adicionarMsgErro("CPF e/ou senha inválidos");
				return null; // fico na mesma pagina 
			} else {
				FacesUtil.adicionarMsgInfo("Funcionario autenticado com sucesso");
				return "/pages/principal.xhtml?faces-redirect=true";
			}

		} catch (RuntimeException ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar entrar no sistema:" + ex.getMessage());
			return null;
		}
	}

	public String sair() {
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
}
