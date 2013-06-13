package ejb;

import interfaces.LocalGreeter;
import interfaces.RemoteGreeter;

import javax.ejb.Stateful;


@Stateful
public class StatefulGreeterEJB implements LocalGreeter, RemoteGreeter{

	String name = "";

    public String sayHello() {
        if (!name.equals(""))
    		return "Hello " + name+" !";
        else return "";
    }
    
    public void setHello(String n) {
    	name = n;
    }
}