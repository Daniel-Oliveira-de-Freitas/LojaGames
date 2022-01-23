package com.grupo3.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo3.Model.Cliente;
import com.grupo3.dao.ClienteDao;

public class LoginService {
	
	@Autowired
	private ClienteDao login;
	
	public Cliente efetuarlogin(String email, String senha) throws ServiceException{
		Cliente userlogin = this.login.findByLogin(email, senha);
		if(userlogin == null) {
			throw new ServiceException("Email ou senha inválidos!");
		}
		return userlogin;
	}
	
}
