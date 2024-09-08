package br.com.pc2.rh.persistence;

import java.util.List;

import br.com.pc2.rh.model.Hospedagem;

public interface HospedagemDAO {
	public String inserir(Hospedagem emp);
	public String alterar(Hospedagem emp);
	public String excluir(Hospedagem emp);
	public  List<Hospedagem> listarTodos();

}