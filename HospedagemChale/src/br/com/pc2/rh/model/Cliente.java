package br.com.pc2.rh.model;

import java.time.LocalDate;

public class Cliente {
	private Integer codCliente;
	private String nomeCliente;
	private String rgCliente;
	private String endereco;
    private String bairro;
	private String cidade;
    private String estado;
	private String CEP;
    private LocalDate dataNascimento;

	public Cliente() {
	}
	
	public int getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
    
	 public String getNome() {
        return nomeCliente;
    }
    public void setNome(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getRg() {
        return rgCliente;
    }
    public void setRg(String rgCliente) {
        this.rgCliente = rgCliente;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return CEP;
    }
    public void setCep(String CEP) {
        this.CEP = CEP;
    }

    public LocalDate getNascimento() {
        return dataNascimento;
    }
    public void setNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


}

