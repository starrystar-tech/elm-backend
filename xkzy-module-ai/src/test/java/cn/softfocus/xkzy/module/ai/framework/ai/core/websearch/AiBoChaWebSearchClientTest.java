package cn.softfocus.xkzy.module.ai.framework.ai.core.websearch;

import cn.softfocus.xkzy.framework.common.util.json.JsonUtils;
import cn.softfocus.xkzy.module.ai.framework.ai.core.webserch.AiWebSearchRequest;
import cn.softfocus.xkzy.module.ai.framework.ai.core.webserch.AiWebSearchResponse;
import cn.softfocus.xkzy.module.ai.framework.ai.core.webserch.bocha.AiBoChaWebSearchClient;
import org.junit.jupiter.api.Test;

/**
 * {@link AiBoChaWebSearchClient} 集成测试类
 *
 * 
 */
public class AiBoChaWebSearchClientTest {

    private final AiBoChaWebSearchClient webSearchClient = new AiBoChaWebSearchClient(
            "sk-40500e52840f4d24b956d0b1d80d9abe");

    @Test
    public void testSearch() {
        AiWebSearchRequest request = new AiWebSearchRequest()
                .setQuery("阿里巴巴")
                .setCount(3);
        AiWebSearchResponse response = webSearchClient.search(request);
        System.out.println(JsonUtils.toJsonPrettyString(response));
    }

}