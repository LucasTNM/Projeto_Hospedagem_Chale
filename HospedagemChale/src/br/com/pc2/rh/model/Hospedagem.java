package br.com.pc2.rh.model;

import java.time.LocalDate;

public class Hospedagem {
    private Integer codHospedagem;
    private Integer codChale;
	private String estado;
	private LocalDate data_inicio;
	private LocalDate data_fim;
    private Integer qtd_pessoas;
	private Double desconto;
	private Double valor_final;
    private Integer cod_cliente;
    
    public Integer getCodHospedagem() {
    	return codHospedagem;
    }
    public void setCodHospedagem(Integer codHospedagem) {
    	this.codHospedagem = codHospedagem;
    }

    public Integer getCodChale() {
    	return codChale;
    }
    public void setCodChale(Integer codChale) {
    	this.codChale = codChale;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }
    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }
    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public Integer getQtd_pessoas() {
        return qtd_pessoas;
    }
    public void setQtd_pessoas(Integer qtd_pessoas) {
        this.qtd_pessoas = qtd_pessoas;
    }

    public Double getDesconto() {
        return desconto;
    }
    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValor_final() {
        return valor_final;
    }
    public void setValor_final(Double valor_final) {
        this.valor_final = valor_final;
    }

    public Integer getCodCliente() {
        return cod_cliente;
    }
    public void setCodCliente(Integer cod_cliente) {
        this.cod_cliente = cod_cliente;
    }



}
