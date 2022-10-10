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
public class ContatoEdicaoController {

	@RequestMapping(value = "/admin/editar-contato")
	public ModelAndView contatoEdicao(HttpServletRequest request) {

		// WEB-INF/views/admin/contato-edicao.jsp
		ModelAndView modelAndView = new ModelAndView("admin/contato-edicao");

		try {

			// capturar o ID do contato enviado pela URL
			Integer idContato = Integer.parseInt(request.getParameter("id"));
			
			//capturar o usuário autenticado na sessão!!
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");

			// consultando o contato no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			Contato contato = contatoRepository.findById(idContato, usuario.getIdUsuario());
			
			//verificar se o contato foi encontrado
			if(contato != null) {
				
				//enviando os dados do contato para a página
				modelAndView.addObject("idcontato", contato.getIdContato());
				modelAndView.addObject("nome", contato.getNome());
				modelAndView.addObject("telefone", contato.getTelefone());
				modelAndView.addObject("email", contato.getEmail());
				modelAndView.addObject("datanasc", new SimpleDateFormat("dd/MM/yyyy").format(contato.getDataNasc()));				
			}
			else {
				modelAndView.setViewName("redirect:/admin/consultar-contatos");
			}

		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao obter contato.");
			e.printStackTrace();
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/atualizar-contato", method = RequestMethod.POST)
	public ModelAndView atualizarContato(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("admin/contato-edicao");
		
		try {
			
			Contato contato = new Contato();
			
			contato.setIdContato(Integer.parseInt(request.getParameter("idcontato")));
			contato.setNome(request.getParameter("nome"));
			contato.setTelefone(request.getParameter("telefone"));
			contato.setEmail(request.getParameter("email"));
			contato.setDataNasc(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("datanasc")));
			
			//atualizando o contato no banco de dados
			ContatoRepository contatoRepository = new ContatoRepository();
			contatoRepository.update(contato);
			
			modelAndView.addObject("mensagem_sucesso", "Contato atualizado com sucesso.");
			
			//devolvendo os dados atualizados do contato para a página JSP
			modelAndView.addObject("idcontato", contato.getIdContato());
			modelAndView.addObject("nome", contato.getNome());
			modelAndView.addObject("telefone", contato.getTelefone());
			modelAndView.addObject("email", contato.getEmail());
			modelAndView.addObject("datanasc", new SimpleDateFormat("dd/MM/yyyy").format(contato.getDataNasc()));	
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao obter contato.");
			e.printStackTrace();
		}
		
		return modelAndView;
	}

}
