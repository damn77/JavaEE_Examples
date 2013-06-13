package Guest;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Guest implements Serializable {
    private static final long serialVersionUID = 1L;

    // Persistent Fields:
    @Id @GeneratedValue
    Long id;
    private String name;
    private Timestamp signingDate;

    //Constructors:
    public Guest() {
    }

    public Guest(String name) {
        this.name = name;
        this.signingDate = new Timestamp(System.currentTimeMillis());
    }

    // String Representation:
    @SuppressWarnings("deprecation")
	@Override
    public String toString() {
        return name + " (signed on " + signingDate.toLocaleString()+ ")";
    }
}