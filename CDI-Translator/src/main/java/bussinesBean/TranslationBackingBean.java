package bussinesBean;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import languageInjections.EnglishTranslateService;
import languageInjections.SpanishTranslateService;
import languageInjections.TranslateService;
import languageQualifiers.English;
import languageQualifiers.Spanish;

@Named("translation")
@SessionScoped
public class TranslationBackingBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String choice;
	
    @Inject
    @Spanish
    private TranslateService spanishTranslateService;

    @Inject
    @English
    private TranslateService englishTranslateService;
    
    @Inject
    @Any
    private Instance<TranslateService> unknownTranslateService;

    public String getSpanishHello() {
        return spanishTranslateService.hello();
    }

    public String getEnglishHello() {
        return englishTranslateService.hello();
    }
    
    public String getUnknownHello() {
    	Annotation qualifier = null;
    	
    	if (choice.equals("ENG"))  qualifier =  new EnglishTranslateService();
    	else if (choice.equals("SPA")) qualifier =  new SpanishTranslateService();
    	
    	if (qualifier!=null) return unknownTranslateService.select(qualifier).get().hello();
    	else return "Incorect language identifier";
    }

    public void check() {
//    	if (choice.equals("ENG"))
//    		System.out.println(" choice is ENG -> "+ choice);
//    	else System.out.println(" choice is NOT ENG -> "+ choice);
//    		
     }
     

	public String getChoice() {
	    return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}
    
    @PostConstruct
    public void reset() {
     this.choice = "ENG";
    }
}
