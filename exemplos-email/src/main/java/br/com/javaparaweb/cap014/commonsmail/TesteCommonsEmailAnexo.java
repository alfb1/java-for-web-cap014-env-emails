package br.com.javaparaweb.cap014.commonsmail;

import org.apache.commons.mail.*;

public class TesteCommonsEmailAnexo 
{
   private static final String EMAIL="andre@localhost";
   
   public static void main(String args[]) throws EmailException
   {
	   MultiPartEmail email = new MultiPartEmail();
	   
	   email.setHostName("localhost");
	   email.setSmtpPort(25);
	   email.setAuthentication(EMAIL, "andre");
	   email.setFrom(EMAIL, "Administrator");
	   email.addTo("fulano@localhost");
	   email.setSubject("Teste de email usando commonsMail - com anexo." );
	   email.setMsg( "Body message" );
	   
	   EmailAttachment attachment = new EmailAttachment();
	   attachment.setPath("/temp/texto.txt");
	   attachment.setDisposition(EmailAttachment.ATTACHMENT);
	   
	   email.attach(attachment);
	   email.send();
	   	
	   System.out.println("E-mail enviado com sucesso");
   }
}
