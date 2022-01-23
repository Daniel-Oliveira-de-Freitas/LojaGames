package com.grupo3.controller;
import com.grupo3.Model.Jogo;
import com.grupo3.dao.GameDao;
import com.grupo3.service.JogoService;

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

@Controller
public class GerenciamentoJogos {

		@Autowired
		private JogoService  jogoservice;
		@Autowired
		private GameDao gameRepositorio;
		private Jogo jogoEdicao;
		//Função que carrega a pagina para o cliente em que ela pega o map da pagina de cadastro jogos
		//e seta na url cadastrarJogo
		
	    @GetMapping("/CadastrarJogo")
	    public ModelAndView cadastroJogo(Jogo jogo){
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("/cadastroJogos");
	        mv.addObject("jogo",new Jogo());
	        return mv;
	    }
		
	    @PostMapping("newJogo")
	    public ModelAndView jogoCadastrado(Jogo jogo) {
	    	 ModelAndView mv = new ModelAndView();
	    	 mv.setViewName("redirect:/");
	    	 gameRepositorio.save(jogo);
	    	 return mv;
	    }
	    
		@GetMapping("/listarJogosAdmin")
		public ModelAndView searchAll(@RequestParam(defaultValue = "1") int page) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("gerenciarJogos");
			mv.addObject("jogo", new Jogo());
			Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
			Page<Jogo> paginaResult = this.jogoservice.allJogo(pagreq);
			mv.addObject("allJogo", paginaResult);
			return mv;
		}
		
		@GetMapping("excluir-jogo")
		public ModelAndView removerJogo(@RequestParam Integer id, @RequestParam(defaultValue = "1") int page) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("listaJogos");
			mv.addObject("jogo", new Jogo());
			Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
			Page<Jogo> paginaResult = this.jogoservice.allJogo(pagreq);
			mv.addObject("allJogo", paginaResult);
			this.jogoservice.excluirJogo(id);
			mv.addObject("msgExclusao", "Jogo Excluido");
			mv.setViewName("redirect:/listarJogos");
			return mv;
		}
		
		@PostMapping("search-result")
		public ModelAndView resultPesquisa(@RequestParam(required = false) String nome) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("buscaResultado");
			List<Jogo> nomeJogos;
			if (nome == null || nome.trim().isEmpty()) {
				nomeJogos = this.jogoservice.listarTodosJogos(Sort.by("nome"));
			} else {
				nomeJogos = this.jogoservice.buscaJogos(nome);
			}

			mv.addObject("nomeJogos", nomeJogos);
			return mv;
		}
		
		@PostMapping("editarTela")
		public ModelAndView editarPesquisa(@RequestParam(required = false) String nome) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("editarJogo");
			List<Jogo> nomeJogo;
			if (nome != null) {
				nomeJogo = this.jogoservice.buscaJogos(nome);
				mv.addObject("nomeJogo", nomeJogo);
			}
			return mv;
		}
		

		@GetMapping("editarJogoAdmin")
		public ModelAndView editarAluno(@RequestParam Integer id) {
			ModelAndView mv = new ModelAndView("editarJogo");
			jogoEdicao= this.jogoservice.editarJogo(id);
			mv.addObject("jogo", jogoEdicao);
			System.out.println(id);
			return mv;
		}

		
		@PostMapping("editar-jogo")
		public ModelAndView editaJogo(Jogo jogo) {
			ModelAndView mv = new ModelAndView();
			jogoservice.excluirJogo(jogoEdicao.getId());
			gameRepositorio.save(jogo);
			mv.setViewName("redirect:/");
			return mv;
		}
		

	}


