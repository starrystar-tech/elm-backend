package cn.softfocus.xkzy.framework.tracer.config;

import cn.softfocus.xkzy.framework.common.enums.WebFilterOrderEnum;
import cn.softfocus.xkzy.framework.tracer.core.filter.TraceFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Tracer 配置类
 */
@AutoConfiguration
@ConditionalOnClass(name = {
        "org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer", // 来自 apm-toolkit-opentracing.jar
//        "io.opentracing.Tracer", // 来自 opentracing-api.jar
        "jakarta.servlet.Filter"
})
@EnableConfigurationProperties(TracerProperties.class)
@ConditionalOnProperty(prefix = "xkzy.tracer", value = "enable", matchIfMissing = true)
public class XkzyTracerAutoConfiguration {

//    @Bean
//    public BizTraceAspect bizTracingAop() {
//        return new BizTraceAspect(tracer());
//    }
//
//    @Bean
//    public Tracer tracer() {
//        // 创建 SkywalkingTracer 对象
//        SkywalkingTracer tracer = new SkywalkingTracer();
//        // 设置为 GlobalTracer 的追踪器
//        GlobalTracer.registerIfAbsent(tracer);
//        return tracer;
//    }

    /**
     * 创建 TraceFilter 过滤器，响应 header 设置 traceId
     */
    @Bean
    public FilterRegistrationBean<TraceFilter> traceFilter() {
        FilterRegistrationBean<TraceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceFilter());
        registrationBean.setOrder(WebFilterOrderEnum.TRACE_FILTER);
        return registrationBean;
    }

}
