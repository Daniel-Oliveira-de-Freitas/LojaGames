package com.grupo3.service;

import java.util.List;


import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.grupo3.Model.Cliente;
import com.grupo3.dao.ClienteDao;

@Service
public class ClienteService {
	@Autowired
	private ClienteDao cliente;
	
	public Page<Cliente> allCliente(Pageable pagReq){
		return cliente.AllCliente(pagReq);
	}
	
	public List<Cliente> listarTodosClientes(Sort sort){
		return cliente.findAll();
	}
	
	public List<Cliente> buscaClientes(String nome){
		return cliente.findByNomeContainingIgnoreCase(nome);
	}
	
	public void excluirCliente(Integer id) {
		cliente.deleteById(id);
	}

	public Cliente editarCliente(Integer id) throws ServiceException {
		Cliente cliente = this.cliente.EncontraCliId(id);
		if(cliente == null) {
			throw new ServiceException("Erro ao Editar");
		}
		return cliente;
	}
	
	public Cliente efetuarlogin(String email, String senha) throws ServiceException{
		Cliente userlogin = this.cliente.findByLogin(email, senha);
		if(userlogin == null) {
			throw new ServiceException("Email ou senha inválidos!");
		}
		return userlogin;	
		}
	
	public void atividadeUser(String email, String senha,String atividade) {
		cliente.logado(email, senha,atividade);
		}

}
