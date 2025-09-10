package cn.softfocus.xkzy.module.member.service.config;

import cn.softfocus.xkzy.module.member.controller.admin.config.vo.MemberConfigSaveReqVO;
import cn.softfocus.xkzy.module.member.dal.dataobject.config.MemberConfigDO;

import jakarta.validation.Valid;

/**
 * 会员配置 Service 接口
 *
 * 
 */
public interface MemberConfigService {

    /**
     * 保存会员配置
     *
     * @param saveReqVO 更新信息
     */
    void saveConfig(@Valid MemberConfigSaveReqVO saveReqVO);

    /**
     * 获得会员配置
     *
     * @return 积分配置
     */
    MemberConfigDO getConfig();

}
