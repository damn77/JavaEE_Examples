package Guest;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name implements Serializable {


	private static final long serialVersionUID = -3529680576068022703L;
	
	private String fname;
	private String lname;
	
	public Name() {}
	
	public Name(String fn, String ln) {
		fname = fn;
		lname = ln;
	}
	
	@Column(name="FIRST_NAME", nullable=true, length=128)
	public String getFname() {
		return fname;
	}
	
	@Column(name="LAST_NAME", nullable=true, length=128)
	public String getLname() {
		return lname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fname + " " + lname;
	}
}
