package br.com.cotiinformatica.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.ContatoRepository;

@Controller
public class ContatoCadastroController {

	@RequestMapping(value = "/admin/cadastrar-contatos")
	public ModelAndView contatoCadastro() {

		// WEB-INF/views/admin/contato-cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contato-cadastro");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/cadastrar-contato", method = RequestMethod.POST)
	public ModelAndView cadastrarContato(HttpServletRequest request) {

		// WEB-INF/views/admin/contato-cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contato-cadastro");
		
		try {
			
			//capturar os campos enviados pelo formulário
			Contato contato = new Contato();
			
			contato.setNome(request.getParameter("nome"));
			contato.setTelefone(request.getParameter("telefone"));
			contato.setEmail(request.getParameter("email"));
			contato.setDataNasc(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("datanasc")));			
			contato.setUsuario((Usuario) request.getSession().getAttribute("usuario_auth"));
			
			//gravar no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			contatoRepository.create(contato);
			
			modelAndView.addObject("mensagem_sucesso", "Parabéns! O Contato " + contato.getNome() + " foi cadastrado com sucesso!");
		}
		catch(Exception e) {
			//mensagem de erro
			modelAndView.addObject("mensagem_erro", e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}

}


