package cn.softfocus.xkzy.framework.signature.config;

import cn.softfocus.xkzy.framework.redis.config.XkzyRedisAutoConfiguration;
import cn.softfocus.xkzy.framework.signature.core.aop.ApiSignatureAspect;
import cn.softfocus.xkzy.framework.signature.core.redis.ApiSignatureRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * HTTP API 签名的自动配置类
 */
@AutoConfiguration(after = XkzyRedisAutoConfiguration.class)
public class XkzyApiSignatureAutoConfiguration {

    @Bean
    public ApiSignatureAspect signatureAspect(ApiSignatureRedisDAO signatureRedisDAO) {
        return new ApiSignatureAspect(signatureRedisDAO);
    }

    @Bean
    public ApiSignatureRedisDAO signatureRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new ApiSignatureRedisDAO(stringRedisTemplate);
    }

}
