package com.grupo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo3.Model.Compra;
import com.grupo3.Model.Jogo;
import com.grupo3.dao.CompraDao;
import com.grupo3.dao.GameDao;

@Service
public class CompraService {
	@Autowired
	private  CompraDao compra;
	
	
	public Page<Compra> allCompra(Pageable pagReq){
		return compra.AllCompras(pagReq);
	}

}
