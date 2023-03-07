package study.bulletinboard.config.interceptorConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.bulletinboard.common.interceptor.CustomInterceptor;
import study.bulletinboard.common.interceptor.LogInterceptor;

/**
 * InterceptorConfig 설명
 *
 <pre>
 - Interceptor 사용을 위한 Configuration
 * @author cyh68
 * @since 2023-03-07
 </pre>
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new CustomInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");
    }
}
