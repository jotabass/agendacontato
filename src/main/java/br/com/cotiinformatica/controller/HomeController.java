package br.com.cotiinformatica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.QuantidadeContatoDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.ContatoRepository;

@Controller
public class HomeController {

	@RequestMapping(value = "/admin/pagina-inicial")
	public ModelAndView home(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("admin/home");

		try {

			// capturar o usuário autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");

			// consultar a quantidade de contatos cadastrados do usuário
			ContatoRepository contatoRepository = new ContatoRepository();
			List<QuantidadeContatoDto> lista = contatoRepository.findQuantidade(usuario.getIdUsuario());

			// enviando o DTO para a página
			modelAndView.addObject("consulta", lista);
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao consultar.");
			e.printStackTrace();
		}

		return modelAndView;
	}
}
