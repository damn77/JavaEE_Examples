package interceptors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;


@SessionScoped
@Named
public class BookShow implements Serializable{
    private static final long serialVersionUID = 1L;

    private String str = "";
    
    @Log
    public void method(){
    	setStr("Function \"method\" was invoked");
    }
    
    @Log
    public void method2(){
    	setStr("Function \"method2\" was invoked");
    }

    public void method3(){
    	setStr("Function \"method3\" was invoked");
    }

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
}
