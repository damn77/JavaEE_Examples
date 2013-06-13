package ejb;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.PrePassivate;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class SigletonClockEJB {

	private String appStarted;
	private String appPassivated = "";

	@PostConstruct
	private void startUp() {
		appStarted = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
	}
	
	@PrePassivate
	private void prePassivate() {
		appPassivated = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());
	}
	
	public String getStartUp() {
		return "Singleton: Application started at "+appStarted;
	}
	
	public String getPassivation() {
		return "Singleton: Passivated  at "+appPassivated;
	}
}
