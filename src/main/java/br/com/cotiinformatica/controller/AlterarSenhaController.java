package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class AlterarSenhaController {

	// método para mapear a rota de navegação da página de alteração de senha
	@RequestMapping(value = "/admin/alterar-senha")
	public ModelAndView alterarSenha() {

		// WEB-INF/views/admin/alterar-senha.jsp
		ModelAndView modelAndView = new ModelAndView("admin/alterar-senha");
		return modelAndView;
	}

	// método para capturar o submit post do formulário
	@RequestMapping(value = "/admin/atualizar-senha", method = RequestMethod.POST)
	public ModelAndView atualizarSenha(HttpServletRequest request) {

		// WEB-INF/views/admin/alterar-senha.jsp
		ModelAndView modelAndView = new ModelAndView("admin/alterar-senha");
		
		try {
			
			//capturar a senha enviada pelo formulário
			String novaSenha = request.getParameter("novasenha");
			
			//capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//atualizando a senha do usuário no banco de dados
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			usuarioRepository.update(usuario.getIdUsuario(), novaSenha);
			
			modelAndView.addObject("mensagem_sucesso", "Senha de acesso atualizada com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao atualizar senha");
			e.printStackTrace();
		}
		
		return modelAndView;
	}

}
