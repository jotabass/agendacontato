package br.com.cotiinformatica.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.javafaker.Faker;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.messages.EmailMessage;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class PasswordController {

	// Mapeando a rota para exibir a página de recuperação de senha do usuário
	@RequestMapping(value = "/esqueci-minha-senha")
	public ModelAndView password() {

		ModelAndView modelAndView = new ModelAndView("password");
		return modelAndView;
	}

	// Método para capturar a requisição enviada pelo formulário
	@RequestMapping(value = "/recuperar-senha", method = RequestMethod.POST)
	public ModelAndView recuperarSenha(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("password");

		try {

			// capturando o valor do email enviado pelo formulário
			String email = request.getParameter("email");

			// buscar no banco de dados o usuário cujo email é igual ao informado
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.findByEmail(email);

			// verificar se o usuário não foi encontrado
			if (usuario == null) {

				// gerando uma mensagem de erro
				modelAndView.addObject("mensagem_erro", "O email '" + email + "' não foi encontrado no sistema.");
			} else {

				// gerando uma nova senha
				Faker faker = new Faker(new Locale("pt-BR"));
				String novaSenha = faker.internet().password(8, 10);

				// criando uma mensagem e enviar por email
				String assunto = "Recuperação de senha de acesso - Agenda de Contatos";
				String texto = "Olá, " + usuario.getNome() + "\n\nUma nova senha de acesso foi gerada com sucesso."
						+ "\nUtilize a senha: " + novaSenha
						+ "\nAcesse o sistema com esta senha e depois de preferir altere para outra de sua preferência."
						+ "\n\nAtt" + "\nEquipe Agenda de Contatos";

				// enviando o email
				EmailMessage.send(email, assunto, texto);

				// atualizando a senha do usuário no banco de dados
				usuarioRepository.update(usuario.getIdUsuario(), novaSenha);

				// gerando uma mensagem de sucesso
				modelAndView.addObject("mensagem_sucesso",
						"Uma nova senha foi gerada e enviada para o seu email, por favor verifique.");
			}
		} catch (Exception e) {

			// gerando uma mensagem de erro
			modelAndView.addObject("mensagem_erro", "Falha ao recuperar senha.");
			e.printStackTrace();
		}

		return modelAndView;
	}
}
