package cn.softfocus.xkzy.module.member.convert.group;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.module.member.controller.admin.group.vo.MemberGroupCreateReqVO;
import cn.softfocus.xkzy.module.member.controller.admin.group.vo.MemberGroupRespVO;
import cn.softfocus.xkzy.module.member.controller.admin.group.vo.MemberGroupSimpleRespVO;
import cn.softfocus.xkzy.module.member.controller.admin.group.vo.MemberGroupUpdateReqVO;
import cn.softfocus.xkzy.module.member.dal.dataobject.group.MemberGroupDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户分组 Convert
 *
 * 三
 */
@Mapper
public interface MemberGroupConvert {

    MemberGroupConvert INSTANCE = Mappers.getMapper(MemberGroupConvert.class);

    MemberGroupDO convert(MemberGroupCreateReqVO bean);

    MemberGroupDO convert(MemberGroupUpdateReqVO bean);

    MemberGroupRespVO convert(MemberGroupDO bean);

    List<MemberGroupRespVO> convertList(List<MemberGroupDO> list);

    PageResult<MemberGroupRespVO> convertPage(PageResult<MemberGroupDO> page);

    List<MemberGroupSimpleRespVO> convertSimpleList(List<MemberGroupDO> list);
}
