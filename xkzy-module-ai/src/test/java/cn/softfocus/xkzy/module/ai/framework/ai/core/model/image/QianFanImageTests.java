package cn.softfocus.xkzy.module.ai.framework.ai.core.model.image;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springaicommunity.qianfan.QianFanImageModel;
import org.springaicommunity.qianfan.QianFanImageOptions;
import org.springaicommunity.qianfan.api.QianFanImageApi;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;

import static cn.softfocus.xkzy.module.ai.framework.ai.core.model.image.StabilityAiImageModelTests.viewImage;


/**
 * {@link QianFanImageModel} 集成测试类
 */
public class QianFanImageTests {

    private final QianFanImageModel imageModel = new QianFanImageModel(
            new QianFanImageApi("qS8k8dYr2nXunagK4SSU8Xjj", "pHGbx51ql2f0hOyabQvSZezahVC3hh3e")); // 密钥

    @Test
    @Disabled
    public void testCall() {
        // 准备参数
        // 只支持 1024x1024、768x768、768x1024、1024x768、576x1024、1024x576
        QianFanImageOptions imageOptions = QianFanImageOptions.builder()
                .model(QianFanImageApi.ImageModel.Stable_Diffusion_XL.getValue())
                .width(1024).height(1024)
                .N(1)
                .build();
        ImagePrompt prompt = new ImagePrompt("good", imageOptions);

        // 方法调用
        ImageResponse response = imageModel.call(prompt);
        // 打印结果
        String b64Json = response.getResult().getOutput().getB64Json();
        System.out.println(response);
        viewImage(b64Json);
    }

}
