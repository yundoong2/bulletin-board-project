package study.bulletinboard.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TraceAnnotation 설명
 *
 <pre>
 - AOP 실습을 위한 Trace 용 Custom Annotation
 * @author cyh68
 * @since 2023-03-07
 </pre>
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TraceAnnotation {

}
