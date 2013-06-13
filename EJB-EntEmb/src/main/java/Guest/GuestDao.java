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
	
	public String addGuest(String fname, String lname) {
		String str = "";
		
		if (fname != null && !fname.equals("") && lname != null && !lname.equals("")) {
		Guest g = new Guest(fname,lname);
		em.persist(g);
		
		str = "New Guest: "+g+"<br />";
		}
		@SuppressWarnings("unchecked")
		final List<Guest> list = em.createQuery("select g from Guest g").getResultList();
		if (list == null) str += "List is Empty";
		else str += "<br />All Guests:<br />";
		for (Guest G : list)
            str += G +"<br />";
		return str;
	}
}
