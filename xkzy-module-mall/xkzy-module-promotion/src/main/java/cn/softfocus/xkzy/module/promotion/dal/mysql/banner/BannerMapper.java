package cn.softfocus.xkzy.module.promotion.dal.mysql.banner;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.softfocus.xkzy.module.promotion.controller.admin.banner.vo.BannerPageReqVO;
import cn.softfocus.xkzy.module.promotion.dal.dataobject.banner.BannerDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Banner Mapper
 *
 * 
 */
@Mapper
public interface BannerMapper extends BaseMapperX<BannerDO> {

    default PageResult<BannerDO> selectPage(BannerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BannerDO>()
                .likeIfPresent(BannerDO::getTitle, reqVO.getTitle())
                .eqIfPresent(BannerDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BannerDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BannerDO::getSort));
    }

    default void updateBrowseCount(Long id) {
        update(null, new LambdaUpdateWrapper<BannerDO>()
                .eq(BannerDO::getId, id)
                .setSql("browse_count = browse_count + 1"));
    }

    default List<BannerDO> selectBannerListByPosition(Integer position) {
        return selectList(new LambdaQueryWrapperX<BannerDO>().eq(BannerDO::getPosition, position));
    }

}
