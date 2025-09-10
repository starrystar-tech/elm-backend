package cn.softfocus.xkzy.module.crm.dal.mysql.customer;

import cn.softfocus.xkzy.framework.mybatis.core.mapper.BaseMapperX;
import cn.softfocus.xkzy.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.softfocus.xkzy.module.crm.dal.dataobject.customer.CrmCustomerPoolConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户公海配置 Mapper
 *
 * 
 */
@Mapper
public interface CrmCustomerPoolConfigMapper extends BaseMapperX<CrmCustomerPoolConfigDO> {

    default CrmCustomerPoolConfigDO selectOne() {
        return selectOne(new LambdaQueryWrapperX<CrmCustomerPoolConfigDO>().last("LIMIT 1"));
    }

}
