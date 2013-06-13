package Guest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class GuestDao {

	@PersistenceContext
	private EntityManager em;
	
	public String addGuest(String name) {
		String str = "";
		if (name != null && !name.equals("")) {
		Guest g = new Guest(name);
		em.persist(g);
		str = "New Guest: "+g+"<br />";
		}	
		return str + createInformation();
	}
	
	@SuppressWarnings("unchecked")
	private String createInformation(){
		final List<Guest> list = em.createQuery("select g from Guest g").getResultList();
		String str =	"<br />All Guests:<br />";
		for (Guest G : list)
            str += G +"<br />";
		return str;
	}
}
