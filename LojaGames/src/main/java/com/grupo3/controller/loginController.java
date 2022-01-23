 package com.grupo3.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo3.Model.Cliente;
import com.grupo3.service.ClienteService;
import com.grupo3.service.JogoService;

@Controller
public class loginController {
	private Cliente loginuser;
	@Autowired
	private ClienteService  clienteService;
	
	@GetMapping("/login")
	public ModelAndView login(@ModelAttribute Cliente user) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("user", new Cliente());
		return mv;
	}
	
	
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute  Cliente user, HttpSession session, auxLog a) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("cliente", new Cliente());
			 
			try {
				clienteService.atividadeUser(user.getEmail(), user.getSenha(),"ativo");
				loginuser = this.clienteService.efetuarlogin(user.getEmail(), user.getSenha());
				session.setAttribute("userLogado", loginuser);
				session.getAttribute("userLogado");
				a.ativo(loginuser.getAtividade());
				a.role(loginuser.getRole());
			} catch (ServiceException e) {
				mv.addObject("msgLoginErro", e.getMessage());
				return mv;
			}
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, auxLog a) {
		if(session != null && loginuser != null) {
			clienteService.atividadeUser(loginuser.getEmail(), loginuser.getSenha(),"inativo");
		loginuser = this.clienteService.efetuarlogin(loginuser.getEmail(), loginuser.getSenha());
		a.ativo(loginuser.getAtividade());
		session.invalidate();
		}
		return "redirect:/login";
	}
	

}
