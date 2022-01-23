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

import com.grupo3.Model.Cliente;
import com.grupo3.dao.ClienteDao;
import com.grupo3.service.ClienteService;

@Controller
public class ListaClienteController {
	@Autowired
	private ClienteService  clienteservice;
	@Autowired
	private ClienteDao clienteRepositorio;
	private Cliente clienteEdicao;
	
	@GetMapping("/listarClientes")
	public ModelAndView listar(@RequestParam(defaultValue = "1") int page) {
	ModelAndView mv = new ModelAndView();
	mv.setViewName("listaClientes");
	mv.addObject("cliente", new Cliente());
	Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
	Page<Cliente> paginaResult = this.clienteservice.allCliente(pagreq);
	mv.addObject("allCliente", paginaResult);
	return mv;
	}
	
	@PostMapping("pesquisar-cliente")
	public ModelAndView resultPesquisa(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("buscaResultado");
		List<Cliente> nomeClientes;
		if (nome == null || nome.trim().isEmpty()) {
			nomeClientes = this.clienteservice.listarTodosClientes(Sort.by("nome"));
		} else {
			nomeClientes = this.clienteservice.buscaClientes(nome);
		}

		mv.addObject("nomeClientes", nomeClientes);
		return mv;
	}
	
	@GetMapping("excluir-cliente")
	public ModelAndView removerCliente(@RequestParam Integer id, @RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listaCliente");
		mv.addObject("cliente", new Cliente());
		Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
		Page<Cliente> paginaResult = this.clienteservice.allCliente(pagreq);
		mv.addObject("allCliente", paginaResult);
		this.clienteservice.excluirCliente(id);
		mv.addObject("msgExclusao", "Cliente Excluido");
		mv.setViewName("redirect:/listarClientes");
		return mv;
	}
	
	 @GetMapping("/editarCliente")
	    public ModelAndView editarCliente(@RequestParam Integer id){
	        ModelAndView mv = new ModelAndView("editaCliente");
	        clienteEdicao= this.clienteservice.editarCliente(id);
			mv.addObject("clienteEdit", clienteEdicao);
			System.out.println(id);

	        return mv;
	    }
	    
	    @PostMapping("editar-cliente")
		public ModelAndView editaCliente(Cliente cliente) {
			ModelAndView mv = new ModelAndView();
			clienteservice.excluirCliente(clienteEdicao.getId());
			clienteRepositorio.save(cliente);
			mv.setViewName("redirect:/");

	    	return mv;
	    }
	    
	   
	
	 

}
