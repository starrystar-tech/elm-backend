package cn.softfocus.xkzy.module.trade.dal.dataobject.delivery;

import cn.softfocus.xkzy.framework.common.enums.CommonStatusEnum;
import cn.softfocus.xkzy.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 快递公司 DO
 *
 * 三
 */
@TableName(value ="trade_delivery_express")
@KeySequence("trade_delivery_express_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
public class DeliveryExpressDO extends BaseDO {

    /**
     * 编号，自增
     */
    @TableId
    private Long id;

    /**
     * 快递公司 code
     */
    private String code;

    /**
     * 快递公司名称
     */
    private String name;

    /**
     * 快递公司 logo
     */
    private String logo;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

}
