package study.bulletinboard.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.TreeUI;
import java.util.UUID;

/**
 * LogInterceptor 설명
 *
 * <pre>
 * - REQUEST에 로그 남기기 위한 Interceptor 클래스
 * @author cyh68
 * @since 2023-03-03
 </pre>
 **/
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        if(handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler; //호출할 컨트롤러 메소드의 모든 정보가 포함되어 있음
        }

        log.info("preHandle REQUEST [{}][{}][{}]", uuid, requestURI, handler);

        //true, false 말고 아래와 같이 사용하는 방식도 있음 (피드백 내용)
        return HandlerInterceptor.super.preHandle(request, response, handler);
//        return true; //false 인 경우 다음으로 진행되지 않고 종료됨
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //아래처럼 쓰는 방식도 있음(피드백 내용)
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = request.getAttribute(LOG_ID).toString();

        log.info("afterCompletion RESPONSE [{}][{}]", uuid, requestURI);
        if (ex != null) {
            log.error("afterCompletion error!!", ex);

        }
    }
}
