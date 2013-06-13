package interceptors;

import javax.ejb.Singleton;
import javax.inject.Named;

@Singleton
@Named
public class MessageBoard{
	
	String message = "";
	
	public String getMessage() {
		String str = message;
		setMessage("No method was intercepted");
		return str;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
