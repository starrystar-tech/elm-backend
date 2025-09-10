package cn.softfocus.xkzy.module.infra.framework.web.config;

import cn.softfocus.xkzy.framework.swagger.config.XkzySwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * infra 模块的 web 组件的 Configuration
 *
 *
 */
@Configuration(proxyBeanMethods = false)
public class InfraWebConfiguration {

    /**
     * infra 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi infraGroupedOpenApi() {
        return XkzySwaggerAutoConfiguration.buildGroupedOpenApi("infra");
    }

}
