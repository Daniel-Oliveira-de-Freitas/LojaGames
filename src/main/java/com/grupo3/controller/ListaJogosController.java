package com.grupo3.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.grupo3.Model.Jogo;
import com.grupo3.dao.GameDao;
import com.grupo3.service.JogoService;

@Controller
public class ListaJogosController {
	
	@Autowired
	private JogoService  jogoservice;

	//Função que carrega a pagina para o cliente em que ela pega o map da pagina de cadastro jogos
	//e seta na url cadastrarJogo
	@GetMapping("/listarJogos")
	public ModelAndView searchAll(@RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listaJogosCliente");
	    mv.addObject("jogo", new Jogo());
	    Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
		Page<Jogo> paginaResult = this.jogoservice.allJogo(pagreq);
		mv.addObject("allJogo", paginaResult);
		return mv;
	}
	
	@PostMapping("search-resultCliente")
	public ModelAndView resultPesquisa(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("buscaResultadoJogoCliente");
		List<Jogo> nomeJogos;
		if (nome == null || nome.trim().isEmpty()) {
			nomeJogos = this.jogoservice.listarTodosJogos(Sort.by("nome"));
		} else {
			nomeJogos = this.jogoservice.buscaJogos(nome);
		}

		mv.addObject("nomeJogos", nomeJogos);
		return mv;
	}

}
