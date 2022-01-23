package com.grupo3.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.grupo3.Model.Cliente;
import com.grupo3.dao.ClienteDao;

@Controller
public class CadastroClienteController {
	
	@Autowired
	private ClienteDao clienteRepositorio;

	    @GetMapping("/cadastrarCliente")
	    public ModelAndView cadastroCliente(Cliente cliente ){
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("/cadastroCliente");
	        mv.addObject("cliente", new Cliente());
	        return mv;
	    }
	    
	    @PostMapping("newCliente")
	    public ModelAndView clienteCadastrado(Cliente cliente) {
	    	ModelAndView mv = new ModelAndView();
	    	mv.setViewName("redirect:/");
	    	cliente.setRole("USER");
	    	cliente.setAtividade("inativo");
	    	clienteRepositorio.save(cliente);
	    	return mv;
	    }
	    
	   
	    
}
