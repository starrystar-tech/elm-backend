package cn.softfocus.xkzy.module.member.convert.address;

import cn.softfocus.xkzy.framework.ip.core.utils.AreaUtils;
import cn.softfocus.xkzy.module.member.api.address.dto.MemberAddressRespDTO;
import cn.softfocus.xkzy.module.member.controller.admin.address.vo.AddressRespVO;
import cn.softfocus.xkzy.module.member.controller.app.address.vo.AppAddressCreateReqVO;
import cn.softfocus.xkzy.module.member.controller.app.address.vo.AppAddressRespVO;
import cn.softfocus.xkzy.module.member.controller.app.address.vo.AppAddressUpdateReqVO;
import cn.softfocus.xkzy.module.member.dal.dataobject.address.MemberAddressDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户收件地址 Convert
 *
 *
 */
@Mapper
public interface AddressConvert {

    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);

    MemberAddressDO convert(AppAddressCreateReqVO bean);

    MemberAddressDO convert(AppAddressUpdateReqVO bean);

    @Mapping(source = "areaId", target = "areaName",  qualifiedByName = "convertAreaIdToAreaName")
    AppAddressRespVO convert(MemberAddressDO bean);

    List<AppAddressRespVO> convertList(List<MemberAddressDO> list);

    MemberAddressRespDTO convert02(MemberAddressDO bean);

    @Named("convertAreaIdToAreaName")
    default String convertAreaIdToAreaName(Integer areaId) {
        return AreaUtils.format(areaId);
    }

    List<AddressRespVO> convertList2(List<MemberAddressDO> list);

}
