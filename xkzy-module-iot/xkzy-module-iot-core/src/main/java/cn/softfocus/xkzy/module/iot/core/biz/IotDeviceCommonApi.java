package cn.softfocus.xkzy.module.iot.core.biz;

import cn.softfocus.xkzy.framework.common.pojo.CommonResult;
import cn.softfocus.xkzy.module.iot.core.biz.dto.IotDeviceAuthReqDTO;
import cn.softfocus.xkzy.module.iot.core.biz.dto.IotDeviceGetReqDTO;
import cn.softfocus.xkzy.module.iot.core.biz.dto.IotDeviceRespDTO;

/**
 * IoT 设备通用 API
 *
 * 
 */
public interface IotDeviceCommonApi {

    /**
     * 设备认证
     *
     * @param authReqDTO 认证请求
     * @return 认证结果
     */
    CommonResult<Boolean> authDevice(IotDeviceAuthReqDTO authReqDTO);

    /**
     * 获取设备信息
     *
     * @param infoReqDTO 设备信息请求
     * @return 设备信息
     */
    CommonResult<IotDeviceRespDTO> getDevice(IotDeviceGetReqDTO infoReqDTO);

}
