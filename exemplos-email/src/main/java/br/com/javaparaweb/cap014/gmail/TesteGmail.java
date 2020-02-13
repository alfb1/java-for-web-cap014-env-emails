package br.com.javaparaweb.cap014.gmail;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import br.com.javaparaweb.cap014.javamail.Autenticacao;

public class TesteGmail 
{
   private static final String SERVIDOR_SMTP = "smtp.gmail.com";
   private static final String PORTA = "465";
   private static final String EMAIL = "envio.br@gmail.com";
   private static final String SENHA = "sua_senha.aqui";
   
   // é necessario liberar o acesso para app menos seguros na sua conta google
   // Google conta - security - Acesso a app menos seguro : deve ficar em on.
   public static void main( String args[]) throws AddressException, MessagingException
   {
	   Autenticacao autenticacao = new Autenticacao(EMAIL, SENHA);
	   
	   Session session = Session.getDefaultInstance(getPropriedades(), autenticacao);
	   session.setDebug(true);
	   
	   MimeMessage email = new MimeMessage(session);
	   
	   email.setRecipient(Message.RecipientType.TO, new InternetAddress("email_destino@gmail.com"));
	   email.setFrom( new InternetAddress(EMAIL));
	   email.setSubject("Teste de email usando Gmail");
	   email.setContent("Corpo da mensagem", "text/plain");
	   email.setSentDate(new Date());
	   
	   Transport envio = session.getTransport("smtp");
	   envio.connect(SERVIDOR_SMTP, EMAIL, SENHA);
	   
	   email.saveChanges();
	   
	   envio.sendMessage(email, email.getAllRecipients());
	   envio.close();
	   
	   System.out.println("E-mail enviado com sucesso.");
	   
   }
   
   public static Properties getPropriedades() 
   {
	   Properties config = new Properties();
	   
	   config.setProperty("mail.transport.protocol", "smtp") ;
	   config.setProperty("mail.smtp.starttls.enable", "true");
	   
	   config.setProperty("mail.smtp.host", SERVIDOR_SMTP);
	   config.setProperty("mail.smtp.auth", "true");
	   config.setProperty("mail.smtp.user", EMAIL);
	   config.setProperty("mail.debug", "true");
	   config.setProperty("mail.smtp.port", PORTA);
	   config.setProperty("mail.smtp.socketFactory.port", PORTA);
	   config.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
	   config.setProperty("mail.smtp.socketFactory.fallback", "false"); 
	   
	   return config;	   
   }
}
