package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class LoginController {

	// método para mapear a rota de navegação da página de login,
	// definindo-a como página inicial do sistema.
	@RequestMapping(value = "/") // mapeando a rota raiz do projeto
	public ModelAndView login() {

		// definindo o nome da página que será exibida pelo sistema
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	// método para capturar a requisição de autenticação de usuário
	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("login");

		try {

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);

			if (usuario == null) {

				modelAndView.addObject("mensagem_erro", "Acesso negado.");
			} else {

				// salvar os dados do usuário em sessão
				request.getSession().setAttribute("usuario_auth", usuario);

				// redirecionar o usuário para a primeira página da área Admin do projeto
				modelAndView.setViewName("redirect:admin/pagina-inicial");
			}
		} catch (Exception e) {

			modelAndView.addObject("mensagem_erro", "Falha ao autenticar.");
			e.printStackTrace();
		}

		return modelAndView;
	}
	
	//método para mapear a rota de logout do usuário
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
	
		//destruir a sessão do usuário
		request.getSession().removeAttribute("usuario_auth");
		//apagar todo o conteudo gravado em sessão
		request.getSession().invalidate();
		
		//redirecionar o usuário de volta para a rota do sistema (login)
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		return modelAndView;
	}
}

