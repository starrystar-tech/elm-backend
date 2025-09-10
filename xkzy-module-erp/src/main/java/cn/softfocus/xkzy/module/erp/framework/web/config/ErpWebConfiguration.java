package cn.softfocus.xkzy.module.erp.framework.web.config;

import cn.softfocus.xkzy.framework.swagger.config.XkzySwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * erp 模块的 web 组件的 Configuration
 *
 *
 */
@Configuration(proxyBeanMethods = false)
public class ErpWebConfiguration {

    /**
     * erp 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi erpGroupedOpenApi() {
        return XkzySwaggerAutoConfiguration.buildGroupedOpenApi("erp");
    }

}
