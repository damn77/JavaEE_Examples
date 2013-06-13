package languageQualifiers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Qualifier
@Target({TYPE, METHOD, FIELD, PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Spanish {
}
