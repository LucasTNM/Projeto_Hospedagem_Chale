package br.com.pc2.rh.persistence;

import java.util.List;

import br.com.pc2.rh.model.Cliente;

public interface ClienteDAO {
	public String inserir(Cliente cl);
	public String alterar(Cliente cl);
	public String excluir(Cliente cl);
	public List<Cliente> listarTodos();
}
