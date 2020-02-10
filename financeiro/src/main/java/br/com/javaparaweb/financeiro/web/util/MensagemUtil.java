package br.com.javaparaweb.financeiro.web.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;


public class MensagemUtil {

	public static String getMensagem(String propriedade) 
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		
		return resourceBundle.getString(propriedade);
	}
	
	
	public static String getMensagem(String propriedade, Object...parametros) 
	{
		String mensagem = getMensagem(propriedade);
		MessageFormat messageFormat = new MessageFormat(mensagem);
		
		return messageFormat.format(parametros);
	}
	
}
