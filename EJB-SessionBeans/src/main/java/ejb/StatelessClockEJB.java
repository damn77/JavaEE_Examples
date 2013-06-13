package ejb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.Stateless;


@Stateless
public class StatelessClockEJB {

	public String getTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		Date date = new Date();
		return "Stateless: Right now it is "+ dateFormat.format(date);
	}	
}
