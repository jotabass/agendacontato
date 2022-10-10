package br.com.cotiinformatica.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.ContatoRepository;

@Controller
public class ContatoConsultaController {

	// método executado ao abrir a página de consulta de contatos
	@RequestMapping(value = "/admin/consultar-contatos")
	public ModelAndView contatoConsulta(HttpServletRequest request) {

		// WEB-INF/views/admin/contato-consulta.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contato-consulta");

		try {

			// capturar o usuário que está autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");

			// consultar os contatos no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			List<Contato> lista = contatoRepository.findByUsuario(usuario.getIdUsuario());

			// enviando a lista para a página
			modelAndView.addObject("lista_contatos", lista);
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao consultar contatos");
			e.printStackTrace();
		}

		return modelAndView;
	}

	// método para capturar a requisição de exclusão do contato
	@RequestMapping(value = "/admin/excluir-contato")
	public ModelAndView excluirContato(HttpServletRequest request) {

		// WEB-INF/views/admin/contato-consulta.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contato-consulta");

		try {
			
			//capturando o id do contato na URL
			Integer idContato = Integer.parseInt(request.getParameter("id"));
			
			//capturando o usuário autenticado na sessão
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//consultar o contato no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			Contato contato = contatoRepository.findById(idContato, usuario.getIdUsuario());
			
			//verificando se o usuário foi encontrado
			if(contato != null) {
				
				//excluindo o contato
				contatoRepository.delete(contato);				
				modelAndView.addObject("mensagem_sucesso", "Contato " + contato.getNome() + ", excluído com sucesso.");
				
				//fazendo uma nova consulta no banco de dados
				List<Contato> lista = contatoRepository.findByUsuario(usuario.getIdUsuario());

				// enviando a lista para a página
				modelAndView.addObject("lista_contatos", lista);
			}			
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao excluir o contato");
			e.printStackTrace();
		}
		
		return modelAndView;
	}

}


