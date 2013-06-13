package Guest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Guests")
public class Guest implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id @Column(unique = true)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="guest")
    private Collection<SignIn> signIns;

    public Guest() {}

    public Guest(String name) {
        this.name = name;
    }
    
    public void addSignIn(SignIn s) {
       if (signIns == null) signIns = new ArrayList<SignIn>();
       signIns.add(s);
    }
    
    public Collection<SignIn> getLineItems() {
       return signIns;
    }
    
    public void setLineItems(Collection<SignIn> signIns) {
       this.signIns = signIns;
    }
    
    public String getName() {
		return name;
	}
    
    // String Representation:
    @Override
    public String toString() {
        String str = name +" @<br />";
        for (SignIn s : signIns) {
			str += "	"+ s + "<br />";
		}
    	return str;
    }
    
}