package Guest;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SignIn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	Long id;
	
	private Timestamp signingDate;
	
	@ManyToOne(optional=false)	
	@JoinColumn(name = "GUEST", nullable=false)
	private Guest guest;
	
	public SignIn() {
		// TODO Auto-generated constructor stub
	}
	
	public SignIn(Guest guest) {
		// TODO Auto-generated constructor stub
		setSigningDate(new Timestamp(System.currentTimeMillis()));
		setGuest(guest);
	}
	
	public void setSigningDate(Timestamp timestamp) {
		// TODO Auto-generated method stub
		signingDate = timestamp;
	}
	
	public Timestamp getSigningDate() {
		// TODO Auto-generated method stub
		return signingDate;
	}

	public Guest getGuest()
	{
	   return guest;
	}

	public void setGuest(Guest guest)
	{
	   this.guest = guest;
	}
	
	// String Representation:
    @SuppressWarnings("deprecation")
	@Override
    public String toString() {
        return "signed on " + signingDate.toLocaleString();
    }
    
}





