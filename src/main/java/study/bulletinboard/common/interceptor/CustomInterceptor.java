package study.bulletinboard.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import study.bulletinboard.config.annotation.CustomAnnotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.TreeUI;


/**
 * CustomInterceptor 설명
 *
 <pre>
 - @CustomAnnotation을 사용하기 위한 Interceptor 클래스
 * @author cyh68
 * @since 2023-03-07
 </pre>
 **/
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        CustomAnnotation customAnnotation = handlerMethod.getMethodAnnotation(CustomAnnotation.class);

        if (customAnnotation != null) {
            log.info("Custom Annotation in");
        }

        return true;
    }
}
