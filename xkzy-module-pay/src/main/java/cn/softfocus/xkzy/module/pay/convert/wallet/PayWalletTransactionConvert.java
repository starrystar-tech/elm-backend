package cn.softfocus.xkzy.module.pay.convert.wallet;

import cn.softfocus.xkzy.framework.common.pojo.PageResult;
import cn.softfocus.xkzy.module.pay.controller.admin.wallet.vo.transaction.PayWalletTransactionRespVO;
import cn.softfocus.xkzy.module.pay.controller.app.wallet.vo.transaction.AppPayWalletTransactionRespVO;
import cn.softfocus.xkzy.module.pay.dal.dataobject.wallet.PayWalletTransactionDO;
import cn.softfocus.xkzy.module.pay.service.wallet.bo.WalletTransactionCreateReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayWalletTransactionConvert {

    PayWalletTransactionConvert INSTANCE = Mappers.getMapper(PayWalletTransactionConvert.class);

    PageResult<PayWalletTransactionRespVO> convertPage2(PageResult<PayWalletTransactionDO> page);

    PayWalletTransactionDO convert(WalletTransactionCreateReqBO bean);

}
