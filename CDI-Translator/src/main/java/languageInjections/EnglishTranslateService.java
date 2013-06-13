package languageInjections;

import javax.enterprise.util.AnnotationLiteral;

import languageQualifiers.English;

@English
public class EnglishTranslateService extends AnnotationLiteral<English> implements TranslateService {

	private static final long serialVersionUID = 1L;

	@Override
    public String hello() {
        return "Hello";
    }

}
