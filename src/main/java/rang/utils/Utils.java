package rang.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utils {
	
	public static void info(String text) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void erro(String text) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, text, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void war(String text) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, text, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
