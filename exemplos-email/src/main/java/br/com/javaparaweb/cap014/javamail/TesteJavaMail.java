package br.com.javaparaweb.cap014.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TesteJavaMail {

	private static final String EMAIL = "andre@localhost";
	
	public static void main(String[] args) throws AddressException, MessagingException
	{
		Properties config = new Properties();
		
		config.setProperty( "mail.smtp.host", "localhost" );
		config.setProperty( "mail.smtp.port", "25");
		config.setProperty( "mail.smtp.auth", "true");
		
		Session session = Session.getInstance(config, new Autenticacao(EMAIL, "andre"));
		MimeMessage email = new MimeMessage(session);
		
		email.setFrom( new InternetAddress(EMAIL) );
		email.setRecipient(Message.RecipientType.TO	, new InternetAddress("andre@localhost"));
		email.setSubject("Teste de e-mail usando JavaEmail");
		email.setSentDate(new Date());
		email.setText("Corpo da mensagem");
		
		Transport.send(email);
		
		System.out.println("E-mail enviado com sucesso");
		
	}
}
