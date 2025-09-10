package cn.softfocus.xkzy.module.member.convert.tag;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.module.member.controller.admin.tag.vo.MemberTagCreateReqVO;
import cn.softfocus.xkzy.module.member.controller.admin.tag.vo.MemberTagRespVO;
import cn.softfocus.xkzy.module.member.controller.admin.tag.vo.MemberTagUpdateReqVO;
import cn.softfocus.xkzy.module.member.dal.dataobject.tag.MemberTagDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员标签 Convert
 *
 *
 */
@Mapper
public interface MemberTagConvert {

    MemberTagConvert INSTANCE = Mappers.getMapper(MemberTagConvert.class);

    MemberTagDO convert(MemberTagCreateReqVO bean);

    MemberTagDO convert(MemberTagUpdateReqVO bean);

    MemberTagRespVO convert(MemberTagDO bean);

    List<MemberTagRespVO> convertList(List<MemberTagDO> list);

    PageResult<MemberTagRespVO> convertPage(PageResult<MemberTagDO> page);

}
