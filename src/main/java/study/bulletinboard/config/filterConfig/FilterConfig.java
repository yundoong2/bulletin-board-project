package study.bulletinboard.config.filterConfig;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.bulletinboard.common.filter.LogFilter;

import javax.servlet.Filter;

/**
 * FilterConfig 설명
 *
 <pre>
 - Filter 사용을 위한 Configuration
 * @author cyh68
 * @since 2023-03-07
 </pre>
 **/
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
