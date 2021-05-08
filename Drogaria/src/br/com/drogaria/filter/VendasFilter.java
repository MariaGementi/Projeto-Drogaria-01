package br.com.drogaria.filter;

import java.util.Date;

public class VendasFilter {

	private Date dataInicial;
	private Date dataFinal;
	
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}
}
