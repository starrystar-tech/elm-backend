package cn.softfocus.xkzy.module.iot.api.device;

import cn.softfocus.xkzy.framework.common.enums.RpcConstants;
import cn.softfocus.xkzy.framework.common.pojo.CommonResult;
import cn.softfocus.xkzy.framework.common.util.object.BeanUtils;
import cn.softfocus.xkzy.module.iot.core.biz.IotDeviceCommonApi;
import cn.softfocus.xkzy.module.iot.core.biz.dto.IotDeviceAuthReqDTO;
import cn.softfocus.xkzy.module.iot.core.biz.dto.IotDeviceGetReqDTO;
import cn.softfocus.xkzy.module.iot.core.biz.dto.IotDeviceRespDTO;
import cn.softfocus.xkzy.module.iot.dal.dataobject.device.IotDeviceDO;
import cn.softfocus.xkzy.module.iot.dal.dataobject.product.IotProductDO;
import cn.softfocus.xkzy.module.iot.service.device.IotDeviceService;
import cn.softfocus.xkzy.module.iot.service.product.IotProductService;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static cn.softfocus.xkzy.framework.common.pojo.CommonResult.success;

/**
 * IoT 设备 API 实现类
 *
 * 
 */
@RestController
@Validated
@Primary // 保证优先匹配，因为 xkzy-iot-gateway 也有 IotDeviceCommonApi 的实现，并且也可能会被 biz 引入
public class IoTDeviceApiImpl implements IotDeviceCommonApi {

    @Resource
    private IotDeviceService deviceService;
    @Resource
    private IotProductService productService;

    @Override
    @PostMapping(RpcConstants.RPC_API_PREFIX + "/iot/device/auth")
    @PermitAll
    public CommonResult<Boolean> authDevice(@RequestBody IotDeviceAuthReqDTO authReqDTO) {
        return success(deviceService.authDevice(authReqDTO));
    }

    @Override
    @PostMapping(RpcConstants.RPC_API_PREFIX + "/iot/device/get") // 特殊：方便调用，暂时使用 POST，实际更推荐 GET
    @PermitAll
    public CommonResult<IotDeviceRespDTO> getDevice(@RequestBody IotDeviceGetReqDTO getReqDTO) {
        IotDeviceDO device = getReqDTO.getId() != null ? deviceService.getDeviceFromCache(getReqDTO.getId())
                : deviceService.getDeviceFromCache(getReqDTO.getProductKey(), getReqDTO.getDeviceName());
        return success(BeanUtils.toBean(device, IotDeviceRespDTO.class, deviceDTO -> {
            IotProductDO product = productService.getProductFromCache(deviceDTO.getProductId());
            if (product != null) {
                deviceDTO.setCodecType(product.getCodecType());
            }
        }));
    }

}