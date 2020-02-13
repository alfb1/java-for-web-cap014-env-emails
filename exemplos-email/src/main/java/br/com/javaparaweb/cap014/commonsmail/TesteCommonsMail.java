package br.com.javaparaweb.cap014.commonsmail;

import org.apache.commons.mail.*;

public class TesteCommonsMail {

	private static final String EMAIL ="andre@localhost";
	
	public static void main(String[] args) throws EmailException 
	{
	   	Email email = new SimpleEmail();
	   	
	   	email.setHostName("localhost");
	   	email.setSmtpPort(25);
	   	email.setAuthentication(EMAIL, "andre");
	   	email.setFrom(EMAIL, "Administrator");
	   	email.addTo("fulano@localhost");
	   	email.setSubject("Teste de email usando commonsMail" );
	   	email.setMsg( "Corpo da mensagem" );
	   	email.send();
	   	
	   	System.out.println("E-mail enviado com sucesso");
	}
}
