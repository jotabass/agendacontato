package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class RegisterController {

	//método que mapeia a rota para abrir a página
	@RequestMapping(value = "/crie-sua-conta")
	public ModelAndView register() {

		ModelAndView modelAndView = new ModelAndView("register");
		return modelAndView;
	}
	
	//método para capturar a rota POST do formulário (SUBMIT)
	@RequestMapping(value = "/realizar-cadastro", method = RequestMethod.POST)
	public ModelAndView realizarCadastro(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("register");
		
		try {
			
			Usuario usuario = new Usuario();
			
			//capturando os campos enviados pelo formulário
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			
			//verificando se já existe um usuário com o email informado cadastrado
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			if(usuarioRepository.findByEmail(usuario.getEmail()) != null) {
				
				//gerando mensagem de erro para exibir na página
				modelAndView.addObject("mensagem_erro", "O email informado já está cadastrado no sistema, tente outro.");
			}
			else {
				
				//cadastrando o usuário no banco de dados
				usuarioRepository.create(usuario);
				
				//gerando mensagem de sucesso para exibir na página
				modelAndView.addObject("mensagem_sucesso", "Parabéns! Sua conta de usuário foi cadastrada com sucesso.");
			}
		}
		catch(Exception e) {
			
			//gerando mensagem de erro
			modelAndView.addObject("mensagem_erro", "Falha ao cadastrar.");
			e.printStackTrace();
		}
		
		return modelAndView;
	}
}
