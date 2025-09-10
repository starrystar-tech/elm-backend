package cn.softfocus.xkzy.module.infra.framework.file.config;

import cn.softfocus.xkzy.module.infra.framework.file.core.client.FileClientFactory;
import cn.softfocus.xkzy.module.infra.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 *
 */
@Configuration(proxyBeanMethods = false)
public class XkzyFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
