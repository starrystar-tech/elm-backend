package cn.softfocus.xkzy.module.trade.dal.mysql.delivery;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.softfocus.xkzy.module.trade.controller.admin.delivery.vo.express.DeliveryExpressExportReqVO;
import cn.softfocus.xkzy.module.trade.controller.admin.delivery.vo.express.DeliveryExpressPageReqVO;
import cn.softfocus.xkzy.module.trade.dal.dataobject.delivery.DeliveryExpressDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryExpressMapper extends BaseMapperX<DeliveryExpressDO> {

    default PageResult<DeliveryExpressDO> selectPage(DeliveryExpressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeliveryExpressDO>()
                .likeIfPresent(DeliveryExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(DeliveryExpressDO::getName, reqVO.getName())
                .eqIfPresent(DeliveryExpressDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DeliveryExpressDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(DeliveryExpressDO::getSort));
    }

    default List<DeliveryExpressDO> selectList(DeliveryExpressExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DeliveryExpressDO>()
                .likeIfPresent(DeliveryExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(DeliveryExpressDO::getName, reqVO.getName())
                .eqIfPresent(DeliveryExpressDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DeliveryExpressDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(DeliveryExpressDO::getSort));
    }

    default DeliveryExpressDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<DeliveryExpressDO>()
                .eq(DeliveryExpressDO::getCode, code));
    }

    default List<DeliveryExpressDO> selectListByStatus(Integer status) {
        return selectList(DeliveryExpressDO::getStatus, status);
    }

}




