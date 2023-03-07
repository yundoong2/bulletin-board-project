package study.bulletinboard.common.aop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * TraceAspect 설명
 *
 <pre>
 - 로그를 기록하기 위한 Trace 용 Aspect 클래스
 * @author cyh68
 * @since 2023-03-07
 </pre>
 **/
@Slf4j
@Aspect
@Component
public class TraceAspect {

    /**
     * doAround 설명
     *
     <pre>
     - @Around 실습 메소드
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return object {@link Object}
     * @throw e {@link Exception}
     * @author cyh68
     * @since 2023-03-07
     </pre>
     **/
    @Around("@annotation(study.bulletinboard.config.annotation.TraceAnnotation)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //@Before
            log.info("[Before doAround] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            //@AfterReturning
            log.info("[AfterReturning doAround] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            //@AfterThrowing
            log.info("[AfterThrowing doAround] {}", joinPoint.getSignature());
            throw e;
        } finally {
            //@After
            log.info("[After doAround] {}", joinPoint.getSignature());
        }
    }

    /**
     * doBefore 설명
     *
     <pre>
     - @Before 실습 메소드
     * @param joinPoint {@link JoinPoint}
     * @author cyh68
     * @since 2023-03-07
     </pre>
     **/
    @Before("@annotation(study.bulletinboard.config.annotation.TraceAnnotation)")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("doBefore [trace] {}, args={}", joinPoint.getSignature(), args);

        if(args.length > 0) {
            for(int i = 0; i < args.length; i++) {
                //아래와 같이 request에서 넘어온 파라미터를 받아올 수 있음 (피드백 내용)
                Object param = new ObjectMapper().convertValue(args[i], new TypeReference<Object>() {});
                log.info("param" + (i+1) + " : " + param);
            }
        }
    }

    //포인트컷을 아래처럼 서비스에도 지정할 수 있음 (피드백 내용)
    @Before(value = "execution(* study.bulletinboard.services.BoardService*.addBoardPost(..))")
    public void doBefore2(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("doBefore [service trace] {}, args={}", joinPoint.getSignature(), args);

        if(args.length > 0) {
            for(int i = 0; i < args.length; i++) {
                Object param = new ObjectMapper().convertValue(args[i], new TypeReference<Object>() {});
                log.info("param" + (i+1) + " : " + param);
            }
        }
    }

    /**
     * doAfterReturning 설명
     *
     <pre>
     - @AfterReturning 실습 메소드
     * @param joinPoint {@link JoinPoint}
     * @param result {@link Object}
     * @author cyh68
     * @since 2023-03-07
     </pre>
     **/
    @AfterReturning(value = "@annotation(study.bulletinboard.config.annotation.TraceAnnotation)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("doAfterReturning [trace] {}, return={}", joinPoint.getSignature(), result);
    }

    /**
     * doAfterThrowing 설명
     *
     <pre>
     - @AfterThrowing 실습 메소드
     * @param joinPoint {@link JoinPoint}
     * @param ex {@link Exception}
     * @author cyh68
     * @since 2023-03-07
     </pre>
     **/
    @AfterThrowing(value = "@annotation(study.bulletinboard.config.annotation.TraceAnnotation)", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("doAfterThrowing [trace] {}, ex={}", joinPoint.getSignature() , ex);
    }

    /**
     * doAfter 설명
     *
     <pre>
     - @After 실습 메소드
     * @param joinPoint {@link JoinPoint}
     * @author cyh68
     * @since 2023-03-07
     </pre>
     **/
    @After(value = "@annotation(study.bulletinboard.config.annotation.TraceAnnotation)")
    public void doAfter(JoinPoint joinPoint) {
        log.info("doAfter [trace] {}, after={}", joinPoint.getSignature());
    }
}
