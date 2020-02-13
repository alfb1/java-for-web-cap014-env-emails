package br.com.javaparaweb.cap014.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class TesteJavaEmailAnexo 
{
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
		
		MimeMultipart emailMessage = new MimeMultipart();
		
		MimeBodyPart body = new MimeBodyPart();
		body.setContent("E-mail com anexo", "text/html");
		emailMessage.addBodyPart(body);
		
		MimeBodyPart attachment = new MimeBodyPart();
		// you must create the file "texto.txt" in folder "c:\temp" for windows system
		attachment.setDataHandler(new DataHandler( new FileDataSource("/temp/texto.txt")));
		attachment.setFileName("texto.txt");
		emailMessage.addBodyPart(attachment);
		
		email.setContent(emailMessage);
		
		Transport.send(email);
		
		System.out.println("E-mail enviado com sucesso.");
		
   }
}
