package br.com.pc2.rh.controller;

import java.util.List;

import br.com.pc2.rh.model.Hospedagem;
import br.com.pc2.rh.persistence.HospedagemDAOImpl;

public class HospedagemController {
	public String inserir(Hospedagem emp) {
		HospedagemDAOImpl dao = new HospedagemDAOImpl();
		return dao.inserir(emp);
	}
	
	public String alterar(Hospedagem emp) {
		HospedagemDAOImpl dao = new HospedagemDAOImpl();
		return dao.alterar(emp);
	}
	
	public String excluir(Hospedagem emp) {
		HospedagemDAOImpl dao = new HospedagemDAOImpl();
		return dao.excluir(emp);
	}

	public List<Hospedagem> listarTodos() {
		HospedagemDAOImpl dao = new HospedagemDAOImpl();
		return dao.listarTodos();
	}
}
