package br.com.cotiinformatica.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class MailSenderComponent {

	@Value("${spring.mail.username}")
	private String userName;

	@Autowired
	private JavaMailSender javaMailSender;

	// Método para realizar o envio do emails
	public void sendMessage(String to, String subjetc, String body) throws Exception {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		helper.setFrom(userName);
		helper.setTo(to);
		helper.setSubject(subjetc);
		helper.setText(body);

		javaMailSender.send(mimeMessage);
	}

}
