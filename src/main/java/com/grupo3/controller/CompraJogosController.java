import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo3.Model.Compra;
import com.grupo3.dao.CompraDao;

@Controller
public class CompraJogosController {
	
	@Autowired
	private CompraDao repositorioCompra;
	
	@GetMapping("/CompraJogos")
    public ModelAndView compraJogo(Compra compra){
        ModelAndView cj = new ModelAndView();
        cj.setViewName("/compraJogos");
        cj.addObject("compra",new Compra());
        return cj;
	}
	
    @PostMapping("newCompra")
    public ModelAndView jogoComprado(Compra compra) {
    	 ModelAndView cj = new ModelAndView();
    	 cj.setViewName("redirect:/");
    	 repositorioCompra.save(compra);
    	 return cj;
    }
}