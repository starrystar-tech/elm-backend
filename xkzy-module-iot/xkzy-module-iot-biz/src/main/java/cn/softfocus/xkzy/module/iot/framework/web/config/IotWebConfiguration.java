package cn.softfocus.xkzy.module.iot.framework.web.config;

import cn.softfocus.xkzy.framework.swagger.config.XkzySwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * iot 模块的 web 组件的 Configuration
 *
 * 
 */
@Configuration(proxyBeanMethods = false)
public class IotWebConfiguration {

    /**
     * iot 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi iotGroupedOpenApi() {
        return XkzySwaggerAutoConfiguration.buildGroupedOpenApi("iot");
    }

}
