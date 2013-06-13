package decorators;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named (value = "app")
@SessionScoped
public class ExampleApplication implements ApplicationInterface, Serializable{
	
	private static final long serialVersionUID = 1L;

	private String login = "admin";
	private String password = "password";
	
	@Inject MessageBoard mb;
	
	public void logIn(String login, String password) {
		this.login =login;
		this.password = password;
	}
	
	public void method() {
		mb.appendMessage("Method finnished!");
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
