package cn.softfocus.xkzy.module.trade.service.brokerage;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.framework.test.core.ut.BaseDbUnitTest;
import cn.softfocus.xkzy.module.trade.controller.admin.brokerage.vo.user.BrokerageUserPageReqVO;
import cn.softfocus.xkzy.module.trade.dal.dataobject.brokerage.BrokerageUserDO;
import cn.softfocus.xkzy.module.trade.dal.mysql.brokerage.BrokerageUserMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import jakarta.annotation.Resource;

import static cn.softfocus.xkzy.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static cn.softfocus.xkzy.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static cn.softfocus.xkzy.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.softfocus.xkzy.framework.test.core.util.RandomUtils.randomPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link BrokerageUserServiceImpl} 的单元测试类
 *
 * 三
 */
@Disabled
@Import(BrokerageUserServiceImpl.class)
public class BrokerageUserServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BrokerageUserServiceImpl brokerageUserService;

    @Resource
    private BrokerageUserMapper brokerageUserMapper;

    @Test
    @Disabled
    public void testGetBrokerageUserPage() {
        // mock 数据
        BrokerageUserDO dbBrokerageUser = randomPojo(BrokerageUserDO.class, o -> { // 等会查询到
            o.setBindUserId(null);
            o.setBrokerageEnabled(null);
            o.setCreateTime(null);
        });
        brokerageUserMapper.insert(dbBrokerageUser);
        // 测试 brokerageUserId 不匹配
        brokerageUserMapper.insert(cloneIgnoreId(dbBrokerageUser, o -> o.setBindUserId(null)));
        // 测试 brokerageEnabled 不匹配
        brokerageUserMapper.insert(cloneIgnoreId(dbBrokerageUser, o -> o.setBrokerageEnabled(null)));
        // 测试 createTime 不匹配
        brokerageUserMapper.insert(cloneIgnoreId(dbBrokerageUser, o -> o.setCreateTime(null)));
        // 准备参数
        BrokerageUserPageReqVO reqVO = new BrokerageUserPageReqVO();
        reqVO.setBindUserId(null);
        reqVO.setBrokerageEnabled(null);
        reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

        // 调用
        PageResult<BrokerageUserDO> pageResult = brokerageUserService.getBrokerageUserPage(reqVO);
        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        assertPojoEquals(dbBrokerageUser, pageResult.getList().get(0));
    }

}
