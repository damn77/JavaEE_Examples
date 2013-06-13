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
package Guest;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class GuestDao {

	@PersistenceContext
	private EntityManager em;
	
	public String addGuest(String name) {
		String str = "";
		
		if (name != null && !name.equals("")) {
		Guest g = new Guest(name);
		SignIn s = new SignIn(g);
		g.addSignIn(s);
		em.persist(g);
//		em.persist(s);
		str = "New Guest: "+g+"<br />";
		}		
		return str + printAll();
	}

	private String printAll() {
		String str = "";
		
		@SuppressWarnings("unchecked")
		final List<Guest> list = em.createQuery("select g from Guest g").getResultList();
		str += "<br />All Guests:<br />";
		for (Guest G : list)
            str += G +"<br />";
		
		return str;
	}

	@SuppressWarnings("unchecked")
	public String addVisit(String name) {
		// TODO Auto-generated method stub
		final List<Guest> list = em.createQuery("select g from Guest g").getResultList();
		for (Guest g : list) {
			if (g.getName().equals(name)) return updateGuest(g);
		}
		return addGuest(name);
	}

	private String updateGuest(Guest g) {
		// TODO Auto-generated method stub
		SignIn s = new SignIn(g);
		em.persist(s);
		g.addSignIn(s);
		em.merge(g);
		String str = "Added SignIn to Guest: "+g.getName()+"<br />";
		return str + printAll();
	}
}
