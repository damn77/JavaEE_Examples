package languageInjections;

import javax.enterprise.inject.Alternative;
import javax.enterprise.util.AnnotationLiteral;

import languageQualifiers.Spanish;

@Spanish
@Alternative
public class SpanishTranslateService_2 extends AnnotationLiteral<Spanish> implements TranslateService {

	private static final long serialVersionUID = 1L;

	@Override
    public String hello() {
        return "Hola Hej :)";
    }

}
