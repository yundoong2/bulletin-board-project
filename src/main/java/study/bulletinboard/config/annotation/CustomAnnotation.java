package study.bulletinboard.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * CustomAnnotation 설명
 *
 <pre>
 - CustomInterceptor에 사용하기 위한 Custom Annotation
 * @author cyh68
 * @since 2023-03-07
 </pre>
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CustomAnnotation {

}
