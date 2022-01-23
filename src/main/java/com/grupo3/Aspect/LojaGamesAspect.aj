package com.grupo3.Aspect;

import org.springframework.web.servlet.ModelAndView;

public aspect LojaGamesAspect {
	private boolean login = false;
	private String permissao;

	// Ponto de corte
	//Ponto de corte que pega as operações de todas as classes da pasta controller, exceto a HomeController referente a tela home e a login controler do login
	pointcut isAutenticado() : execution (* com.grupo3.controller.*.*(..)) && !cflow(execution(* com.grupo3.controller.loginController.*(..)))
	&& !cflow(execution(* com.grupo3.controller.HomeController.*(..)));
	// CadastroClienteController
	pointcut isAutorizadoAdmin1() : execution (* com.grupo3.controller.ListaJogosController.*(..));
	
	pointcut isAutorizadoAdmin2() : execution (* com.grupo3.controller.GerenciamentoJogos.*(..));

	pointcut isAutorizadoAdmin3() : execution (* com.grupo3.controller.ListaClienteController.*(..));

	pointcut isAutorizadoAdmin4() : execution (* com.grupo3.controller.CadastroClienteController.*(..));
	
	pointcut isAutorizadoAdmin5() : execution (* com.grupo3.controller.RelatorioController.*(..));
	
	
	// Advice
	//Aspecto para capturar se o usuario esta ativo no sistema ou não
	after() returning(boolean s): call (* com.grupo3.controller.auxLog.ativo(..)) {
		login = s;
	}
	
	//Aspecto para capturara qual tipo de permissão do usuario
	after() returning(String s): call (* com.grupo3.controller.auxLog.role(..)) {
		permissao = s;
		System.out.println(s);
	}
	
	//Verifica se o cliente ou admin esta logado no sistema caso isso seja false força ele a volta a tela de login e realizar o login 
	//para que consiga continuar a usar o sistema
	Object around () : isAutenticado() {
	ModelAndView mv = new ModelAndView();
	if( login == true) {
		return proceed(); 
	}
	mv.setViewName("redirect:/login");
	return mv;
	}
	
	 //verifica se o usuario esta autorizado a ver a tela de admin para cadastro de jogos caso não é mandando para realizar a autenticação no sistema realizando o login
    Object around () : isAutorizadoAdmin1() {
    ModelAndView mv = new ModelAndView();
        if(login == true && permissao.equals("ADMIN")) {    
        	 mv.setViewName("redirect:/listarJogosAdmin");
             return mv;
    }
        mv.setViewName("redirect:/listarJogos");
        return mv;
    }
	
	
     //verifica se o usuario esta autorizado a ver a tela de admin para cadastro de jogos caso não é mandando para realizar a autenticação no sistema realizando o login
    Object around () : isAutorizadoAdmin2() {
    ModelAndView mv = new ModelAndView();
        if(login == true && permissao.equals("ADMIN")) {    
        return proceed();
    }
        mv.setViewName("redirect:/");
        return mv;
    }
    
  //verifica se o usuario esta autorizado a ver a tela de admin para lista de clientes caso não é mandando para realizar a autenticação no sistema realizando o login
    Object around () : isAutorizadoAdmin3() {
        ModelAndView mv = new ModelAndView();
        if(login == true && permissao.equals("ADMIN")) {    
        return proceed();
        }else {
        mv.setViewName("redirect:/");
        return mv;
        }
    }
    
  //verifica se o usuario esta autorizado a ver a tela de admin para cadastro de clientes caso não é mandando para realizar a autenticação no sistema realizando o login
    Object around () : isAutorizadoAdmin4() {
    ModelAndView mv = new ModelAndView();
        if(login == true && permissao.equals("ADMIN")) {    
        return proceed();
    }
        mv.setViewName("redirect:/");
        return mv;
    }
    
    Object around () : isAutorizadoAdmin5() {
        ModelAndView mv = new ModelAndView();
            if(login == true && permissao.equals("ADMIN")) {    
            return proceed();
        }
            mv.setViewName("redirect:/");
            return mv;
        }

}
