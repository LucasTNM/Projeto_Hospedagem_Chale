package br.com.pc2.rh.persistence;

import br.com.pc2.rh.model.Chale;

import java.util.List;


public interface ChaleDAO {
	public String inserir(Chale ch);
	public String alterar(Chale ch);
	public String excluir(Chale ch);
	public  List<Chale> listarTodos();

}
