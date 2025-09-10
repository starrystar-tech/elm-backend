package cn.softfocus.xkzy.module.trade.framework.delivery.core.client;

import cn.softfocus.xkzy.module.trade.framework.delivery.core.client.dto.ExpressTrackQueryReqDTO;
import cn.softfocus.xkzy.module.trade.framework.delivery.core.client.dto.ExpressTrackRespDTO;

import java.util.List;

/**
 * 快递客户端接口
 *
 * 三
 */
public interface ExpressClient {

    /**
     * 快递实时查询
     *
     * @param reqDTO 查询请求参数
     */
    List<ExpressTrackRespDTO> getExpressTrackList(ExpressTrackQueryReqDTO reqDTO);

}
