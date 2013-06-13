package languageInjections;

import javax.enterprise.util.AnnotationLiteral;

import languageQualifiers.Spanish;

@Spanish
public class SpanishTranslateService extends AnnotationLiteral<Spanish> implements TranslateService {

	private static final long serialVersionUID = 1L;

	@Override
    public String hello() {
        return "Hola";
    }

}
