package cn.softfocus.xkzy.module.trade.controller.app.delivery.vo.config;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "用户 App - 配送配置 Response VO")
@Data
public class AppDeliveryConfigRespVO {

    @Schema(description = "腾讯地图 KEY", requiredMode = Schema.RequiredMode.REQUIRED, example = "123456")
    private String tencentLbsKey;

    @Schema(description = "是否开启自提", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean pickUpEnable;

}
