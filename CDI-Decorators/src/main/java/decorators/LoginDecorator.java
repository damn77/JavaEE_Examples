package decorators;

import java.io.Serializable;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class LoginDecorator implements ApplicationInterface, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject @Delegate private ApplicationInterface app;
	
	@Inject MessageBoard mb;

    final String LOGIN = "admin";
    final String PASSWORD = "password";
    
    @Override
    public void method() {
    	// TODO Auto-generated method stub
    	if (LOGIN.equals(app.getLogin()) && PASSWORD.equals(app.getPassword())) {
    		mb.setMessage("Method was decorated, user validated and allowed to proceed");
    		app.method();
    	}
    	else mb.setMessage("Method was decorated but user was NOT validated. Method was stopped");
    }
}
