package com.grupo3.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.grupo3.service.JogoService;

public class auxLog {
	
	@Autowired
	private JogoService  jogoservice;
	
	
	public boolean ativo(String session) {
	if(session != null && session.equals("ativo")) 
	{
	System.out.println("logado :)");
	return true;
	}
	System.out.println(session);
	System.out.println("Nao logado :(");
	return false;
	}
	
	public String  role(String role) {
			return role;
	}
}
