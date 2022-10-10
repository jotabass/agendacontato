package br.com.cotiinformatica.messages;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


public class EmailMessage {

	// método estático para envio de emails
	// mailTo -> email do destinatário da mensagem
	// subject -> texto do assunto da mensagem
	// body -> texto do conteudo da mensagem
	public static void send(final String mailTo, final String subject, final String body) throws Exception {

		// definindo os parametros para fazer o envio da mensagem
		final String conta = "cotinaoresponda@outlook.com";
		final String senha = "@Admin123456";
		final String smtp = "smtp-mail.outlook.com";
		final Integer porta = 587;

		// configurar os parametros para envio da mensagem
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername(conta);
		mailSender.setPassword(senha);
		mailSender.setHost(smtp);
		mailSender.setPort(porta);

		// configurando alguns parametros para fazer o envio da mensagem
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.transport.protocol", "smtp");
		mailSender.setJavaMailProperties(properties);

		// criando o email e fazer o envio
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(mailTo); // conta do remetente
				message.setFrom(conta); // conta do destinatário
				message.setSubject(subject); // assunto da mensagem
				message.setText(body); // corpo da mensagem
			}
		};

		mailSender.send(preparator);
	}

}
