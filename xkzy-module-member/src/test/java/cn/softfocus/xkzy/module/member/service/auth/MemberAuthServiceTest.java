package cn.softfocus.xkzy.module.member.service.auth;

import cn.softfocus.xkzy.framework.common.biz.system.oauth2.OAuth2TokenCommonApi;
import cn.softfocus.xkzy.framework.common.enums.CommonStatusEnum;
import cn.softfocus.xkzy.framework.common.util.collection.ArrayUtils;
import cn.softfocus.xkzy.framework.redis.config.XkzyRedisAutoConfiguration;
import cn.softfocus.xkzy.framework.test.core.ut.BaseDbAndRedisUnitTest;
import cn.softfocus.xkzy.module.member.dal.dataobject.user.MemberUserDO;
import cn.softfocus.xkzy.module.member.dal.mysql.user.MemberUserMapper;
import cn.softfocus.xkzy.module.member.service.user.MemberUserService;
import cn.softfocus.xkzy.module.system.api.logger.LoginLogApi;
import cn.softfocus.xkzy.module.system.api.sms.SmsCodeApi;
import cn.softfocus.xkzy.module.system.api.social.SocialUserApi;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.function.Consumer;

import static cn.hutool.core.util.RandomUtil.randomEle;
import static cn.softfocus.xkzy.framework.test.core.util.RandomUtils.randomPojo;
import static cn.softfocus.xkzy.framework.test.core.util.RandomUtils.randomString;

/**
 * {@link MemberAuthService} 的单元测试类
 */
@Import({MemberAuthServiceImpl.class, XkzyRedisAutoConfiguration.class})
public class MemberAuthServiceTest extends BaseDbAndRedisUnitTest {

    @Resource
    private MemberAuthServiceImpl authService;

    @MockitoBean
    private MemberUserService userService;
    @MockitoBean
    private SmsCodeApi smsCodeApi;
    @MockitoBean
    private LoginLogApi loginLogApi;
    @MockitoBean
    private OAuth2TokenCommonApi oauth2TokenApi;
    @MockitoBean
    private SocialUserApi socialUserApi;
    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @Resource
    private MemberUserMapper memberUserMapper;

//    @Test
//    public void testUpdatePassword_success(){
//        // 准备参数
//        MemberUserDO userDO = randomUserDO();
//        memberUserMapper.insert(userDO);
//
//        // 新密码
//        String newPassword = randomString();
//
//        // 请求实体
//        AppMemberUserUpdatePasswordReqVO reqVO = AppMemberUserUpdatePasswordReqVO.builder()
//                .oldPassword(userDO.getPassword())
//                .password(newPassword)
//                .build();
//
//        // 测试桩
//        // 这两个相等是为了返回ture这个结果
//        when(passwordEncoder.matches(reqVO.getOldPassword(),reqVO.getOldPassword())).thenReturn(true);
//        when(passwordEncoder.encode(newPassword)).thenReturn(newPassword);
//
//        // 更新用户密码
//        authService.updatePassword(userDO.getId(), reqVO);
//        assertEquals(memberUserMapper.selectById(userDO.getId()).getPassword(),newPassword);
//    }

//    @Test
//    public void testResetPassword_success(){
//        // 准备参数
//        MemberUserDO userDO = randomUserDO();
//        memberUserMapper.insert(userDO);
//
//        // 随机密码
//        String password = randomNumbers(11);
//        // 随机验证码
//        String code = randomNumbers(4);
//
//        // mock
//        when(passwordEncoder.encode(password)).thenReturn(password);
//
//        // 更新用户密码
//        AppMemberUserResetPasswordReqVO reqVO = new AppMemberUserResetPasswordReqVO();
//        reqVO.setMobile(userDO.getMobile());
//        reqVO.setPassword(password);
//        reqVO.setCode(code);
//
//        authService.resetPassword(reqVO);
//        assertEquals(memberUserMapper.selectById(userDO.getId()).getPassword(),password);
//    }

    // ========== 随机对象 ==========

    @SafeVarargs
    private static MemberUserDO randomUserDO(Consumer<MemberUserDO>... consumers) {
        Consumer<MemberUserDO> consumer = (o) -> {
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()); // 保证 status 的范围
            o.setPassword(randomString());
        };
        return randomPojo(MemberUserDO.class, ArrayUtils.append(consumer, consumers));
    }


}
