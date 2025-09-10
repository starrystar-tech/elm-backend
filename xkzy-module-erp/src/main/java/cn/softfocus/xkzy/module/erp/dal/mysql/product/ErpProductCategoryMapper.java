package cn.softfocus.xkzy.module.erp.dal.mysql.product;

import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.softfocus.xkzy.module.erp.controller.admin.product.vo.category.ErpProductCategoryListReqVO;
import cn.softfocus.xkzy.module.erp.dal.dataobject.product.ErpProductCategoryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ERP 产品分类 Mapper
 *
 *
 */
@Mapper
public interface ErpProductCategoryMapper extends BaseMapperX<ErpProductCategoryDO> {

    default List<ErpProductCategoryDO> selectList(ErpProductCategoryListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ErpProductCategoryDO>()
                .likeIfPresent(ErpProductCategoryDO::getName, reqVO.getName())
                .eqIfPresent(ErpProductCategoryDO::getStatus, reqVO.getStatus())
                .orderByDesc(ErpProductCategoryDO::getId));
    }

	default ErpProductCategoryDO selectByParentIdAndName(Long parentId, String name) {
	    return selectOne(ErpProductCategoryDO::getParentId, parentId, ErpProductCategoryDO::getName, name);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(ErpProductCategoryDO::getParentId, parentId);
    }

}