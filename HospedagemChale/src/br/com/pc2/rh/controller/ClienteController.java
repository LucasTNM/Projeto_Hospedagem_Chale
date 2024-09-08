package br.com.pc2.rh.controller;

import java.util.List;

import br.com.pc2.rh.model.Cliente;
import br.com.pc2.rh.persistence.ClienteDAOImpl;

public class ClienteController {
	public String inserir(Cliente dep) {
		ClienteDAOImpl dao = new ClienteDAOImpl();
		return dao.inserir(dep);
	}

	public String alterar(Cliente dep) {
		ClienteDAOImpl dao = new ClienteDAOImpl();
		return dao.alterar(dep);
	}

	public String excluir(Cliente dep) {
		ClienteDAOImpl dao = new ClienteDAOImpl();
		return dao.excluir(dep);
	}

	public List<Cliente> listarTodos() {
		ClienteDAOImpl dao = new ClienteDAOImpl();
		return dao.listarTodos();
	}

}

