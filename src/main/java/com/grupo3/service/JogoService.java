package com.grupo3.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.grupo3.Model.Cliente;
import com.grupo3.Model.Jogo;
import com.grupo3.dao.ClienteDao;
import com.grupo3.dao.GameDao;


@Service
public class JogoService {
	@Autowired
	private GameDao jogo;
	
	public Page<Jogo> allJogo(Pageable pagReq){
		return jogo.AllJogos(pagReq);
	}
	
	public void excluirJogo(Integer id) {
		jogo.deleteById(id);
	}
	
	public List<Jogo> buscaJogos(String nome){
		return jogo.findByNomeContainingIgnoreCase(nome);
	}
	
	public List<Jogo> listarTodosJogos(Sort sort){
		return jogo.findAll();
	}

	
	public Jogo editarJogo(int id) throws ServiceException{
		Jogo jogo = this.jogo.EncontrePeloID(id);
		if(jogo == null) {
			throw new ServiceException("Erro ao editar!");
		}
		return jogo;
	}
}
