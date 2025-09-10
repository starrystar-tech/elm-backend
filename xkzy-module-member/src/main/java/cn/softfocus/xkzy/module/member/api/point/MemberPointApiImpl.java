package cn.softfocus.xkzy.module.member.api.point;

import cn.hutool.core.lang.Assert;
import cn.softfocus.xkzy.module.member.enums.point.MemberPointBizTypeEnum;
import cn.softfocus.xkzy.module.member.service.point.MemberPointRecordService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.softfocus.xkzy.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.softfocus.xkzy.module.member.enums.ErrorCodeConstants.POINT_RECORD_BIZ_NOT_SUPPORT;

/**
 * 用户积分的 API 实现类
 *
 * 三
 */
@Slf4j
@Service
@Validated
public class MemberPointApiImpl implements MemberPointApi {

    @Resource
    private MemberPointRecordService memberPointRecordService;

    @Override
    public void addPoint(Long userId, Integer point, Integer bizType, String bizId) {
        Assert.isTrue(point > 0);
        MemberPointBizTypeEnum bizTypeEnum = MemberPointBizTypeEnum.getByType(bizType);
        if (bizTypeEnum == null) {
            log.error("[addPoint][userId({}) point({}) bizType({}) bizId({}) {}]", userId, point, bizType, bizId,
                    POINT_RECORD_BIZ_NOT_SUPPORT);
            return;
        }
        memberPointRecordService.createPointRecord(userId, point, bizTypeEnum, bizId);
    }

    @Override
    public void reducePoint(Long userId, Integer point, Integer bizType, String bizId) {
        Assert.isTrue(point > 0);
        MemberPointBizTypeEnum bizTypeEnum = MemberPointBizTypeEnum.getByType(bizType);
        if (bizTypeEnum == null) {
            throw exception(POINT_RECORD_BIZ_NOT_SUPPORT);
        }
        memberPointRecordService.createPointRecord(userId, -point, bizTypeEnum, bizId);
    }

}
