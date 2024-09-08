package br.com.pc2.rh.model;

public class Chale {
	
	private Integer codChale;
	private String localizacao;
	private Integer capacidade;
	private double valorAltaEstacao;
	private double valorBaixaEstacao;
	
	public Chale() {
		
	}
	
	public Integer getCodChale() {
		return codChale;
	}
	public void setCodChale(Integer codChale) {
		this.codChale = codChale;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public Integer getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	public double getValorAltaEstacao() {
		return valorAltaEstacao;
	}
	public void setValorAltaEstacao(double valorAltaEstacao) {
		this.valorAltaEstacao = valorAltaEstacao;
	}
	public double getValorBaixaEstacao() {
		return valorBaixaEstacao;
	}
	public void setValorBaixaEstacao(double valorBaixaEstacao) {
		this.valorBaixaEstacao = valorBaixaEstacao;
	}

}
