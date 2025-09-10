package cn.softfocus.xkzy.module.infra.api.logger;

import cn.softfocus.xkzy.framework.common.biz.infra.logger.ApiErrorLogCommonApi;
import cn.softfocus.xkzy.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import cn.softfocus.xkzy.module.infra.service.logger.ApiErrorLogService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * API 访问日志的 API 接口
 *
 *
 */
@Service
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogCommonApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @Override
    public void createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
    }

}
