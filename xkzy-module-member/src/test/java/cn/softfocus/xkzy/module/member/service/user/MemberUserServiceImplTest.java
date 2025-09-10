package cn.softfocus.xkzy.module.member.service.user;

import cn.hutool.core.util.RandomUtil;
import cn.softfocus.xkzy.framework.common.enums.CommonStatusEnum;
import cn.softfocus.xkzy.framework.common.util.collection.ArrayUtils;
import cn.softfocus.xkzy.framework.redis.config.XkzyRedisAutoConfiguration;
import cn.softfocus.xkzy.framework.test.core.ut.BaseDbAndRedisUnitTest;
import cn.softfocus.xkzy.module.member.controller.app.user.vo.AppMemberUserUpdateMobileReqVO;
import cn.softfocus.xkzy.module.member.dal.dataobject.user.MemberUserDO;
import cn.softfocus.xkzy.module.member.dal.mysql.user.MemberUserMapper;
import cn.softfocus.xkzy.module.member.service.auth.MemberAuthServiceImpl;
import cn.softfocus.xkzy.module.system.api.sms.SmsCodeApi;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.function.Consumer;

import static cn.hutool.core.util.RandomUtil.randomEle;
import static cn.hutool.core.util.RandomUtil.randomNumbers;
import static cn.softfocus.xkzy.framework.test.core.util.RandomUtils.randomPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@link MemberUserServiceImpl} 的单元测试类
 */
@Disabled
@Import({MemberUserServiceImpl.class, XkzyRedisAutoConfiguration.class})
public class MemberUserServiceImplTest extends BaseDbAndRedisUnitTest {

    @Resource
    private MemberUserServiceImpl memberUserService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private MemberUserMapper userMapper;

    @MockitoBean
    private MemberAuthServiceImpl authService;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private SmsCodeApi smsCodeApi;

    @Test
    @Disabled
    public void updateMobile_success(){
        // mock数据
        String oldMobile = randomNumbers(11);
        MemberUserDO userDO = randomUserDO();
        userDO.setMobile(oldMobile);
        userMapper.insert(userDO);

        String oldCode = RandomUtil.randomString(4);
        // 更新手机号
        String newMobile = randomNumbers(11);
        String newCode = randomNumbers(4);
        AppMemberUserUpdateMobileReqVO reqVO = new AppMemberUserUpdateMobileReqVO();
        reqVO.setMobile(newMobile);
        reqVO.setCode(newCode);
        reqVO.setOldCode(oldCode);
        memberUserService.updateUserMobile(userDO.getId(),reqVO);

        assertEquals(memberUserService.getUser(userDO.getId()).getMobile(),newMobile);
    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static MemberUserDO randomUserDO(Consumer<MemberUserDO>... consumers) {
        Consumer<MemberUserDO> consumer = (o) -> {
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()); // 保证 status 的范围
        };
        return randomPojo(MemberUserDO.class, ArrayUtils.append(consumer, consumers));
    }

}
