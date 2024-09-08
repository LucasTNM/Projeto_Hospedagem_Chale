package br.com.pc2.rh.controller;

import java.util.List;

import br.com.pc2.rh.model.Chale;
import br.com.pc2.rh.persistence.ChaleDAOImpl;

public class ChaleController {
	
	public String inserir(Chale ch) {
		ChaleDAOImpl dao = new ChaleDAOImpl();
		return dao.inserir(ch);
	}
	
	public String alterar(Chale ch) {
		ChaleDAOImpl dao = new ChaleDAOImpl();
		return dao.alterar(ch);
	}
	
	public String excluir(Chale ch) {
		ChaleDAOImpl dao = new ChaleDAOImpl();
		return dao.excluir(ch);
	}
	
	public List<Chale> listarTodos() {
		ChaleDAOImpl dao = new ChaleDAOImpl();
		return dao.listarTodos();
	}

}
