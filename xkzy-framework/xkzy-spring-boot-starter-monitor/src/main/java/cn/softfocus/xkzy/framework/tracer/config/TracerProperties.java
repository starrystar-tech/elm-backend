package cn.softfocus.xkzy.framework.tracer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BizTracer配置类
 */
@ConfigurationProperties("xkzy.tracer")
@Data
public class TracerProperties {
}
