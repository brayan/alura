package br.com.casadocodigo.loja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.dao.ProdutoDao;
import br.com.casadocodigo.loja.dao.UsuarioDao;
import br.com.casadocodigo.loja.model.Produto;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping("/")
	@Cacheable(value="produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}
	
//	@Transactional
//	@ResponseBody
//	@RequestMapping("/url-magica-maluca-asdkfjhasjkdfh1234243jkasdfhjkasdhfjk")
//	public String urlMagicaMalucaGambiarra() {
//		Usuario usuario = new Usuario();
//		
//		
//		return "Url Mágica executada";
//	}
	
}
