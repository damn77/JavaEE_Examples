/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package controler;

import ejb.StatelessClockEJB;
import ejb.StatefulGreeterEJB;
import ejb.SigletonClockEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("greeter")
@SessionScoped
public class Greeter implements Serializable {
	private static final long serialVersionUID = 1L;

    private StatefulGreeterEJB greeterStateFul;   
    @EJB private StatelessClockEJB clockStateless;
    @EJB private SigletonClockEJB clockSingleton;
    
    @EJB
    public void setGreeter(StatefulGreeterEJB g) {
    	greeterStateFul = g;
    }
    
    public String getDate() {
    	return clockStateless.getTime();
    }
    
    public String getSDate() {
    	return clockSingleton.getStartUp();
    }
    
    public String getPDate() {
    	return clockSingleton.getPassivation();
    }
    
    public void setName(String name) {
    	if (greeterStateFul!=null) greeterStateFul.setHello(name);
    	else {
    		setGreeter(new StatefulGreeterEJB());
    		greeterStateFul.setHello(name);
    	}
    }

    public String getHello() {
    	if (greeterStateFul!=null) return greeterStateFul.sayHello();
    	else return "Class greeterStateFul has not yet been initialized";
    }
}
