package com.grupo3.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.grupo3.Model.Compra;
import com.grupo3.Model.Jogo;
import com.grupo3.service.CompraService;
import com.grupo3.service.JogoService;

@Controller
public class RelatorioController {
	@Autowired
	private CompraService  compraservice;
	//Função que carrega a pagina para o cliente em que ela pega o map da pagina de cadastro jogos
		//e seta na url cadastrarJogo
		@GetMapping("/listarVendasDiarias")
		public ModelAndView searchTodos(@RequestParam(defaultValue = "1") int page) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("relatorioDiarias");
		    mv.addObject("compra", new Compra());
		    Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nomeJogo"));
			Page<Compra> paginaResult = this.compraservice.allCompra(pagreq);
			mv.addObject("allCompra", paginaResult);
			return mv;
		}
}
